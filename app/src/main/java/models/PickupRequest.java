package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PickupRequest {
    // on init
    private User postedBy;
    private String location;
    private String description;
    private LocalDateTime datePosted;
    private ArrayList<byte[]> images;

    // once claimed
    private User claimedBy;
    private LocalDateTime claimedOn;
    private LocalDateTime pickedupOn;

    public PickupRequest(User postedBy, String location, String description, LocalDateTime datePosted, ArrayList<byte[]> images) {
        this.postedBy = postedBy;
        this.location = location;
        this.datePosted = datePosted;
        this.images = images;
        this.description = description;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() { return description; }

    public List<byte[]> getImages() {
        return images;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public LocalDateTime getClaimedOn() {   return claimedOn; }

    public LocalDateTime getPickedupOn() {
        return pickedupOn;
    }

    public User getClaimedBy(){return  claimedBy;}

    public void setPostedBy(User postedBy) {
        if (postedBy != null) {
            this.postedBy = postedBy;
        } else {
            throw new NullPointerException("user provided is null");
        }
    }

    public void setLocation(String location) {
        if (!U.IsNullOrEmpty(location)) {
            this.location = location;
        } else {
            throw new NullPointerException("location provided is null");
        }
    }

    public void setDescription(String description) {
        if (!U.IsNullOrEmpty(description)) {
            this.description = description;
        } else {
            throw new NullPointerException("location provided is null");
        }
    }

    public void setDatePosted(LocalDateTime datePosted) {
        if (datePosted != null) {
            this.datePosted = datePosted;
        } else {
            throw new NullPointerException("location provided is null");
        }
    }

    public void setImages(ArrayList<byte[]> images) {
        if (images != null) {
            this.images = images;
        } else {
            throw new NullPointerException("image provided is null");
        }
    }

    public void claimRequest(User claimer) throws Exception {
        if (!isClaimed() && claimer != postedBy) {
            claimedBy = claimer;
            claimedOn = LocalDateTime.now();
        } else {
            throw new Exception("request already claimed");
        }
    }

    public void dropClaim(User dropper) throws Exception {
        if (claimedBy.equals(dropper) && !isPickedUp()) { // request can only be dropped by claimer
            claimedBy = null;
            claimedOn = null;
        } else {
            throw new Exception("request can only be dropped by claimer");
        }
    }

    public void pickUp() {
        pickedupOn = LocalDateTime.now();
    }

    public boolean isClaimed() {
        return claimedBy != null;
    }

    public boolean isPickedUp() {
        return pickedupOn != null;
    }



}
