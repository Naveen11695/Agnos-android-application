package naveen.agnosbeta.agnos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class services extends AppCompatActivity {

    ExpandableListView expandableListView;
    private static TextView Username_about;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);

        //Email returning class..
        database_handler about = new database_handler();
        Username_about = (TextView) findViewById(R.id.user_email_about4);
        Username_about.setText(about.email_action_bar());

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

    public void onrequest(View view) {
        if(getter_setter.getFirst_name()==null) {
            Snackbar.make(view, "Please fill the Personal details before sending a request.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else {
            Intent intent=new Intent(services.this,service_details.class);
            startActivity(intent);
            finish();
        }


    }

    public void on_back_service(View view)
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(services.this,Main_screen_with_navigation.class);
        startActivity(intent);
        finish();
        //super.onBackPressed();
    }
}
