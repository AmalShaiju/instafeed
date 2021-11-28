package models;

import java.time.LocalDateTime;
import java.util.List;

public class PickupRequest {
    // on init
    private User postedBy;
    private String location;
    private String description;
    private LocalDateTime datePosted;
    private List<Byte[]> images;

    // once claimed
    private User claimedBy;
    private LocalDateTime dateClaimed;
    private LocalDateTime datePickedUp;

    public PickupRequest(User postedBy, String location, String description, LocalDateTime datePosted, List<Byte[]> images) {
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

    public List<Byte[]> getImages() {
        return images;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setPostedBy(User postedBy) {
        if (postedBy != null) {
            this.postedBy = postedBy;
        } else {
            throw new NullPointerException("user provided is null");
        }
    }

    public void setLocation(String location) {
        if (!U.isNullOrEmpty(location)) {
            this.location = location;
        } else {
            throw new NullPointerException("location provided is null");
        }
    }

    public void setDescription(String description) {
        if (!U.isNullOrEmpty(description)) {
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

    public void setImages(List<Byte[]> images) {
        if (images != null) {
            this.images = images;
        } else {
            throw new NullPointerException("image provided is null");
        }
    }

    public void claimRequest(User claimer) throws Exception {
        if (!isClaimed() && claimer != postedBy) {
            claimedBy = claimer;
            dateClaimed = LocalDateTime.now();
        } else {
            throw new Exception("request already claimed");
        }
    }

    public void dropClaim(User dropper) throws Exception {
        if (claimedBy.equals(dropper) && !isPickedUp()) { // request can only be dropped by claimer
            claimedBy = null;
            dateClaimed = null;
        } else {
            throw new Exception("request can only be dropped by claimer");
        }
    }

    public void pickUp() {
        datePickedUp = LocalDateTime.now();
    }

    public boolean isClaimed() {
        return claimedBy != null;
    }

    public boolean isPickedUp() {
        return datePickedUp != null;
    }

}
