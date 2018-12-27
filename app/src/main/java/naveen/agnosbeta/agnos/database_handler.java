package naveen.agnosbeta.agnos;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by naVeen on 02-10-2017.
 */

class database_handler {
    private static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static FirebaseUser user;
    private String email;
    private static Bitmap u_img;
    public String email_action_bar()
    {
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // email address
            email = user.getEmail();
        }
        return email;
    }

    public static void set_user_image(Bitmap user_img)
    {
        u_img=user_img;
    }

    public static Bitmap get_user_image()
    {
        return u_img;
    }


}
