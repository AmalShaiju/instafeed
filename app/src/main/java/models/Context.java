package models;

import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Context {
    public  static User loggedInUser;

    public  static ArrayList<User> userList = new ArrayList<User>();

    public  static ArrayList<PickupRequest> prList = new ArrayList<PickupRequest>();

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
        userToLogin = userList.stream().filter(p -> p.getEmail().equals(email) && p.getPassword() == password).findFirst().orElse(null);
        if(userToLogin != null){
            loggedInUser = userToLogin;
            return true;
        }
        return false;
    }

    public static void Logout(){
        loggedInUser = null;
    }


    public static void seedData() throws Exception {
        User claimer = new User("Saf","Shaiju","amalshaiju16@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy", UserType.CLAIMER);
        User donor = new User("Amal","Shaiju","amalshaiju16@gmail.com","2898230814","1222 Natanial Crescent", "Wonderboy", UserType.DONOR);
        PickupRequest p3 = new PickupRequest(donor,"test Address","Test item", LocalDateTime.now(),new ArrayList<byte[]>());
        PickupRequest p1 = new PickupRequest(donor,"1222 Natanial Crescent","Table", LocalDateTime.now(),new ArrayList<byte[]>());
        PickupRequest p2 = new PickupRequest(donor,"516 First Ave","Chair", LocalDateTime.now(),new ArrayList<byte[]>());

        p2.claimRequest(claimer);
        p3.claimRequest(claimer);
        p2.pickUp();
        p3.pickUp();
        prList.add(p1);
        prList.add(p2);
        prList.add(p3);

        loggedInUser = claimer;
    }
}
