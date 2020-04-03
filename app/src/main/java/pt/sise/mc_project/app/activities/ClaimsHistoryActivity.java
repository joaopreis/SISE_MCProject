package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pt.sise.mc_project.datamodel.ClaimItem;
import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.R;

public class ClaimsHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims_history);
        final Button backButton=findViewById(R.id.claimsHistoryBackButton);

        // get the claim list in the application domain
        GlobalState globalState = (GlobalState) getApplicationContext();
        List<ClaimItem> _claimItemList =globalState.get_claimItemList();

        // assign adapter to list view
        ListView _listView = (ListView) findViewById(R.id.claimsList);
        ArrayAdapter<ClaimItem> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, _claimItemList);
        _listView.setAdapter(adapter);

        // attach click listener to list view items
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the claim information activity, passing to it the index position as parameter
                Log.d("position", position + "");
                Intent intent = new Intent(ClaimsHistoryActivity.this, ClaimInformationActivity.class);
                intent.putExtra(InternalProtocol.READ_CLAIM_INDEX, position);
                startActivityForResult(intent,InternalProtocol.CLAIM_INFORMATION_REQUEST);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case InternalProtocol.CLAIM_INFORMATION_REQUEST:
                if (resultCode== Activity.RESULT_CANCELED){
                    finish();
                }
        }
    }
}
