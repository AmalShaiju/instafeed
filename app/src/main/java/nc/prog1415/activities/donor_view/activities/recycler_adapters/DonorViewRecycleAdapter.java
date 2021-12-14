package nc.prog1415.activities.donor_view.activities.recycler_adapters;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import models.PickupRequest;
import models.U;
import nc.prog1415.R;

public class DonorViewRecycleAdapter  extends RecyclerView.Adapter<DonorViewRecycleAdapter.MyViewHolder>{
    private ArrayList<PickupRequest> prList;

    public DonorViewRecycleAdapter(ArrayList<PickupRequest> prList){
        this.prList = prList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView lblLocation;
        private TextView lblPostedOn;
        private TextView lblClaimedOn;
        private TextView lblPickedUpOn;
        private TextView lblClaimedBy;

        private TextView plcPostedOn;
        private TextView plcClaimedOn;
        private TextView plcPickedUpOn;
        private TextView plcClaimedBy;
        private ImageView imgItem;

        public MyViewHolder(final View view){
            super(view);
            lblLocation = view.findViewById(R.id.donor_pr_lblLocation);
            lblPostedOn = view.findViewById(R.id.donor_pr_lblPostedOn);
            lblClaimedOn = view.findViewById(R.id.donor_pr_lblClaimedOn);
            lblPickedUpOn = view.findViewById(R.id.donor_pr_lblPickedUpOn);
            lblClaimedBy = view.findViewById(R.id.donor_pr_lblClaimedBy);

            plcPostedOn = view.findViewById(R.id.donor_pr_plcPostedOn);
            plcClaimedOn = view.findViewById(R.id.donor_pr_plcClaimedOn);
            plcPickedUpOn = view.findViewById(R.id.donor_pr_plcPickedUpOn);
            plcClaimedBy = view.findViewById(R.id.donor_pr_plcClaimedBy);

            imgItem = view.findViewById(R.id.pr_open_imgItem);
        }
    }

    @NonNull
    @Override
    public DonorViewRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_donor_pr,parent,false);
        return new DonorViewRecycleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorViewRecycleAdapter.MyViewHolder holder, int position) {
        PickupRequest pr = prList.get(position);

         if(pr.isClaimed()){
            holder.lblClaimedOn.setText(U.ToDateString(pr.getClaimedOn()));
            holder.lblClaimedBy.setText(pr.getClaimedBy().getOrganiztionName());
            holder.lblClaimedBy.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.warning));
        }else{
            holder.lblClaimedOn.setVisibility(View.GONE);
            holder.lblClaimedBy.setVisibility(View.GONE);
            holder.plcClaimedOn.setVisibility(View.GONE);
            holder.plcClaimedBy.setVisibility(View.GONE);
        }

        if(pr.isPickedUp()){
            holder.lblPickedUpOn.setText(U.ToDateString(pr.getPickedupOn()));
            holder.lblClaimedBy.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.success));
        }else{
            holder.lblPickedUpOn.setVisibility(View.GONE);
            holder.plcPickedUpOn.setVisibility(View.GONE);
        }




        holder.lblLocation.setText(pr.getLocation());
        holder.lblPostedOn.setText(U.ToDateString(prList.get(position).getDatePosted()));


        //Bitmap itemImgBitMap = U.byteToBitMap(openPrList.get(position).getImages().get(0));
        //ImageView image = (ImageView) holder.itemView.findViewById(R.id.imgItem);
        // image.setImageBitmap(Bitmap.createScaledBitmap(itemImgBitMap, image.getWidth(), image.getHeight(), false));
    }

    @Override
    public int getItemCount() {
        return prList.size();
    }
}

