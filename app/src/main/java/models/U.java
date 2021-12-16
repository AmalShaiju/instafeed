package models;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nc.prog1415.R;

// Utility Class
public class U {
    public static boolean IsNullOrEmpty(String parm) {
        if (parm != null && !parm.trim().isEmpty())
            return false;
        return true;
    }

    public static Bitmap ByteToBitMap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

    }

    public static String ToDateString (LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:m a");
        return dateTime.format(formatter); // "1986-04-08 12:30"
    }

    public static String getFromTxtbox(EditText txtbox){
        return txtbox.getText().toString().trim();
    }

//    private void getIntent(Context context,Class fromActivity, Class toActivity) {
//        Intent intent = new Intent(fromActivity,toActivity);
//        intent.putExtra("MyClass", context);
//    }


}
