package prologif;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;
import jpl.*;


public class FOIL {
    
    public final ArrayList<Term>     m_posExamples;
    private final ArrayList<Term>     m_negExamples;
    private ArrayList<Term>           m_candidatePreds_categorical;
    private ArrayList<Term>           m_candidatePreds_numeric;
    
    private ArrayList<Clause>   m_Clauses;
    private ArrayList<Clause>   m_ExceptionClauses;
    private String              m_Goal;
    private Query               m_qfoil;
    private Query               m_qDS;
    private int                 m_nAbIndex;
    
    public FOIL()
    {
        m_posExamples = new ArrayList<>();
        m_negExamples = new ArrayList<>();
        m_Clauses = new ArrayList<>();
        m_ExceptionClauses = new ArrayList<>();
        m_nAbIndex = 0;
        m_candidatePreds_categorical = new ArrayList<>();
        m_candidatePreds_numeric = new ArrayList<>();
    }
    public boolean Initialize(String x_strDataset)
    {
        String t1 = "consult('foil.pl')";
        String t2 = "consult('"+x_strDataset+"')";
        m_qfoil = new Query(t1);
        if(!m_qfoil.hasSolution())
        {
            System.out.println("failed to initialized foil...");
            return false;
        }
        else
        {
            m_qDS = new Query(t2);
            if(m_qDS.hasSolution())
            {
                System.out.printf("%s initialized successfully...\r\n","foil.pl");
                System.out.printf("%s initialized successfully...\r\n",x_strDataset);
                
                getAllowedPredicates_categorical();
                getAllowedPredicates_numeric();
                return true;
            }
            else
            {
                System.out.printf("failed to load dataset %s...\r\n",x_strDataset);
                return false;
            }
        } 
    }
    private boolean getAllowedPredicates_categorical()
    {
        String[] alphabet_set = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        
        Query q = new Query("foil_predicates(X)");
        if(q.hasSolution())
        {
            Term t = (Term)q.oneSolution().get("X");
            Term[] sub_t = Util.listToTermArray(t);
            for(Term tt: sub_t)
            {
                String strTT = tt.toString();
                String strTemp = strTT.substring(strTT.indexOf("(")+1, strTT.lastIndexOf(")"));
                strTemp = strTemp.replaceAll("\\s+","");
                String[] arrTemp = strTemp.split(",");
                int alph = 0;
                String strVars = "";
                
                int varLength = (int)Integer.valueOf(arrTemp[1]);
                for(int i = 0 ; i < varLength  ; i++)
                {
                    strVars += alphabet_set[alph++];
                    if(i < varLength -1)
                        strVars +=",";
                }
                String strPred = String.format("%s(%s)", arrTemp[0],strVars);
                m_candidatePreds_categorical.add(Util.textToTerm(strPred));
            }
        }
        return true;
    }
    private boolean getAllowedPredicates_numeric()
    {
        String[] alphabet_set = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        
        Query q = new Query("foil_numeric_predicates(X)");
        if(q.hasSolution())
        {
            Term t = (Term)q.oneSolution().get("X");
            Term[] sub_t = Util.listToTermArray(t);
            for(Term tt: sub_t)
            {
                String strTT = tt.toString();
                String strTemp = strTT.substring(strTT.indexOf("(")+1, strTT.lastIndexOf(")"));
                strTemp = strTemp.replaceAll("\\s+","");
                String[] arrTemp = strTemp.split(",");
                int alph = 0;
                String strVars = "";
                
                int varLength = (int)Integer.valueOf(arrTemp[1]);
                for(int i = 0 ; i < varLength  ; i++)
                {
                    strVars += alphabet_set[alph++];
                    if(i < varLength -1)
                        strVars +=",";
                }
                String strPred = String.format("%s(%s)", arrTemp[0],strVars);
                m_candidatePreds_numeric.add(Util.textToTerm(strPred));
            }
        }
        return true;
    }
    public void SetGoal(String x_strGoal)
    {
        m_Goal = x_strGoal;
    }
    public boolean getExamples()
    {
        String t1 = "get_examples("+m_Goal+",Positives,Negatives)";
        Query q = new Query(t1);
        if(q.hasSolution())
        {
            Compound comp_pos = (Compound)q.oneSolution().get("Positives");
            Compound comp_neg = (Compound)q.oneSolution().get("Negatives");
            
            for(Term t:comp_pos.toTermArray())
                m_posExamples.add(t);
            
            for(Term t:comp_neg.toTermArray())
                m_negExamples.add(t);
                        
            return true;
        }
        return false;        
    }
    public void foil_run()
    {
        foil_loop(m_posExamples, m_negExamples, m_Clauses);
        System.out.println("Learned concept clause(s):");
        for(Clause c: m_Clauses)
            System.out.printf("%s.\r\n", c.toString());
        System.out.println("Exception clause(s):");
        for(Clause c: m_ExceptionClauses)
            System.out.printf("%s.\r\n", c.toString());
    }
    private void foil_loop(ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms,ArrayList<Clause> x_Clauses)
    {
        while(x_PosTerms.size() > 0)
        {
            Clause CurClause = new Clause(Util.textToTerm(m_Goal),new Term[]{Util.textToTerm("true")});
            Clause c_new = null;
            if(m_candidatePreds_numeric.size() == 0)
                c_new = extend_clause_loop_only_categorical(CurClause,x_PosTerms,x_NegTerms);
            else
                c_new = extend_clause_loop(CurClause,x_PosTerms,x_NegTerms);
            x_PosTerms = update_covered_examples(c_new,x_PosTerms);
            x_Clauses.add(c_new);
            System.out.printf("Clause added:>>>> %s\r\n", c_new);
        }        
    }
    public ArrayList<Term> covered_examples(Clause x_clause,ArrayList<Term> x_Terms)
    {
        ArrayList<Term> arrTemp = new ArrayList<>();
        String strPos_list = toPrologList(x_Terms);
        String t1 = "covered_examples(("+x_clause.toString()+"), "+strPos_list+", Xs1)";
        Query q = new Query(t1);
        if(q.hasSolution())
        {
            Term t = (Term)q.oneSolution().get("Xs1");
            Term[] sub_t = Util.listToTermArray(t);
            for(Term tt: sub_t)
            {
                arrTemp.add(tt);
                //System.out.println(tt.toString());
            }
        }
        else
        {
            System.out.println("Error in covered_examples");
            System.exit(-1);
        } 
        return arrTemp;
    }
    public ArrayList<Term> update_covered_examples(Clause x_clause,ArrayList<Term> x_PosTerms)
    {
        ArrayList<Term> arrUncovered = new ArrayList<>();
        String strPos_list = toPrologList(x_PosTerms);
        String t1 = "uncovered_examples(("+x_clause.toString()+"), "+strPos_list+", Xs1)";
        Query q = new Query(t1);
        if(q.hasSolution())
        {
            Term t = (Term)q.oneSolution().get("Xs1");
            Term[] sub_t = Util.listToTermArray(t);
            for(Term tt: sub_t)
            {
                arrUncovered.add(tt);
                //System.out.println(tt.toString());
            }            
        }
        else
        {
            System.out.println("Error in update_covered_examples");
            System.exit(-1);
        }        
        return arrUncovered;
    }
    private Clause extend_clause_loop_only_categorical(Clause x_clause,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        Clause c_new = null;
        boolean bJustStarted = true;
        while(x_NegTerms.size() > 0)
        {
            ArrayList<Term> arrCandidateTerms = x_clause.getCandidates_categorical(m_candidatePreds_categorical);
            Pair<Clause,Double> pair = best_next_clause_categorical(x_clause, arrCandidateTerms,x_PosTerms,x_NegTerms);
            
            if(pair.Second > 0 )
            {
                c_new = pair.First;
                x_clause = c_new;
            }
            else
            {
                if(bJustStarted)
                {
                    c_new = enumerate(x_clause, x_PosTerms);
                }
                else
                {
                    //Call Exception part (Swap [psitive and Negative])
                    
                    Pair<Clause,ArrayList<Clause>> pair_exception = exception_handler(x_clause, x_NegTerms, x_PosTerms);
                    if(pair_exception.First == null)
                    {
                        c_new = enumerate(x_clause, x_PosTerms);
                    }
                    else
                    {
                        c_new = pair_exception.First;
                        for(Clause c: pair_exception.Second)
                            m_ExceptionClauses.add(c);
                    }
                }
                
            }
            bJustStarted = false;
            x_PosTerms = covered_examples(c_new, x_PosTerms);
            x_NegTerms = covered_examples(c_new, x_NegTerms);
        }
        return c_new;
    }
    private Clause extend_clause_loop(Clause x_clause,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        Clause c_new = null;
        //boolean bDefault_is_empty = true;
        boolean bJustStarted = true;
        while(x_NegTerms.size() > 0)
        {
            ArrayList<Term> arrCandidateTerms = x_clause.getCandidates_categorical(m_candidatePreds_categorical);
            Pair<Clause,Double> pair_cat = best_next_clause_categorical(x_clause, arrCandidateTerms,x_PosTerms,x_NegTerms);
            Pair<Clause,Double> pair_num = best_next_clause_numerical(x_clause,x_PosTerms,x_NegTerms);
            //Pair<Clause,ArrayList<Clause>> pair_exception = null;
            //if(!bDefault_is_empty)
            //    pair_exception = exception_handler(x_clause, x_NegTerms, x_PosTerms);
            
            
            //System.out.printf("Cat specialize: %s\r\n",pair_cat.First);
            //System.out.printf("Num specialize: %s\r\n",pair_num.First);
            
            if(pair_cat.Second == 0 && pair_num.Second == 0)
            {
                if(bJustStarted)
                {
                    c_new = enumerate(x_clause, x_PosTerms);
                }
                else
                {
                    Pair<Clause,ArrayList<Clause>> pair_exception = exception_handler(x_clause, x_NegTerms, x_PosTerms);
                    if(pair_exception.First == null)
                    {
                        c_new = enumerate(x_clause, x_PosTerms);
                    }
                    else
                    {
                        c_new = pair_exception.First;
                        for(Clause c: pair_exception.Second)
                            m_ExceptionClauses.add(c);
                    }
                }
            }
            else if(pair_cat.Second >= pair_num.Second ) // No need to check the exception clause
            {
                c_new = pair_cat.First;
                x_clause = c_new;
            }
            else // 
            {
                c_new = pair_num.First;
                x_clause = c_new;
            } 
            bJustStarted = false;
            x_PosTerms = covered_examples(c_new, x_PosTerms);
            x_NegTerms = covered_examples(c_new, x_NegTerms);
        }
        return c_new;
    }
    private Clause enumerate(Clause x_clause,ArrayList<Term> x_PosTerms)
    {
        String strList = toPrologList(x_PosTerms);
        String strHead = x_clause.getHead();
        String strBody = String.format("member(%s,%s)", strHead,strList);
        return new Clause(strHead, new String[]{strBody});
    }
    private Pair<Clause,ArrayList<Clause>> exception_handler(Clause x_curClause,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        //System.out.printf("Finding exception for the rule %s\r\n",x_curClause);
        ArrayList<Clause> arrAbPredicates = new ArrayList<>();
        ArrayList<Term> arrCandidateTerms = x_curClause.getCandidates_categorical(m_candidatePreds_categorical);
        Pair<Clause,Double> pair_cat = best_next_clause_categorical(x_curClause, arrCandidateTerms, x_PosTerms, x_NegTerms);
        Pair<Clause,Double> pair_num = best_next_clause_numerical(x_curClause, x_PosTerms, x_NegTerms);
        Clause c_ret = null;
        
        if(pair_cat.Second > 0 || pair_num.Second > 0) // positive gain
        {
            ArrayList<Clause> arrClauses = new ArrayList<>();
            foil_loop(x_PosTerms, x_NegTerms,arrClauses);
            if(arrClauses.size() > 0)
            {
                String strHead = String.format("ab%d", m_nAbIndex++);
                for(int i = 0 ; i < arrClauses.size() ; i++)
                {
                    Clause c = arrClauses.get(i);
                    c.Replace_head(strHead); 
                    //m_ExceptionClauses.add(c);
                    arrAbPredicates.add(c);
                    if(c.assert_clause())
                    {
                        System.out.printf("%s added to the exceptions.\r\n", c.toString());
                    }
                    else
                    {
                        System.out.printf("Error in asserting clause %s\r\n",c.toString());
                        System.exit(-1);
                    }
                }
                String strNewLiteral = String.format("not(%s)", arrClauses.get(0).getHead());
                c_ret = x_curClause.addLiteral_categorical(Util.textToTerm(strNewLiteral));
            }
        }
        if(c_ret == null)
        {
            System.out.println("null....");
        }
        return new Pair<Clause,ArrayList<Clause>>(c_ret,arrAbPredicates);
    }
    private String toPrologList(ArrayList<Term> x_terms)
    {
        String strList ="[";
        for(int i = 0 ; i < x_terms.size() ; i++)
        {
            strList +=x_terms.get(i).toString();
            if(i < x_terms.size() - 1)
                strList +=",";
        }
        strList += "]";
        return strList;
    }
    private double getInfo_value(Clause x_clause,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        String strPos_list = toPrologList(x_PosTerms);
        String strNeg_list = toPrologList(x_NegTerms);
        String t1 = "info_value(("+x_clause.toString()+"), "+strPos_list+","+strNeg_list+", Value)";
        Query q1 = new Query(t1);
        if(q1.hasSolution())
        {
            double dGain = ((Term)q1.oneSolution().get("Value")).doubleValue();
            return dGain;
        }
        System.out.println("Error in getInfo_value");
        System.exit(-1);
        return 0;        
    }
    private double compute_gain(Clause x_clause,double x_dCurrentInfo,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        String strPos_list = toPrologList(x_PosTerms);
        String strNeg_list = toPrologList(x_NegTerms);
        String t1 = "compute_gain("+strNeg_list+", "+strPos_list+", "+x_dCurrentInfo+", ("+x_clause.toString()+"),Gain)";
        Query q1 = new Query(t1);
        if(q1.hasSolution())
        {
            double dGain = ((Term)q1.oneSolution().get("Gain")).doubleValue();
            return dGain;
        }
        System.out.println("Error in gain computation");
        System.exit(-1);
        return 0;
    }
    
