package nc.prog1415.activities.claimer_view.recycler_adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        holder.lblLocation.setText(claimedPrList.get(position).getLocation());
        holder.lblPostedOn.setText(claimedPrList.get(position).getDatePosted().toString());
        holder.lblClaimedOn.setText(claimedPrList.get(position).getClaimedOn() == null ? "Not claimed" : claimedPrList.get(position).getClaimedOn().toString());
        holder.lblUsername.setText(claimedPrList.get(position).getPostedBy().getFullName());

        //Bitmap itemImgBitMap = U.byteToBitMap(openPrList.get(position).getImages().get(0));
        //ImageView image = (ImageView) holder.itemView.findViewById(R.id.imgItem);
        // image.setImageBitmap(Bitmap.createScaledBitmap(itemImgBitMap, image.getWidth(), image.getHeight(), false));
    }

    @Override
    public int getItemCount() {
        return claimedPrList.size();
    }
}
