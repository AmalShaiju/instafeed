package models;

// Utility Class
public class U {
    public static boolean isNullOrEmpty(String parm){
        if(parm != null && !parm.trim().isEmpty())
            return false;
        return true;
    }


}
