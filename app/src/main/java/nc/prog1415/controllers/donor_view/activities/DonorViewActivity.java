package nc.prog1415.controllers.donor_view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.Context;
import models.PickupRequest;
import models.User;
import models.UserType;
import nc.prog1415.R;
import nc.prog1415.controllers.donor_view.activities.recycler_adapters.DonorViewRecycleAdapter;

public class DonorViewActivity extends AppCompatActivity {
    Button btnCreateRequest;
    private RecyclerView donorPrRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_view);
        btnCreateRequest = findViewById(R.id.btnCreateRequest);

        if(!Context.getPostedByMe().isEmpty()){
           final TextView lblError = findViewById(R.id.donor_pr_lblError);
           lblError.setVisibility(View.GONE);
        }

        btnCreateRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonorViewActivity.this, PrCreateActivity.class);
                startActivity(intent);
            }
        });

        donorPrRecyclerView = findViewById(R.id.donor_pr_recyView);
        setAdapter();
    }

    private void setAdapter() {
        DonorViewRecycleAdapter adapter = new DonorViewRecycleAdapter(Context.getPostedByMe());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        donorPrRecyclerView.setLayoutManager(layoutManager);
        donorPrRecyclerView.setItemAnimator(new DefaultItemAnimator());
        donorPrRecyclerView.setAdapter(adapter);
    }


}