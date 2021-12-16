package nc.prog1415.controllers.claimer_view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import models.Context;
import nc.prog1415.R;
import nc.prog1415.controllers.claimer_view.fragments.PrClaimedListFragment;
import nc.prog1415.controllers.claimer_view.fragments.PrFulfilledListFragment;
import nc.prog1415.controllers.claimer_view.fragments.PrOpenListFragment;

public class ClaimerViewActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claimer_view);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PrOpenListFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.openRequests:
                    selectedFragment = new PrOpenListFragment();
                    break;
                case R.id.claims:
                    selectedFragment = new PrClaimedListFragment();
                    break;
                case R.id.fullfiled:
                    selectedFragment = new PrFulfilledListFragment();
                    break;
            }

            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }
    };



}