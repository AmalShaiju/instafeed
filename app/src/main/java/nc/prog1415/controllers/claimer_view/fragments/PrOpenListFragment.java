package nc.prog1415.controllers.claimer_view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.Context;
import models.PickupRequest;
import models.User;
import models.UserType;
import nc.prog1415.R;
import nc.prog1415.controllers.claimer_view.recycler_adapters.PrOpenRecycleAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrOpenListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrOpenListFragment extends Fragment {
    private RecyclerView openPrRecyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrOpenListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PickupRequestListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrOpenListFragment newInstance(String param1, String param2) {
        PrOpenListFragment fragment = new PrOpenListFragment();
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
        return inflater.inflate(R.layout.fragment_pr_open_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(!Context.getAllOpenPr().isEmpty()){
            final TextView lblError = view.findViewById(R.id.pr_open_lblError);
            lblError.setVisibility(View.GONE);
        }
        openPrRecyclerView = view.findViewById(R.id.pr_claimed_recyView);
        setAdapter();
    }

    private void setAdapter() {
        PrOpenRecycleAdapter adapter = new PrOpenRecycleAdapter(Context.getAllOpenPr());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        openPrRecyclerView.setLayoutManager(layoutManager);
        openPrRecyclerView.setItemAnimator(new DefaultItemAnimator());
        openPrRecyclerView.setAdapter(adapter);
    }

}