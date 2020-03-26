package pt.sise.mc_project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClaimInformationActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Insure - ClaimInformation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_information);

        // set up the listener of the back button
        final Button buttonDone = (Button) findViewById(R.id.claim_info_btn_back);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // just finish the current activity
                finish();
            }
        });
        // display the title and body of the note identified by the index parameter
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            Log.d(InternalProtocol.LOG, "Internal error: Index cannot be null.");
            finish();
            return;
        }
        //int index = extras.getInt(InternalProtocol.READ_Claim_INDEX);
        //Log.d(InternalProtocol.LOG, "Index:" + index);

       // // obtain a reference to the claim's data structure
       // GlobalState context = (GlobalState) getApplicationContext();
       // Claim claim = context.get_claimList().get(index);

       // // update the UI
        //TextView claimIDTextView = (TextView) findViewById(R.id.space_Claim_id);
       // claimIDTextView.setText(claim.getClaim_ID());

       // TextView ClaimStatusTextView = (TextView) findViewById(R.id.space_Status);
       // ClaimStatusTextView.setText(claim.getStatus());

        //TextView ClaimTitleTextView = (TextView) findViewById(R.id.space_title);
      //  ClaimStatusTextView.setText(claim.getTitle());

       // TextView ClaimPlateNumberTextView = (TextView) findViewById(R.id.number_Plate_number);
       // ClaimStatusTextView.setText(claim.getPlate_number());

       // TextView ClaimDateTextView = (TextView) findViewById(R.id.date_occurence_date);
       // ClaimStatusTextView.setText((CharSequence) claim.getOccurence_date());



    }
}
