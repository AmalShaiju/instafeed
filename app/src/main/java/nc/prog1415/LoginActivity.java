package nc.prog1415;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.Locale;

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
        btnLogin = findViewById(R.id.btnLogin);
        txtUsername = findViewById(R.id.txtUsername);
        lblRegister = findViewById(R.id.lblRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String s = txtUsername.getText().toString();
                if(s.equals("donor")){
                    intent = new Intent(LoginActivity.this, DonorViewActivity.class);

                }else{
                    intent = new Intent(LoginActivity.this, ClaimerViewActivity.class);
                }
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, btnLogin, "login").toBundle();
                startActivity(intent,bundle);
            }
        });

        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void config() {
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);
    }

}