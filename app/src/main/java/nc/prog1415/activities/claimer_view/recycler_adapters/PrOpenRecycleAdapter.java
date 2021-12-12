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
        holder.lblLocation.setText(openPrList.get(position).getLocation());
        holder.lblDate.setText(openPrList.get(position).getDatePosted().toString());
        holder.lblUser.setText(openPrList.get(position).getPostedBy().getFullName());
        holder.lblDescription.setText(openPrList.get(position).getDescription());

       //Bitmap itemImgBitMap = U.byteToBitMap(openPrList.get(position).getImages().get(0));
       //ImageView image = (ImageView) holder.itemView.findViewById(R.id.imgItem);
        // image.setImageBitmap(Bitmap.createScaledBitmap(itemImgBitMap, image.getWidth(), image.getHeight(), false));
    }

    @Override
    public int getItemCount() {
        return openPrList.size();
    }
}
