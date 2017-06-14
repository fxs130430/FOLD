
package prologif;

import java.util.ArrayList;
import java.util.Hashtable;
import jpl.*;

public class PrologIF {

    public static void main(String[] args) {
        // TODO code application logic here
        FOIL f = new FOIL();
        
        //if(f.Initialize("soybean_train.pl"))
            //f.SetGoal("diaporthe_stem_canker(X)");
            //f.SetGoal("charcoal_rot(X)");
            //f.SetGoal("rhizoctonia_root_rot(X)");
            //f.SetGoal("phytophthora_rot(X)");
            //f.SetGoal("brown_stem_rot(X)");
            //f.SetGoal("powdery_mildew(X)");
            //f.SetGoal("downy_mildew(X)");
            //f.SetGoal("brown_spot(X)");
            //f.SetGoal("bacterial_blight(X)");
            //f.SetGoal("bacterial_pustule(X)");
            //f.SetGoal("purple_seed_stain(X)");
            //f.SetGoal("anthracnose(X)");
            //f.SetGoal("phyllosticta_leaf_spot(X)");
            //f.SetGoal("alternarialeaf_spot(X)");
            //f.SetGoal("frog_eye_leaf_spot(X)");
            //f.SetGoal("diaporthe_pod_and_stem_blight(X)");
            //f.SetGoal("cyst_nematode(X)");
            //f.SetGoal("injury_2_4_d(X)");
            //f.SetGoal("herbicide_injury(X)");
        
        //if(f.Initialize("labor_train.pl"))
        //    f.SetGoal("good_contract(X)");
        
        //if(f.Initialize("postoperative.pl"))
        //    f.SetGoal("gohome(X)");
        
        //if(f.Initialize("heart_train.pl"))
        //  f.SetGoal("heart_desease(X)");
        //if(f.getExamples())
        //    f.foil_run();

        //if(f.Initialize("flies.pl"))
        //    f.SetGoal("flies(X)"); 
        
        //if(f.Initialize("playtennis.pl"))
        //    f.SetGoal("play(X)");
        
        //if(f.Initialize("flies_only_categorical.pl"))
        //    f.SetGoal("flies(X)");
        
        //if(f.Initialize("playtennis_categorical.pl"))
        //    f.SetGoal("play(X)");
        
        //if(f.Initialize("flies(7).pl"))
        //    f.SetGoal("flies(X)");
        
        //if(f.Initialize("credit_train.pl"))
        //  f.SetGoal("denied(X)");

        
        //if(f.Initialize("credit_au_train.pl"))
        //  f.SetGoal("denied(X)");

        
        //if(f.Initialize("iris_train.pl"))
        //  f.SetGoal("versicolor(X)");
        
        //if(f.Initialize("contraceptive_train.pl"))
        //    f.SetGoal("long_term(X)");
        
        //if(f.Initialize("acute_train.pl"))
        //    f.SetGoal("nephritis(X)");
        
        //if(f.Initialize("credit_g_train.pl"))
        //  f.SetGoal("denied(X)");
        
        //if(f.Initialize("animals.pl"))
        //    f.SetGoal("type_3(X)");
        //if(f.Initialize("mushroom_train.pl"))
        //    f.SetGoal("poisonous(X)");
        
        //if(f.getExamples())
        //{
        //    f.foil_run();
        //}
        //credit_australia_cross_validation(); 
        //credit_japanese_cross_validation();
        //credit_germany_cross_validation();
        //bridges_cross_validation();
        //iris_cross_validation();
        chess_cross_validation();
        //ecoli_cross_validation();
        //postoperative_cross_validation();
    } 
    public static void ecoli_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("ecoli_train_0.pl"))
            f.SetGoal("im(X)");
        if(f.getExamples())
            f.foil_run();
    }

    public static void credit_australia_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("credit_au_train_9.pl"))
            f.SetGoal("denied(X)");
        if(f.getExamples())
            f.foil_run();
    }
    public static void credit_japanese_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("credit_j_train_9.pl"))
            f.SetGoal("denied(X)");
        if(f.getExamples())
            f.foil_run();
    }
    public static void credit_germany_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("credit_g_train_0.pl"))
            f.SetGoal("denied(X)");
        if(f.getExamples())
            f.foil_run();
    }
    public static void bridges_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("bridges_train_0.pl"))
            f.SetGoal("cantilev(X)");
        if(f.getExamples())
            f.foil_run();
    }
    public static void iris_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("iris_train_3.pl"))
            f.SetGoal("versicolor(X)");
        if(f.getExamples())
            f.foil_run();
    }
    public static void postoperative_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("post_operative_train_0.pl"))
            f.SetGoal("generalfloor(X)");
        if(f.getExamples())
            f.foil_run();
    }
    public static void chess_cross_validation()
    {
        FOIL f = new FOIL();
        if(f.Initialize("chess_train_0.pl"))
            f.SetGoal("draw(X)");
        if(f.getExamples())
            f.foil_run();
    }
}
