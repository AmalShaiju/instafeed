package nc.prog1415.controllers.donor_view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.PickupRequest;
import models.User;
import models.UserType;
import nc.prog1415.R;
import nc.prog1415.controllers.donor_view.activities.recycler_adapters.DonorViewRecycleAdapter;

public class DonorViewActivity extends AppCompatActivity {
    Button btnCreateRequest;
    private ArrayList<PickupRequest> prList;
    private RecyclerView donorPrRecyclerView;

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

        prList = new ArrayList();
        donorPrRecyclerView = findViewById(R.id.donor_pr_recyView);
        try {
            seedData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAdapter();
    }

    private void setAdapter() {
        DonorViewRecycleAdapter adapter = new DonorViewRecycleAdapter(prList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        // add space between list items
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(openPrRecyclerView.getContext(), layoutManager.getOrientation());
        // openPrRecyclerView.addItemDecoration(dividerItemDecoration);

        donorPrRecyclerView.setLayoutManager(layoutManager);
        donorPrRecyclerView.setItemAnimator(new DefaultItemAnimator());
        donorPrRecyclerView.setAdapter(adapter);
    }

    private void seedData() throws Exception {
        User claimer = new User("Saf","Shaiju","amalshaiju16@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy", UserType.DONOR);
        User donor = new User("Amal","Shaiju","amalshaiju16@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy", UserType.CLAIMER);
        PickupRequest p3 = new PickupRequest(donor,"test Address","Test item", LocalDateTime.now(),new ArrayList<byte[]>());
        PickupRequest p1 = new PickupRequest(donor,"1222 Natanial Crescent","Table", LocalDateTime.now(),new ArrayList<byte[]>());
        PickupRequest p2 = new PickupRequest(donor,"516 First Ave","Chair", LocalDateTime.now(),new ArrayList<byte[]>());

        p2.claimRequest(claimer);
        p3.claimRequest(claimer);
        p3.pickUp();
        prList.add(p1);
        prList.add(p2);
        prList.add(p3);
    }
}