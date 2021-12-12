package nc.prog1415.activities.user_register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import nc.prog1415.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Create User Account");
        setContentView(R.layout.activity_register);
    }
}