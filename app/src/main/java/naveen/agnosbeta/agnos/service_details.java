package naveen.agnosbeta.agnos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class service_details extends AppCompatActivity {
    private Spinner service_Select;
    private ArrayAdapter services_arrayadapter;
    private EditText product_description;
    private Button request_send;
    private View focusView = null;
    int count=0;
    private static TextView Username_about;
    private View mProgressView;
    private View mregistrationFormView;
    private Handler mHandler = new Handler();
    private database_handler obj=new database_handler();
    private String service_type,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_details);

        //Email returning class..
        Username_about= (TextView) findViewById(R.id.user_email_about4);
        Username_about.setText(obj.email_action_bar());

       service_Select = (Spinner) findViewById(R.id.service_select);
       product_description=(EditText) findViewById(R.id.product_description);
       request_send=(Button)findViewById(R.id.request_button_service_details);
        mProgressView = findViewById(R.id.reg_progress_service_details);
        mregistrationFormView=findViewById(R.id.service_details_request_view);
        ArrayList<String> services_list = new ArrayList<>();
        //services
        services_list.add("Website Development");
        services_list.add("Android Applications");
        services_list.add("Cryptocurrency (Bitcoin Related Softwares)");
        services_list.add("Blockchain Softwares");
        services_list.add("IOT (Internet Of Things) Devices");
        services_arrayadapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, services_list);
        service_Select.setAdapter(services_arrayadapter);

        //setting values
        if (!(getter_setter.getService_type() == null)) {
            if (getter_setter.getService_type().equalsIgnoreCase("Website DevelopmentS")) {
                service_Select.setSelection(0);
            } else if (getter_setter.getService_type().equalsIgnoreCase("Android Applications")) {
                service_Select.setSelection(1);
            } else if (getter_setter.getService_type().equalsIgnoreCase("Cryptocurrency (Bitcoin Related Softwares)")) {
                service_Select.setSelection(2);
            } else if (getter_setter.getService_type().equalsIgnoreCase("Blockchain Softwares")) {
                service_Select.setSelection(2);
            } else if (getter_setter.getService_type().equalsIgnoreCase("IOT (Internet Of Things) Devices")) {
                service_Select.setSelection(2);
            }
        }
        product_description.setText(getter_setter.getDescription());


        request_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view=v;
                service_type=service_Select.getSelectedItem().toString();
                description=product_description.getText().toString();
                if (product_description.getText().toString().length() == 0) {
                    Snackbar.make(v, "Please enter your project description.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                        new AlertDialog.Builder(service_details.this)
                                .setTitle("Service Request")
                                .setMessage(get_full_details() + "\n" + "\nSelect ok to confirm and cancel to deny the requested details.")
                                .setIcon(R.drawable.agnos_logo)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Snackbar.make(view, "Creating a request for your project.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                        new AlertDialog.Builder(service_details.this)
                                                .setTitle("Service Request")
                                                .setMessage("Confirm ok to register the request. and cancel to deny the request.\n")
                                                .setIcon(R.drawable.agnos_logo)
                                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int whichButton) {
                                                        Snackbar.make(view, "Sending the request of your project.", Snackbar.LENGTH_LONG)
                                                                .setAction("Action", null).show();
                                                        new Thread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                while (count < 100) {
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
                                                                        Intent intent = new Intent(service_details.this, firebase_firestore.class);
                                                                        Bundle bundle = new Bundle();
                                                                        bundle.putString("key", "value_services");
                                                                        bundle.putString("Project_Type", service_Select.getSelectedItem().toString());
                                                                        bundle.putString("Project_Description", product_description.getText().toString());
                                                                        intent.putExtras(bundle);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                });
                                                            }
                                                        }).start();
                                                    }
                                                })
                                                .setNegativeButton(android.R.string.no, null).show();

                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show();
                    }
                }

        });

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

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(service_details.this,services.class);
        startActivity(intent);
        finish();
        //super.onBackPressed();
    }

    public void on_back_service(View view)
    {
        onBackPressed();
    }

    public String get_full_details()
    {
        registration obj=new registration();
        String full_details="Personal Details\n"+"\nFirst Name: "+getter_setter.getFirst_name()+"\nLast Name: "+getter_setter.getLast_name()+"\nAge: "+getter_setter.getAge()+"\nContact no.: "+getter_setter.getContact_no() +"\nEmail id: "+getter_setter.getEmail()+ "\n"
                +"\nCompany Details\n"+"\nBusiness / Company name: "+getter_setter.getCom_name()+"\nContact no.: "+getter_setter.getOffice_contact_no() +"\nEmail id: "+getter_setter.getOffice_email()+"\n"
                +"\nMember of C.S.I: "+getter_setter.getCsi_member_s() +"\n" +"\nService Details\n"+"\nCategory of Project: "+service_type+"\nProject description: "+description;
        return full_details;
    }
}
