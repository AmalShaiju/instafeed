package nc.prog1415.controllers.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import models.Context;
import models.U;
import nc.prog1415.controllers.claimer_view.activities.ClaimerViewActivity;
import nc.prog1415.controllers.donor_view.activities.DonorViewActivity;
import nc.prog1415.R;
import nc.prog1415.controllers.user_register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername;
    TextView lblRegister;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        config();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        setControls();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginSuccess = Context.Login(U.getFromTxtbox(txtUsername),U.getFromTxtbox(txtPassword));
                if(loginSuccess){
                    if(false){
                        NaviagteTo(ClaimerViewActivity.class);
                    }else{
                        NaviagteTo(DonorViewActivity.class);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Username or password does not match",Toast.LENGTH_LONG);
                }
            }
        });

        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviagteTo(RegisterActivity.class);
            }
        });
    }

    private void config() {
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);
    }

    private void setControls(){
        btnLogin = findViewById(R.id.login_btnLogin);
        txtUsername = findViewById(R.id.login_txtUsername);
        txtPassword = findViewById(R.id.login_txtPassword);
        lblRegister = findViewById(R.id.login_lblRegister);
    }

    private void NaviagteTo(Class toActivity){
       Intent intent = new Intent(LoginActivity.this,toActivity);
       Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, btnLogin, "login").toBundle();
       startActivity(intent,bundle);
    }


}