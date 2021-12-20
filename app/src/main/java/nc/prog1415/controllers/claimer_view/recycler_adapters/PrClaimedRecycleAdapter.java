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
import models.U;

import models.PickupRequest;
import nc.prog1415.R;

public class PrClaimedRecycleAdapter extends RecyclerView.Adapter<PrClaimedRecycleAdapter.MyViewHolder> {

    private ArrayList<PickupRequest> claimedPrList;

    public PrClaimedRecycleAdapter(ArrayList<PickupRequest> claimedPrList){
        this.claimedPrList = claimedPrList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        // declare labels
        private TextView lblLocation;
        private TextView lblPostedOn;
        private TextView lblClaimedOn;
        private TextView lblUsername;
        private ImageView imgItem;
        private Button btnDropClaim;
        private Button btnPickUp;

        public MyViewHolder(final View view){
            super(view);
            lblLocation = view.findViewById(R.id.pr_claimed_lblLocation);
            lblPostedOn = view.findViewById(R.id.pr_claimed_lblPostedOn);
            lblClaimedOn = view.findViewById(R.id.pr_claimed_lblClaimedOn);
            lblUsername = view.findViewById(R.id.pr_claimed_lblUsername);
            imgItem = view.findViewById(R.id.pr_claimed_imgItem);
            btnDropClaim = view.findViewById(R.id.pr_claimed_btnDropClaim);
            btnPickUp = view.findViewById(R.id.pr_claimed_btnPickedup);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pr_claimed,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PickupRequest pr = claimedPrList.get(position);
        holder.lblLocation.setText(pr.getLocation());
        holder.lblPostedOn.setText(U.ToDateString(pr.getDatePosted()));
        holder.lblClaimedOn.setText(U.ToDateString(pr.getClaimedOn()));
        holder.lblUsername.setText(pr.getPostedBy().getFullName());
        if(pr.getImage() != null){
            holder.imgItem.setImageBitmap(pr.getImage());
        }
        holder.btnPickUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Context.PickupRequest(pr);
                    Toast.makeText(holder.itemView.getContext(), "Request Fulfilled", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(holder.itemView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btnDropClaim.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Context.DropRequest(pr);
                    Toast.makeText(holder.itemView.getContext(), "Dropped Claim", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(holder.itemView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return claimedPrList.size();
    }
}
