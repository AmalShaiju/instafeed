package nc.prog1415.activities.donor_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nc.prog1415.R;

public class DonorViewActivity extends AppCompatActivity {
    Button btnCreateRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_view);
        btnCreateRequest = findViewById(R.id.btnCreateRequest);


        btnCreateRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonorViewActivity.this, PrCreateActivity.class);
                startActivity(intent);
            }
        });
    }
}