    private Pair<Clause,Double> best_next_clause_categorical(Clause x_clause,ArrayList<Term> x_lstCandidates,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        double dCurrent_Info = getInfo_value(x_clause, x_PosTerms, x_NegTerms);
        Clause c_best_categorical = null;
        double dGain_best_categorical = 0;
        for(int i = 0 ; i < x_lstCandidates.size() ; i++)
        {
            Term t = x_lstCandidates.get(i);
            Clause c_new = x_clause.addLiteral_categorical(t);
            double dGain = compute_gain(c_new, dCurrent_Info, x_PosTerms, x_NegTerms);
            //double dSplitInfo = splitInformation_categorical(t);
            double dSplitInfo = 1;
            double dGainRatio = dGain / dSplitInfo;
            if(dGainRatio > dGain_best_categorical)
            {
                dGain_best_categorical = dGainRatio;
                c_best_categorical = c_new;
            }
        }
        
        return new Pair<Clause,Double>(c_best_categorical,dGain_best_categorical);
       
    }
    private Pair<Clause,Double> best_next_clause_negated_categorical(Clause x_clause,ArrayList<Term> x_lstCandidates,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        double dCurrent_Info = getInfo_value(x_clause, x_PosTerms, x_NegTerms);
        Clause c_best_categorical = null;
        double dGain_best_categorical = 0;
        for(int i = 0 ; i < x_lstCandidates.size() ; i++)
        {
            Term t = x_lstCandidates.get(i);
            
            String strNewLiteral = String.format("not(%s)", t.toString());
            //c_ret = x_curClause.addLiteral_categorical(Util.textToTerm(strNewLiteral));
            Clause c_new = x_clause.addLiteral_categorical(Util.textToTerm(strNewLiteral));
            double dGain = compute_gain(c_new, dCurrent_Info, x_PosTerms, x_NegTerms);
            //double dSplitInfo = splitInformation_categorical(t);
            double dSplitInfo = 1;
            double dGainRatio = dGain / dSplitInfo;
            if(dGainRatio > dGain_best_categorical)
            {
                dGain_best_categorical = dGainRatio;
                c_best_categorical = c_new;
            }
        }
        
        return new Pair<Clause,Double>(c_best_categorical,dGain_best_categorical);
       
    }
    private Pair<Clause,Double> best_next_clause_numerical(Clause x_clause,ArrayList<Term> x_PosTerms,ArrayList<Term> x_NegTerms)
    {
        double dCurrent_Info = getInfo_value(x_clause, x_PosTerms, x_NegTerms);
        Clause c_best_numerical = null;
        double dGain_best_numerical = 0;
        String[] arrOperators = new String[]{"=<",">="};
        ArrayList<String> arrIds = getIds(x_clause, x_PosTerms);
        for(Term t: m_candidatePreds_numeric)
        {
           ArrayList<Double> arrRange = getNumericRange(arrIds,t.name());
           for(int i = 1 ; i < arrRange.size() - 1 ; i++)
           {
                for(int j = 0 ; j < arrOperators.length ; j++)
                {
                    double dThreshold = (arrRange.get(i) + arrRange.get(i+1))/2;
                    //if(arrOperators[j].equals("="))
                    //    dThreshold = arrRange.get(i);
                    Clause c_new = x_clause.addLiteral_numeric(t.name(),arrOperators[j],dThreshold);
                    double dGain = compute_gain(c_new, dCurrent_Info, x_PosTerms, x_NegTerms);
                    //double dSplitInfo = splitInformation_numerical(arrRange, arrOperators[j], i);
                    //double dGainRatio = dGain / dSplitInfo;
                    double dGainRatio = dGain;
                    if(dGainRatio > dGain_best_numerical)
                    {
                        dGain_best_numerical = dGainRatio;
                        c_best_numerical = c_new;
                    }
                }
           }                   
        }
        
        return new Pair<Clause,Double>(c_best_numerical,dGain_best_numerical);
    }
    public ArrayList<Double> getNumericRange(ArrayList<String> x_arrIds,String x_strPred)
    {
        String strListOfIds = "[";
        for(int i = 0 ; i < x_arrIds.size() ; i++)
        {
            strListOfIds +=x_arrIds.get(i);
            if(i < x_arrIds.size() - 1)
                strListOfIds += ",";
        }
        strListOfIds += "]";
        
        String t1 = String.format("findall(V,(member(Id,%s),%s(Id,V)),Range)",strListOfIds ,x_strPred);
        ArrayList<Double> arrRange = new ArrayList<>();
        Query q = new Query(t1);
        
        if(q.hasSolution())
        {
            Term t = (Term)q.oneSolution().get("Range");
            Term[] sub_t = Util.listToTermArray(t);
            for(Term tt: sub_t)
            {
                double n = Double.valueOf(tt.toString());
                if(!arrRange.contains(n))
                    arrRange.add(n);
            }   
        }
        else
        {
            System.out.println("Error in getNumericRange");
            System.exit(-1);
        }
        Collections.sort(arrRange);
        return arrRange;
    }
    private double splitInformation_numerical(ArrayList<Integer> x_arrRange,String x_strOperator,int x_nIndex)
    {
        //"=<","<",">",">="
        double nGroup1 = 0;
        double nGroup2 = 0;
        int nTotal = x_arrRange.size();
        switch(x_strOperator)
        {
            case "=<":
                nGroup1 = x_nIndex;
                break;
            case "<":
                nGroup1 = x_nIndex -1;
                break;
            case ">":
                nGroup1 = x_nIndex + 1;
                break;
            case ">=":
                nGroup1 = x_nIndex;
                break;
        }
        if(nGroup1 < 0)
            nGroup1 = 0;
        nGroup2 = nTotal - nGroup1;
        if(nGroup1 == nTotal || nGroup2 == nTotal)
            return 1.0;
        return -(nGroup1/nTotal) * (Math.log(nGroup1/nTotal) / Math.log(2)) - (nGroup2/nTotal)*(Math.log(nGroup2/nTotal) / Math.log(2));
    }
    private double splitInformation_categorical(Term x_t)
    {
        double nTotal = m_posExamples.size() + m_negExamples.size();
        double nTermCOunt = findall(x_t);
         
        double dRet = -(nTermCOunt/nTotal) * (Math.log(nTermCOunt/nTotal) / Math.log(2)) - ((nTotal - nTermCOunt)/nTotal)*(Math.log((nTotal - nTermCOunt)/nTotal) / Math.log(2));
        return dRet;
    }
    private int findall(Term x_t)
    {
        String t1 = "findall(X,"+x_t.toString()+",Vs),length(Vs,N)";
        Query q = new Query(t1);
        int nCount = 0;
        if(q.hasSolution())
        {
            nCount = Integer.valueOf(q.oneSolution().get("N").toString());
        }
        else
        {
            System.out.println("Error in findall");
            System.exit(-1);
        }
        return nCount;
    }
    public ArrayList<String> getIds(Clause x_clause,ArrayList<Term> x_posExamples)
    {
        ArrayList<String> arrRet = new ArrayList(); 
        String strList = toPrologList(x_posExamples);
        String t1 = "tuples(("+x_clause.toString()+"),"+strList+",Tuples)";
        Query q = new Query(t1);
        if(q.hasSolution())
        {
            Term t = (Term)q.oneSolution().get("Tuples");
            Term[] sub_t = Util.listToTermArray(t);
            for(Term tt: sub_t)
            {
                for(Term ttt:tt.toTermArray())
                    arrRet.add(ttt.toString());
            }
        }
        else
        {
            System.out.println("Error in getIds");
            System.exit(-1);
        }
        return arrRet;
    }
}
