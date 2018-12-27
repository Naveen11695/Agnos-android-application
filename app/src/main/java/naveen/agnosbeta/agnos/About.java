package naveen.agnosbeta.agnos;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {

    Animation anim1,anim2;
    private static TextView Useremail;
    private WebView web1;
    private View ctr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        database_handler about=new database_handler();
        moveIcon(findViewById(R.id.imageViewAbout));
        Useremail=(TextView)findViewById(R.id.user_email_about);
        ctr=findViewById(R.id.ctr);
        //members user_id_details
        web1 = (WebView) findViewById(R.id.webview);


        //Email returning class..
        Useremail.setText(about.email_action_bar());
        WebSettings settings = web1.getSettings();
        //

    }



    private void moveIcon( View view )
    {
        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);
        anim2 = new TranslateAnimation( 0, 0, 0, originalPos[1]+10 );
        anim2.setDuration(100);
        anim2.setFillAfter(true);
        view.startAnimation(anim2);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(About.this,Main_screen_with_navigation.class));
        finish();
    }
    public void about_icon(View view)
    {
        int id = view.getId();
        if(id==R.id.back_about) {
                onBackPressed();
        }
        else if(isNetworkAvailable()) {
            switch (view.getId()) {
                case R.id.website_about:
                    ctr.setVisibility(View.GONE);
                    web1.setVisibility(View.VISIBLE);
                    web1.loadUrl("http://www.agnosdevelopers.in");
                    web1.setWebViewClient(new WebViewClient());
                    break;
                case R.id.whatsapp_about:
                    Snackbar.make(view, "Add Phone:98737359735,8826843701 to contact us", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    break;
                case R.id.facebook_about:
                    ctr.setVisibility(View.GONE);
                    web1.setVisibility(View.VISIBLE);
                    web1.loadUrl("");
                    web1.setWebViewClient(new WebViewClient());
                    break;
                case R.id.twitter_about:
                    ctr.setVisibility(View.GONE);
                    web1.setVisibility(View.VISIBLE);
                    web1.loadUrl("");
                    web1.setWebViewClient(new WebViewClient());
                    break;
                case R.id.blog_about:
                    ctr.setVisibility(View.GONE);
                    web1.setVisibility(View.VISIBLE);
                    web1.loadUrl("");
                    web1.setWebViewClient(new WebViewClient());
                    break;
            }
        }
            else
            {
                Snackbar.make(view, "Network Unavailable!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
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

