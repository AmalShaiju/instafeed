package models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import nc.prog1415.R;

// Utility Class
public class U {
    public static boolean isNullOrEmpty(String parm){
        if(parm != null && !parm.trim().isEmpty())
            return false;
        return true;
    }

    public static Bitmap byteToBitMap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

    }
    

}
