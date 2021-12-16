package nc.prog1415.controllers.donor_view.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import models.Context;
import models.PickupRequest;
import models.U;
import nc.prog1415.R;

public class PrCreateActivity extends AppCompatActivity {
    EditText txtLocation;
    EditText txtDesc;
    ImageButton btnAddImg;
    ImageButton btnRetakeImg;
    Button btnCanel;
    Button btnCreate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("New Pickup Request");
        setContentView(R.layout.activity_pr_create);
        setControls();
        txtLocation = findViewById(R.id.pr_create_txtLocation);

        //Inititalize places
        Places.initialize(getApplicationContext(),"AIzaSyBPWqoOuPUoqu0EZjszT1I_0g23D9MZrnw");

        txtLocation.setFocusable(false);
        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializer place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG,Place.Field.NAME);

                // Create intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,fieldList).build(PrCreateActivity.this);

                //start activity result
                startActivityForResult(intent,100);
            }
        });

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btnRetakeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInputValid()){
                    BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    PickupRequest newPr = new PickupRequest(U.getFromTxtbox(txtLocation),U.getFromTxtbox(txtDesc), bitmap);
                    Context.prList.add(newPr);
                }
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, 102);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(),"Camera not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void getImageFile(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 101);
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

        // pick img from directory
        if (resultCode == RESULT_OK && requestCode == 101) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
                imageView.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(PrCreateActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }

        // retrieve img from camera
        if (requestCode == 102 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            imageView.setVisibility(View.VISIBLE);
            btnAddImg.setVisibility(View.GONE);
            btnRetakeImg.setVisibility(View.VISIBLE);
        }
    }

    private void setControls(){
        txtLocation = findViewById(R.id.pr_create_txtLocation);
        txtDesc = findViewById(R.id.pr_create_txtDesc);
        btnAddImg = findViewById(R.id.pr_create_btnAddImg);
        btnRetakeImg = findViewById(R.id.pr_create_btnRetakeImg);
        btnCanel = findViewById(R.id.pr_create_btnCancel);
        btnCreate = findViewById(R.id.pr_create_btnCreate);
        imageView = findViewById(R.id.pr_create_imgItem);
    }

    private boolean isInputValid(){
        return !(U.IsNullOrEmpty(U.getFromTxtbox(txtDesc)) && U.IsNullOrEmpty(U.getFromTxtbox(txtLocation)));
    }

}