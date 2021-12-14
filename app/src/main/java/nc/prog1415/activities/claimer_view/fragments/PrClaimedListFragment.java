package nc.prog1415.activities.claimer_view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.PickupRequest;
import models.User;
import models.UserType;
import nc.prog1415.R;
import nc.prog1415.activities.claimer_view.recycler_adapters.PrClaimedRecycleAdapter;
import nc.prog1415.activities.claimer_view.recycler_adapters.PrOpenRecycleAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrClaimedListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrClaimedListFragment extends Fragment {
    private ArrayList<PickupRequest> claimedPrList;
    private RecyclerView claimedPrRecyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrClaimedListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClaimsListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrClaimedListFragment newInstance(String param1, String param2) {
        PrClaimedListFragment fragment = new PrClaimedListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pr_claimed_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        claimedPrList = new ArrayList();
        claimedPrRecyclerView = view.findViewById(R.id.pr_claimed_recyView);
        try {
            seedData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAdapter();
    }

    private void setAdapter() {
        PrClaimedRecycleAdapter adapter = new PrClaimedRecycleAdapter(claimedPrList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        // add space between list items
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(openPrRecyclerView.getContext(), layoutManager.getOrientation());
        // openPrRecyclerView.addItemDecoration(dividerItemDecoration);

        claimedPrRecyclerView.setLayoutManager(layoutManager);
        claimedPrRecyclerView.setItemAnimator(new DefaultItemAnimator());
        claimedPrRecyclerView.setAdapter(adapter);
    }

    private void seedData() throws Exception {
        User claimer = new User("Saf","Shaiju","amalshaiju16@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy", UserType.INDIVIDUAL);
        User donor = new User("Amal","Shaiju","amalshaiju16@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy", UserType.INDIVIDUAL);
        PickupRequest p3 = new PickupRequest(donor,"test Address","Test item", LocalDateTime.now(),new ArrayList<byte[]>());
        PickupRequest p1 = new PickupRequest(donor,"1222 Natanial Crescent","Table", LocalDateTime.now(),new ArrayList<byte[]>());
        PickupRequest p2 = new PickupRequest(donor,"516 First Ave","Chair", LocalDateTime.now(),new ArrayList<byte[]>());

        p1.claimRequest(claimer);
        p2.claimRequest(claimer);
        p3.claimRequest(claimer);
        claimedPrList.add(p1);
        claimedPrList.add(p2);
        claimedPrList.add(p3);
    }
}