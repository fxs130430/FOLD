package prologif;

import java.util.ArrayList;
import jpl.*;
public class Clause {
    
    private Term Head;
    private Term[] Body;
    
    public Clause(String x_strHead,String[] x_strBody)
    {
        Body = new Term[x_strBody.length];
        Head = Util.textToTerm(x_strHead);
        for(int i = 0 ; i < Body.length ; i++)
        {
            Body[i] = Util.textToTerm(x_strBody[i]); 
        }
    }
    public Clause(Term x_tHead, Term[] x_tBody)
    {
        Head = x_tHead;
        Body = new Term[x_tBody.length];
        for(int i = 0 ; i < x_tBody.length ; i++)
            Body[i] = x_tBody[i];
    }

    public Clause addLiteral_categorical(Term x_t)
    {
        if(Body.length == 1)// Check for "true" i.e fact
        {
            if(Body[0].isAtom()) // 
                return new Clause(Head,new Term[]{x_t});
        }
        String[] strBody = new String[Body.length + 1];
        for(int i = 0 ; i < Body.length ; i++)
            strBody[i] = Body[i].toString();
        //new literal
        strBody[Body.length] = x_t.toString();
        Clause c = new Clause(Head.toString(), strBody );
        return c;
    }
    public Clause addLiteral_numeric(String x_strPred,String strOperator,double x_nThreshhold)
    {
        String strIdVar = getHeadVars().get(0);
        String strNewVar = find_first_alphabet_not_used();
        String[] strBody = new String[Body.length + 1];
        for(int i = 0 ; i < Body.length ; i++)
            strBody[i] = Body[i].toString();
        //new literal
        strBody[Body.length] = String.format("%s(%s,%s),%s %s %f",x_strPred,strIdVar,strNewVar,strNewVar,strOperator,x_nThreshhold);
        Clause c = null;
        if(Body.length == 1 && Body[0].isAtom())
        {
            c = new Clause(Head.toString(), new String[]{strBody[Body.length]});
        }
        else
        {
            c = new Clause(Head.toString(), strBody );
        }
        return c;
        
    }
    private ArrayList<String> getHeadVars()
    {
        ArrayList<String> arrRet = new ArrayList<>();
        for(Term t: Head.args())
        {
            if(t.isVariable())
                arrRet.add(t.toString());
        }
        return arrRet;
    }
    private ArrayList<String> getBodyVars()
    {
        ArrayList<String> arrRet = new ArrayList<>();
        for(Term t: Body)
        {
            for(Term sub_t: t.args())
            {
                if(sub_t.isVariable())
                {
                    if(!arrRet.contains(sub_t.toString()))
                        arrRet.add(sub_t.toString());
                    
                }
                else if(sub_t.isCompound()) // because of not(path(X,Y))
                {
                    Term[] sub_sub_t = sub_t.args();
                    for(Term tt : sub_sub_t)
                    {
                        if(tt.isVariable() && !arrRet.contains(tt.toString()))
                            arrRet.add(tt.toString());
                    }
                }
            }
        }
        return arrRet;
    }
    public ArrayList<String> getVars()
    {
        ArrayList<String> arrRet = new ArrayList<String>();
        ArrayList<String> arrheadVars = getHeadVars();
        ArrayList<String> arrBodyVars = getBodyVars();
        for(String s: arrheadVars)
            arrRet.add(s);
        for(String s: arrBodyVars)
        {
            if(!arrRet.contains(s))
                arrRet.add(s);
        }
        return arrRet;
    }
    
    public String toString()
    {
        String strRet = Head.toString();
        strRet += " :- ";
        for(int i = 0 ; i < Body.length ; i++)
        {
            strRet += Body[i].toString();
            if(i < Body.length - 1)
                strRet += ",";
        }
        return strRet;
    }
    public boolean retract_clause()
    {
        boolean bRet = false;
        String t = "retractall(("+Head.toString()+"))";
        Query q = new Query(t);
        if(q.hasSolution())
            bRet = true;
        return bRet;
    }
    public boolean assert_clause()
    {
        boolean bRet = false;
        String t  = "assert(("+toString()+"))";
        Query q = new Query(t);
        if(q.hasSolution())
            bRet = true;    
        return bRet;
    }
    public String getHead()
    {
        return Head.toString();
    }
    public void Replace_head(String x_strNewName)
    {
        String strHead = Head.toString();
        int nIndex = strHead.indexOf("(");
        strHead = x_strNewName+strHead.substring(nIndex);
        Head = Util.textToTerm(strHead);        
    }
       
    public ArrayList<Term> getCandidates_categorical(ArrayList<Term> x_arrPreds)
    {
        ArrayList<Term> arrAllCandidates = new ArrayList<>();
        for(Term t: x_arrPreds)
        {
            if(t.name().equals(this.Head.name())) // recursion is restricted in many ways
            {
                // No Left recursion at all
                if(Body.length == 0)
                    continue;
            }
            ArrayList<Term> arrVariablized = getVariablized(t);
            for(Term tt: arrVariablized)
                arrAllCandidates.add(tt);
        }
        return arrAllCandidates;
    }  
    private ArrayList<Term> getVariablized(Term x_tPred)
    {
        ArrayList<Term> arrTerms = new ArrayList<>();
        ArrayList<String> arrVars = this.getVars();
        arrVars.add(find_first_alphabet_not_used());
        ArrayList<String> arrHeadVars = getHeadVars();
        
        String[] arrCombinations = getAllLists((String[])arrVars.toArray(new String[arrVars.size()]),x_tPred.arity());
        
        for(String s:arrCombinations)
        {
            String[] strVar = s.split("");
            boolean bIntersectionempty = true;
            boolean bSameVariables = true;
            for(String v: strVar)
            {
                if(arrHeadVars.contains(v))
                    bIntersectionempty = false;
                else
                    bSameVariables = false;
            }
            if(bIntersectionempty)
                continue;
            if(x_tPred.name().equals(Head.name()) && bSameVariables)
                continue;
            String temp = x_tPred.name()+"(";
            for(int i = 0 ; i < strVar.length ; i++)
            {
                temp+= strVar[i];
                if(i < strVar.length -1)
                    temp +=",";
            }
            temp+=")";
            Term t = Util.textToTerm(temp);
            arrTerms.add(t);
        }
        
        return arrTerms;
        
    }
    private String find_first_alphabet_not_used()
    {
        ArrayList<String> arrVars = getVars();
        String[] alphabet_set = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        for(int i = 0 ; i < alphabet_set.length ; i++)
        {
            if(!arrVars.contains(alphabet_set[i]))
                return alphabet_set[i];
        }
        System.out.println("Run out of alphabets for variables...");
        System.exit(-1);
        return "";
    }
    private String[] getAllLists(String[] elements, int lengthOfList)
    {
        //initialize our returned list with the number of elements calculated above
        String[] allLists = new String[(int)Math.pow(elements.length, lengthOfList)];

        //lists of length 1 are just the original elements
        if(lengthOfList == 1) return elements; 
        else
        {
            //the recursion--get all lists of length 3, length 2, all the way up to 1
            String[] allSublists = getAllLists(elements, lengthOfList - 1);

            //append the sublists to each element
            int arrayIndex = 0;

            for(int i = 0; i < elements.length; i++)
            {
                for(int j = 0; j < allSublists.length; j++)
                {
                    //add the newly appended combination to the list
                    allLists[arrayIndex] = elements[i] + allSublists[j];
                    arrayIndex++;
                }
            }

            return allLists;
        }
    }
    
}
