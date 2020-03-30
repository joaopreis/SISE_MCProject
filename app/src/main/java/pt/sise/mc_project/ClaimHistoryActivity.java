package pt.sise.mc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ClaimHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_history);
        final Button backButton=findViewById(R.id.claimsHistoryBackButton);

        // get the claim list in the application domain
        GlobalState globalState = (GlobalState) getApplicationContext();
        ArrayList<Claim> _claimList=globalState.get_claimList();


        // assign adapter to list view
        ListView _listView = (ListView) findViewById(R.id.claims_list);
        ArrayAdapter<Claim> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, _claimList);
        _listView.setAdapter(adapter);

        // attach click listener to list view items
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the claim information activity, passing to it the index position as parameter
                Log.d("position", position + "");
                Intent intent = new Intent(ClaimHistoryActivity.this, ClaimInformationActivity.class);
                intent.putExtra(InternalProtocol.READ_CLAIM_INDEX, position);
                startActivity(intent);
            }

        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Back Button Clicked.");
                finish();
            }
        });




    }
}
