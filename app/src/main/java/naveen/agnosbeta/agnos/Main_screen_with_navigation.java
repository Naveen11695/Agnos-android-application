package naveen.agnosbeta.agnos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Main_screen_with_navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private  TextView user_e_mail;
    private ImageView targetImage;
    private StorageReference mStorageRef;
    static Bitmap bitmap=null;
    private boolean flag=false;
    ArrayList<String> basicFields;
    GridView gridView;
    gridAdapter adapter;
    public static Activity activity;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_main_screen_with_navigation);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            mAuth = FirebaseAuth.getInstance();
            mStorageRef = FirebaseStorage.getInstance().getReference();
            user_e_mail=(TextView)findViewById(R.id.nav_user_email);
            database_handler obj=new database_handler();
            user_e_mail.setText(obj.email_action_bar());
            basicFields = new ArrayList<>();
            gridView = (GridView)findViewById(R.id.grid);
            if(getter_setter.getFirst_name()==null) {
                basicFields.add("Personal Details");
            }
            else {
                basicFields.add("Edit Details");
            }
            basicFields.add("Services");
            basicFields.add("Change Password");
            basicFields.add("About Us");
            basicFields.add("Locate Us");
            basicFields.add("Help And Feedback");
            basicFields.add("Logout");
            adapter = new gridAdapter(this,basicFields);
            gridView.setAdapter(adapter);
            activity = this;

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

           NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


            View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main);
            user_e_mail = (TextView)navHeaderView.findViewById(R.id.targeted_text);
            user_e_mail.setText(obj.email_action_bar());
            targetImage=(ImageView)navHeaderView.findViewById(R.id.nav_user_img);

            bitmap= database_handler.get_user_image();
            if(!(bitmap==null))
            {
                targetImage.setImageBitmap(bitmap);
                //Toast.makeText(getApplicationContext(),bitmap.toString(),Toast.LENGTH_SHORT).show();
            }
            targetImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    startActivity(new Intent(Main_screen_with_navigation.this,Main_screen_with_navigation.class));
                    startActivity(new Intent(Main_screen_with_navigation.this,upload_image.class));
                    finish();
                }
            });
        }


    @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                flag=true;
                moveTaskToBack (true);
            }
        }

    @Override
    protected void onStart() {
            View view;
            view = findViewById(R.id.content_frame);
            Bundle bundle = getIntent().getExtras();
            String value;
            if(!isNetworkAvailable())
            {
                Toast.makeText(getApplicationContext(),"Network Unavailable!!",Toast.LENGTH_SHORT).show();
            }
            if (bundle != null) {
                value = bundle.getString("key");
                if (value.equalsIgnoreCase("value_download")&&!flag) {
                    Snackbar.make(view, "login Successfully..", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (value.equalsIgnoreCase("value_register")) {
                    Snackbar.make(view, "Details Save Successfully..", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            super.onStart();

        }

    @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if(id == R.id.nav_Home)
            {
                startActivity(new Intent(Main_screen_with_navigation.this,Main_screen_with_navigation.class));
                finish();
            }
            else if (id == R.id.nav_first_layout) {
                startActivity(new Intent(Main_screen_with_navigation.this,registration.class));
                finish();

            } else if (id == R.id.change_pass) {
                startActivity(new Intent(Main_screen_with_navigation.this,change_pass.class));

            } else if (id == R.id.nav_services) {
                startActivity(new Intent(Main_screen_with_navigation.this,services.class));

            } else if (id == R.id.locate) {
                startActivity(new Intent(Main_screen_with_navigation.this,locate_us.class));

            } else if (id == R.id.nav_about) {
                startActivity(new Intent(Main_screen_with_navigation.this,About.class));
                finish();
            }
            else if (id == R.id.nav_share) {
                 final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
                _Intent.setType("text/html");
                _Intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_share_message));
                _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_share_subject));
                startActivity(Intent.createChooser(_Intent, getString(R.string.title_share)));

            }else if (id == R.id.nav_help) {
                startActivity(new Intent(Main_screen_with_navigation.this,feedback.class));

            } else if (id == R.id.nav_logout) {
                startActivity(new Intent(Main_screen_with_navigation.this, logout.class));
                finish();

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    // Private class isNetworkAvailable
    private boolean isNetworkAvailable() {
        // Using ConnectivityManager to check for Network Connection
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    }
