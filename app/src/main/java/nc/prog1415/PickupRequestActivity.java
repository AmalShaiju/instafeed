package nc.prog1415;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PickupRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("New Pickup Request");
        setContentView(R.layout.activity_pickup_request);
    }
}