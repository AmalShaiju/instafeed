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
        return prList.stream().filter(p -> p.isClaimed() && p.getClaimedBy().equals(loggedInUser)).collect(Collectors.toCollection(ArrayList::new));
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


    public static void seedData() throws Exception {
        if(userList == null){
            userList = new ArrayList<User>();
        }

        if(prList == null){
            prList = new ArrayList<PickupRequest>();
        }

        User claimer = new User("Claimer","Adrin","Claimer@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy", "a", UserType.CLAIMER);
        User donor = new User("Donor","Roshan","Donor@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy","a", UserType.DONOR);
        PickupRequest p3 = new PickupRequest("test Address","Test item",null);
        PickupRequest p1 = new PickupRequest("1222 Natanial Crescent","Table",null);
        PickupRequest p2 = new PickupRequest("516 First Ave","Chair",null);

        p2.claimRequest(claimer);
        p3.claimRequest(claimer);
        p2.pickUp();
        p3.pickUp();
        prList.add(p1);
        prList.add(p2);
        prList.add(p3);

        userList.add(claimer);
        userList.add(donor);
    }
}
