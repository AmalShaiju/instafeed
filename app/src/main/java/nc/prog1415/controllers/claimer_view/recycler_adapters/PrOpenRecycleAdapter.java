package nc.prog1415.controllers.claimer_view.recycler_adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import models.Context;
import models.PickupRequest;
import nc.prog1415.R;

public class PrOpenRecycleAdapter  extends RecyclerView.Adapter<PrOpenRecycleAdapter.MyViewHolder>{
    private ArrayList<PickupRequest> openPrList;

    public PrOpenRecycleAdapter(ArrayList<PickupRequest> openPrList){
        this.openPrList = openPrList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        // declare labels
        private TextView lblLocation;
        private TextView lblDate;
        private TextView lblUser;
        private TextView lblDescription;
        private ImageView imgItem;
        private Button btnClaim;

        public MyViewHolder(final View view){
            super(view);
            lblLocation = view.findViewById(R.id.pr_open_lblLoction);
            lblDate = view.findViewById(R.id.pr_open_lblDate);
            lblUser = view.findViewById(R.id.pr_open_lblUser);
            lblDescription = view.findViewById(R.id.pr_open_lblDescription);
            imgItem = view.findViewById(R.id.pr_open_imgItem);
            btnClaim = view.findViewById(R.id.pr_open_btnClaim);



        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pr_open,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PickupRequest pr = openPrList.get(position);
        holder.lblLocation.setText(pr.getLocation());
        holder.lblDate.setText(pr.getDatePosted().toString());
        holder.lblUser.setText(pr.getPostedBy().getFullName());
        holder.lblDescription.setText(pr.getDescription());
        holder.btnClaim.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Context.ClaimRequest(pr);
                    Toast.makeText(holder.itemView.getContext(), "Request Claimed", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(holder.itemView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return openPrList.size();
    }
}
