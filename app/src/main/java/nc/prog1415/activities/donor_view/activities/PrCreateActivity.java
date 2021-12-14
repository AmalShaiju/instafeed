package nc.prog1415.activities.donor_view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import nc.prog1415.R;

public class PrCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("New Pickup Request");
        setContentView(R.layout.activity_pr_create);
    }
}