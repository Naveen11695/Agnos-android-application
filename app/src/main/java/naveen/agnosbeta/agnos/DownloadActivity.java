package naveen.agnosbeta.agnos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

public class DownloadActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    database_handler obj=new database_handler();
    private FirebaseFirestore Firebasefirestore;
    private database_handler data=new database_handler();
    Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://agnos-b3bc8.appspot.com").child(obj.email_action_bar());

        //get download file url
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("Main", "File uri: " + uri.toString());
            }
        });

        //download the file
        try {
            showProgressDialog("AgNos Developers", "Loading Data...");
            final File localFile = File.createTempFile("images", "jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    //database_handler obj=new database_handler();
                    database_handler.set_user_image(bitmap);
                    dismissProgressDialog();
                    //showToast("Download successful!");
                    Intent intent=new Intent(DownloadActivity.this,Main_screen_with_navigation.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "value_download");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    dismissProgressDialog();
                    //showToast("Download Failed!");
                    startActivity(new Intent(DownloadActivity.this,Main_screen_with_navigation.class));
                    finish();
                }
            });
        } catch (IOException e ) {
            e.printStackTrace();
            Log.e("Main", "IOE Exception");
        }

        read_firestore();
    }

    private void read_firestore() {
            Firebasefirestore = FirebaseFirestore.getInstance();
                DocumentReference doc = Firebasefirestore.collection("Agnos Data").document(data.email_action_bar());
                doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot data = task.getResult();
                        if(data.exists())
                        {
                           //Toast.makeText(getApplicationContext(),"Exist",Toast.LENGTH_SHORT).show();
                           getter_setter.setFirst_name(data.getString("01_First name"));
                            getter_setter.setLast_name(data.getString("02_last name"));
                            getter_setter.setAge(data.getString("03_Age"));
                            getter_setter.setContact_no(data.getString("04_Contact_no"));
                            getter_setter.setEmail(data.getString("05_Email id"));
                            getter_setter.setCom_name(data.getString("06_Company Name"));
                            getter_setter.setOffice_contact_no(data.getString("07_office Contact no"));
                            getter_setter.setOffice_email(data.getString("08_office Email id"));
                            getter_setter.setOffice_add(data.getString("09_office Address"));
                            getter_setter.setCsi_member_s(data.getString("10_CSI Membership"));
                            getter_setter.setService_type(data.getString("11_Project Type"));
                            getter_setter.setDescription(data.getString("12_Project Description"));
                            getter_setter.setuser_profile_link(data.getString("13_User Profile"));
                        }
                        else
                        {
                            //Toast.makeText(getApplicationContext(),"doesn't Exist",Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                            Log.i("fireStore","Reading data from firestore Fail");
                    }
                });
            }



    protected void showProgressDialog(String title, String msg) {
        progressDialog = ProgressDialog.show(this, title, msg, true);
    }


    protected void dismissProgressDialog() {
        progressDialog.dismiss();
    }
}
