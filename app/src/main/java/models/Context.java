package models;

import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Context {
    public static User loggedInUser;

    public static ArrayList<User> userList;

    public static ArrayList<PickupRequest> prList;

    public static ArrayList<PickupRequest> getClaimedByMe() {
        return prList.stream().filter(p -> p.isClaimed() && !p.isPickedUp() && p.getClaimedBy().equals(loggedInUser)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<PickupRequest> getPickedupByMe(){
        return prList.stream().filter(p -> p.isPickedUp() && p.getClaimedBy().equals(loggedInUser)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<PickupRequest> getPostedByMe(){
        return prList.stream().filter(p -> p.getPostedBy().equals(loggedInUser)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<PickupRequest> getAllOpenPr(){
        return prList.stream().filter(p -> !p.isClaimed()).collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean Login(String email, String password){
        User userToLogin;
        userToLogin = userList.stream().filter(p -> p.getEmail().toLowerCase().equals(email.toLowerCase()) && p.getPassword().equals(password)).findFirst().orElse(null);
        if(userToLogin != null){
            loggedInUser = userToLogin;
            return true;
        }
        return false;
    }

    public static boolean RegisterUser(User userToRegister){
         boolean userExists = userList.contains(userToRegister);
         if(!userExists){
             userList.add(userToRegister);
             return true;
         }
         return false;
    }


    public static void Logout(){
        loggedInUser = null;
    }

    public static void CreateNewPr(PickupRequest prToAdd){
        prList.add(prToAdd);
    }

    public static boolean ClaimRequest(PickupRequest pr) throws Exception {
        pr.claimRequest(loggedInUser);
        return true;
    }

    public static boolean PickupRequest(PickupRequest pr){
        pr.pickUp();
        return true;
    }

    public static boolean DropRequest(PickupRequest pr) throws Exception {
        pr.dropClaim(loggedInUser);
        return true;
    }



    public static void seedData() throws Exception {
        if(userList == null){
            userList = new ArrayList<User>();
        }

        if(prList == null){
            prList = new ArrayList<PickupRequest>();
        }

        User claimer = new User("Claimer","","Claimer@gmail.com","2898230814","516 first ave", "YMCA", "a", UserType.CLAIMER);
        User donor = new User("Donor","","Donor@gmail.com","2898230814","1222 natanial crescent", "","a", UserType.DONOR);
        PickupRequest p3 = new PickupRequest("Address 1","Test item 1", donor,null);
        PickupRequest p1 = new PickupRequest("Address 2","Test item 2",donor,null);
        PickupRequest p2 = new PickupRequest("Address 3","Test item 3",donor,null);

        p2.claimRequest(claimer);
        p3.claimRequest(claimer);
        p3.pickUp();
        prList.add(p1);
        prList.add(p2);
        prList.add(p3);

        userList.add(claimer);
        userList.add(donor);
    }
}
