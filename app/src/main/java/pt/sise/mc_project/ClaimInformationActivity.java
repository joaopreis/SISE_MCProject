package pt.sise.mc_project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClaimInformationActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Insure - ClaimInformation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_information);

        // set up the listener of the back button
        final Button buttonOk = (Button) findViewById(R.id.claimInformationOkButton);
        buttonOk.setOnClickListener(new View.OnClickListener() {
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

        int index = extras.getInt(InternalProtocol.READ_CLAIM_INDEX);
        Log.d(InternalProtocol.LOG, "Index:" + index);

        // obtain a reference to the claim's data structure
        GlobalState context = (GlobalState) getApplicationContext();
        Claim claim = context.get_claimList().get(index);

        //update the UI
        TextView claimIDTextView = (TextView) findViewById(R.id.claimID);
        Log.d(InternalProtocol.LOG, "ID:" + claim.get_id());
        claimIDTextView.setText(claim.get_id());

        TextView ClaimStatusTextView = (TextView) findViewById(R.id.claimState);
        ClaimStatusTextView.setText(claim.get_status());

        TextView ClaimTitleTextView = (TextView) findViewById(R.id.claimTitle);
        ClaimTitleTextView.setText(claim.get_title());

        TextView ClaimPlateNumberTextView = (TextView) findViewById(R.id.plateNumber);
        ClaimPlateNumberTextView.setText(claim.get_plateNumber());

        TextView ClaimDateTextView = (TextView) findViewById(R.id.claimDate);
        ClaimDateTextView.setText((CharSequence) claim.get_date());

        TextView ClaimDescrTextView = (TextView) findViewById(R.id.claimDescription);
        ClaimDescrTextView.setText((CharSequence) claim.get_date());



    }
}
