package naveen.agnosbeta.agnos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting text
            getter_setter.setFirst_name(null);
            getter_setter.setLast_name(null);
            getter_setter.setAge(null);
            getter_setter.setContact_no(null);
            getter_setter.setEmail(null);
            getter_setter.setCom_name(null);
            getter_setter.setOffice_contact_no(null);
            getter_setter.setOffice_email(null);
            getter_setter.setOffice_add(null);
            getter_setter.setCsi_member_s(null);
            getter_setter.setService_type(null);
            getter_setter.setDescription(null);
            getter_setter.setuser_profile_link(null);
            database_handler.set_user_image(null);
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(logout.this, GoogleSignInActivity.class);
        startActivity(intent);
        finish();
    }

}

