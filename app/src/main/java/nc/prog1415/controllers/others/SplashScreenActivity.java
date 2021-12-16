package nc.prog1415.controllers.others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.Context;
import models.PickupRequest;
import models.User;
import models.UserType;
import nc.prog1415.R;
import nc.prog1415.controllers.login.LoginActivity;
import nc.prog1415.controllers.user_register.RegisterActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        try {
            Context.seedData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler h = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                NaviagteTo(LoginActivity.class);
            }
        };
        h.postDelayed(r,3000);

    }

    private void NaviagteTo(Class toActivity){
        Intent intent = new Intent(SplashScreenActivity.this,toActivity);
        //intent.putExtra("Context", context);
        startActivity(intent);
    }


}