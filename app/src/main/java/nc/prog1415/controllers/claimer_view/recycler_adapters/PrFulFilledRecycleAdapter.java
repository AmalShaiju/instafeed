package nc.prog1415.controllers.claimer_view.recycler_adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import models.U;

import models.PickupRequest;
import nc.prog1415.R;

public class PrFulFilledRecycleAdapter extends RecyclerView.Adapter<PrFulFilledRecycleAdapter.MyViewHolder> {

    private ArrayList<PickupRequest> fulFilledPrList;

    public PrFulFilledRecycleAdapter(ArrayList<PickupRequest> fulFilledPrList){
        this.fulFilledPrList = fulFilledPrList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        // declare labels
        private TextView lblLocation;
        private TextView lblPostedOn;
        private TextView lblClaimedOn;
        private TextView lblPickedUpOn;
        private TextView lblUsername;
        private ImageView imgItem;


        public MyViewHolder(final View view){
            super(view);
            lblLocation = view.findViewById(R.id.pr_fulfilled_lblLocation);
            lblPostedOn = view.findViewById(R.id.pr_fulfilled_lblPostedOn);
            lblClaimedOn = view.findViewById(R.id.pr_fulfilled_lblClaimedOn);
            lblPickedUpOn = view.findViewById(R.id.pr_fulfilled_lblPickedUpOn);
            lblUsername = view.findViewById(R.id.pr_fulfilled_lblUsername);
            imgItem = view.findViewById(R.id.pr_fulfilled_imgItem);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pr_fulfilled,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lblLocation.setText(fulFilledPrList.get(position).getLocation());
        holder.lblPostedOn.setText(U.ToDateString(fulFilledPrList.get(position).getDatePosted()));
        holder.lblClaimedOn.setText(U.ToDateString(fulFilledPrList.get(position).getClaimedOn()));
        holder.lblPickedUpOn.setText(U.ToDateString(fulFilledPrList.get(position).getPickedupOn()));
        holder.lblUsername.setText(fulFilledPrList.get(position).getPostedBy().getFullName());

        //Bitmap itemImgBitMap = U.byteToBitMap(openPrList.get(position).getImages().get(0));
        //ImageView image = (ImageView) holder.itemView.findViewById(R.id.imgItem);
        // image.setImageBitmap(Bitmap.createScaledBitmap(itemImgBitMap, image.getWidth(), image.getHeight(), false));
    }

    @Override
    public int getItemCount() {
        return fulFilledPrList.size();
    }
}