package nc.prog1415;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import models.Context;
import models.User;
import models.UserType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();
    }

    public  void config(){
        findViewById(android.R.id.content).setTransitionName("login");
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());

        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(250);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);

    }

}