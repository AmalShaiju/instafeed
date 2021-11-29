package nc.prog1415;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        Handler h = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                startActivity( new Intent(SplashScreenActivity.this,LoginActivity.class));
            }
        };
        h.postDelayed(r,3000);

    }
}