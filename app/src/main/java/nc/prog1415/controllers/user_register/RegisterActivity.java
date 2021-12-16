package nc.prog1415.controllers.user_register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

import models.Context;
import models.U;
import models.User;
import models.UserType;
import nc.prog1415.R;
import nc.prog1415.controllers.claimer_view.activities.ClaimerViewActivity;
import nc.prog1415.controllers.donor_view.activities.PrCreateActivity;
import nc.prog1415.controllers.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText txtLocation;
    EditText txtFirstName;
    EditText txtLastName;
    EditText txtEmail;
    EditText txtPhone;
    EditText txtOrganizationName;
    RadioButton rbtnClaimer;
    RadioButton rbtnDonor;
    EditText txtNewPassword;
    EditText txtConfirmPassword;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Create User Account");
        setContentView(R.layout.activity_register);
        setControls();

        //Inititalize places
        Places.initialize(getApplicationContext(),"AIzaSyBPWqoOuPUoqu0EZjszT1I_0g23D9MZrnw");

        txtLocation.setFocusable(false);
        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializer place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG,Place.Field.NAME);

                // Create intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,fieldList).build(RegisterActivity.this);

                //start activity result
                startActivityForResult(intent,100);

            }
        });

        rbtnClaimer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtOrganizationName.setVisibility(View.VISIBLE);
                }else{
                    txtOrganizationName.setVisibility(View.GONE);
                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInputDataValid()){
                    if((U.getFromTxtbox(txtNewPassword).equals(U.getFromTxtbox(txtConfirmPassword)))){
                        User newUser = getUserFromInput();
                        boolean registerSuccess = Context.RegisterUser(newUser);
                        if(registerSuccess)
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        else
                            Toast.makeText(getApplicationContext(),"Email already in use",Toast.LENGTH_LONG);
                    }else
                        Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_LONG);
                }else
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_LONG);
            }
        });
    }

    private void setControls(){
        txtLocation = findViewById(R.id.user_register_txtAddress);
        txtFirstName = findViewById(R.id.user_register_txtFirst);
        txtLastName = findViewById(R.id.user_register_txtLast);
        txtEmail = findViewById(R.id.user_register_txtEmail);
        txtPhone = findViewById(R.id.user_register_txtPhone);
        txtNewPassword = findViewById(R.id.user_register_txtNewPass);
        txtConfirmPassword = findViewById(R.id.user_register_txtConfirmPass);
        btnCreate = findViewById(R.id.user_register_btnCreateAcc);
        rbtnClaimer = findViewById(R.id.user_register_rbtnClaimer);
        txtOrganizationName = findViewById(R.id.user_register_txtOrgName);

    }

    private boolean isInputDataValid(){
        boolean isValid = false;

        isValid =  !(U.IsNullOrEmpty(U.getFromTxtbox(txtLocation))
                && U.IsNullOrEmpty(U.getFromTxtbox(txtFirstName))
                && U.IsNullOrEmpty(U.getFromTxtbox(txtLastName))
                && U.IsNullOrEmpty(U.getFromTxtbox(txtEmail))
                && U.IsNullOrEmpty(U.getFromTxtbox(txtPhone))
                && U.IsNullOrEmpty(U.getFromTxtbox(txtNewPassword))
                && U.IsNullOrEmpty(U.getFromTxtbox(txtConfirmPassword)));

        if(rbtnClaimer.isChecked()){
            if(U.IsNullOrEmpty(U.getFromTxtbox(txtOrganizationName)))
                isValid = false;
        }
        return isValid;
    }

    private User getUserFromInput(){
        return new User(U.getFromTxtbox(txtFirstName),
                U.getFromTxtbox(txtLastName),
                U.getFromTxtbox(txtEmail),
                U.getFromTxtbox(txtPhone),
                U.getFromTxtbox(txtLocation),
                U.getFromTxtbox(txtOrganizationName),
                U.getFromTxtbox(txtNewPassword),
                rbtnClaimer.isChecked() ? UserType.CLAIMER : UserType.DONOR);
    }

    private Context getContextFromIntent(){
        return (Context) getIntent().getSerializableExtra("Context");
    }

    private void NaviagteTo(Class toActivity){
        Intent intent = new Intent(RegisterActivity.this,toActivity);
        //intent.putExtra("Context", context);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            Place place = Autocomplete.getPlaceFromIntent(data);
            txtLocation.setText(place.getAddress());
        }else if(resultCode == AutocompleteActivity.RESULT_ERROR){
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(),status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}