package naveen.agnosbeta.agnos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class firebase_firestore extends AppCompatActivity {

    private FirebaseFirestore Firebasefirestore;
    private database_handler data=new database_handler();
    private static String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebasefirestore= FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        Map<String, Object> newData = new HashMap<>();
            if (bundle != null) {
                String value = bundle.getString("key");
                if (value.equalsIgnoreCase("value_register")) {
                    //Add new Contact to Address book
                    newData.put("01_First name", bundle.getString("first_name"));
                    newData.put("02_last name", bundle.getString("last_name"));
                    newData.put("03_Age", bundle.getString("age"));
                    newData.put("04_Contact_no", bundle.getString("contact_no"));
                    newData.put("05_Email id", bundle.getString("email"));
                    newData.put("06_Company Name", bundle.getString("com_name"));
                    newData.put("07_office Contact no", bundle.getString("office_contact_no"));
                    newData.put("08_office Email id", bundle.getString("office_email"));
                    newData.put("09_office Address", bundle.getString("office_add_s"));
                    newData.put("10_CSI Membership", bundle.getString("csi_member_s"));
                    newData.put("11_Project Type",getter_setter.getService_type());
                    newData.put("12_Project Description",getter_setter.getDescription());
                    newData.put("13_User Profile",getter_setter.getuser_profile_link());
                    Firebasefirestore.collection("Agnos Data").document(data.email_action_bar())
                            .set(newData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent intent = new Intent(firebase_firestore.this, DownloadActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("key", "value_register");
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Log.i("firebase_firestore", "could not insert data..");
                                }
                            });
                }

                else if (value.equalsIgnoreCase("value_services"))  {
                    DocumentReference contact =Firebasefirestore.collection("Agnos Data").document(data.email_action_bar());
                    contact.update("11_Project Type", bundle.getString("Project_Type"));
                    contact.update("12_Project Description", bundle.getString("Project_Description")).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.i("firestore update","Success");
                            Intent intent = new Intent(firebase_firestore.this, DownloadActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("key", "value_register");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("firestore update","fail");
                            Intent intent = new Intent(firebase_firestore.this, DownloadActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("key", "value_register");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                    });
                }

                else if (value.equalsIgnoreCase("user_profile_link"))  {
                    DocumentReference contact =Firebasefirestore.collection("Agnos Data").document(data.email_action_bar());
                    getter_setter.setuser_profile_link(bundle.getString("downloadUrl"));
                    contact.update("13_User Profile", bundle.getString("downloadUrl")).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.i("firestore update","Success");
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("firestore update","fail");
                            finish();
                        }
                    });
                }

            }

    }

}

