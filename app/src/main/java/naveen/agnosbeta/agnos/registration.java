package naveen.agnosbeta.agnos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class registration extends AppCompatActivity {

    private ImageView imageView;
    private database_handler obj;
    private Bitmap bitmap_reg=null,bitmap_reg_new;
    private Button save;
    private EditText first_name,last_name,age,contact_no,email,com_name,office_contact_no,office_email,office_add;
    private int radio_id;
    private String first_name_s,last_name_s,age_s,contact_no_s,email_s,com_name_s,office_contact_no_s,office_email_s,office_add_s,csi_member_s;
    private RadioGroup radioGroup;
    private RadioButton r_button;
    private View mProgressView;
    private View mregistrationFormView;
    private View focusView = null;
    private Handler mHandler = new Handler();
    private static final int SELECT_PHOTO = 100;
    protected static Uri selectedimage_uri;
    private Bitmap bitmap;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        imageView=(ImageView)findViewById(R.id.nav_user_img_reg);
        obj=new database_handler();
        //Register Edittext..

        first_name=(EditText)findViewById(R.id.F_name);
        last_name=(EditText)findViewById(R.id.L_name);
        age=(EditText)findViewById(R.id.Age);
        contact_no=(EditText)findViewById(R.id.contact);
        email=(EditText)findViewById(R.id.email);
        com_name=(EditText)findViewById(R.id.com_name);
        office_contact_no=(EditText)findViewById(R.id.office_ph);
        office_email=(EditText)findViewById(R.id.office_email);
        office_add=(EditText)findViewById(R.id.office_add);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        mProgressView = findViewById(R.id.reg_progress);
        mregistrationFormView=findViewById(R.id.registration_form);


        //setting text
        email.setText(obj.email_action_bar());
        first_name.setText(getter_setter.getFirst_name());
        last_name.setText(getter_setter.getLast_name());
        age.setText(getter_setter.getAge());
        contact_no.setText(getter_setter.getContact_no());
        //email.setText(getter_setter.getEmail());
        com_name.setText(getter_setter.getCom_name());
        office_contact_no.setText(getter_setter.getOffice_contact_no());
        office_email.setText(getter_setter.getOffice_email());
        office_add.setText(getter_setter.getOffice_add());
        if(!(getter_setter.getCsi_member_s()==null)) {
            if (getter_setter.getCsi_member_s().equalsIgnoreCase("yes")) {
                radioGroup.check(R.id.radio_yes);
            } else {
                radioGroup.check(R.id.radio_no);
            }
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registration.this, upload_image.class));
                onStart();
            }
        });



        save=(Button)findViewById(R.id.save_reg);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isConnected()) {
                    if (first_name.getText().toString().length() == 0) {
                        first_name.setError(getString(R.string.error_field_required));
                        focusView = first_name;
                    } else if (!first_name.getText().toString().matches("[a-z,A-Z ]+")) {
                        first_name.setError("please type name in Alphabets");
                        focusView = first_name;
                    } else if (last_name.getText().toString().length() == 0) {
                        last_name.setError(getString(R.string.error_field_required));
                        focusView = last_name;
                    } else if (!last_name.getText().toString().matches("[a-z,A-Z ]+")) {
                        last_name.setError("please type name in Alphabets");
                        focusView = last_name;
                    } else if (age.getText().toString().length() == 0) {
                        age.setError(getString(R.string.error_field_required));
                        focusView = age;
                    } else if (!age.getText().toString().matches("[0-9]+")) {
                        age.setError("please enter the correct age");
                        focusView = age;
                    } else if (!((Integer.parseInt(age.getText().toString()) <= 70)&&(Integer.parseInt(age.getText().toString()) >= 16))) {
                        age.setError("please enter the correct age. Your age Should be atleast 16 to register.");
                        focusView = age;
                    } else if (contact_no.getText().toString().length() == 0) {
                        contact_no.setError(getString(R.string.error_field_required));
                        focusView = contact_no;
                    } else if (contact_no.getText().toString().length() != 10) {
                        contact_no.setError("please enter a valid Phone number");
                        focusView = contact_no;
                    } else if (email.getText().toString().length() == 0) {
                        email.setError(getString(R.string.error_field_required));
                        focusView = email;
                    } else if (!isEmailValid(email.getText().toString())) {
                        email.setError(getString(R.string.error_invalid_email) + " only gmail-id");
                        focusView = email;
                    } else if(radioGroup.getCheckedRadioButtonId() == -1)
                    {
                         Snackbar.make(v, "Please select the C.S.I membership.", Snackbar.LENGTH_LONG)
                              .setAction("Action", null).show();
                    }
                    else if(bitmap_reg==null)
                    {
                        Snackbar.make(v, "Please select user profile image.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else
                    {
                        //User_info..

                        first_name_s=first_name.getText().toString();
                        last_name_s=last_name.getText().toString();
                        age_s=age.getText().toString();
                        contact_no_s=contact_no.getText().toString();
                        email_s=email.getText().toString();
                        com_name_s=com_name.getText().toString();
                        office_contact_no_s=office_contact_no.getText().toString();
                        office_email_s=office_email.getText().toString();
                        office_add_s=office_add.getText().toString();
                        radio_id=radioGroup.getCheckedRadioButtonId();
                        r_button=(RadioButton)findViewById(radio_id);
                        csi_member_s=String.valueOf(r_button.getText());
                        //obj.set_user_image(bitmap_reg);
                        //obj.set_detail(first_name_s,last_name_s,age_s,contact_no_s,email_s,com_name_s,office_contact_no_s,office_email_s,office_add_s,csi_member_s,registration_status);
                        new AlertDialog.Builder(registration.this)
                                .setTitle("Registration Request")
                                .setMessage(get_registration_details()+"\nSelect ok to confirm and cancel to deny the requested details.")
                                .setIcon(R.drawable.agnos_logo)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (count<100){
                                    count++;
                                    android.os.SystemClock.sleep(50);
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            showProgress(true);
                                        }
                                    });
                                }
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent=new Intent(registration.this,firebase_firestore.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("key", "value_register");
                                        bundle.putString("first_name", first_name_s);
                                        bundle.putString("last_name", last_name_s);
                                        bundle.putString("age", age_s);
                                        bundle.putString("contact_no", contact_no_s);
                                        bundle.putString("email", email_s);
                                        bundle.putString("com_name", com_name_s);
                                        bundle.putString("office_contact_no", office_contact_no_s);
                                        bundle.putString("office_email", office_email_s);
                                        bundle.putString("office_add_s", office_add_s);
                                        bundle.putString("csi_member_s", csi_member_s);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        }).start();
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();
                    }
                }
                else
                {
                    Snackbar.make(v, "Connection failed! Please check your internet connection..", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
    }


    public boolean isConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        } else {
            return false;

        }
    }



    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@gmail.com");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(registration.this, Main_screen_with_navigation.class));
        finish();
    }
    public void on_back_register(View view)
    {
        Intent intent=new Intent(registration.this,Main_screen_with_navigation.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        bitmap_reg= database_handler.get_user_image();
        if(!(bitmap_reg==null))
        {
            imageView.setImageBitmap(bitmap_reg);
        }
        super.onStart();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mregistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mregistrationFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mregistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mregistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    public  String get_registration_details()
    {
        String registration_details;
        if(com_name.getText().toString().isEmpty()) {
             registration_details="Personal Details\n"+"\nFirst Name: "+first_name_s+"\nLast Name: "+last_name_s+"\nAge: "+age_s+"\nContact no.: "+contact_no_s +"\nEmail id: "+email_s+ "\n"
                    +"\nMember of C.S.I: "+csi_member_s +"\n";
        }
        else {
             registration_details="Personal Details\n"+"\nFirst Name: "+first_name_s+"\nLast Name: "+last_name_s+"\nAge: "+age_s+"\nContact no.: "+contact_no_s +"\nEmail id: "+email_s+ "\n"
                    +"\nCompany Details\n"+"\nBusiness / Company name: "+com_name_s+"\nContact no.: "+office_contact_no_s +"\nEmail id: "+office_email_s+"\n"
                    +"\nMember of C.S.I: "+csi_member_s +"\n";
        }

        return registration_details;
    }

}
