package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSClaimInfo;
import pt.sise.mc_project.datamodel.ClaimItem;
import pt.sise.mc_project.datamodel.ClaimRecord;

public class ClaimInformationActivity extends AppCompatActivity {

    private ClaimRecord _claimRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_information);

        // set up the listener of the back button
        final Button buttonOk = (Button) findViewById(R.id.claimsInformationHomeButton);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // just finish the current activity
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        final Button buttonBack = (Button) findViewById(R.id.ClaimsInformationBackButton);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
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
        ClaimItem claim = context.get_claimItemList().get(index);
        try {
            ClaimRecord claimRecord=new WSClaimInfo(context.get_sessionId(),claim.getId(),context.get_username(),ClaimInformationActivity.this).execute().get();
            this._claimRecord=claimRecord;
            Log.d("SISE","claim Record" +_claimRecord);
            if (_claimRecord==null){
                Log.d("SISE","I am here");
                Toast.makeText(getApplicationContext(),"Information not available. Server is down.", Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK);
                finish();
                return;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //update the UI
        TextView claimIDTextView = (TextView) findViewById(R.id.claimsInformationId);
        claimIDTextView.setText(_claimRecord.getId()+"");

        TextView ClaimStatusTextView = (TextView) findViewById(R.id.claimsInformationState);
        ClaimStatusTextView.setText(_claimRecord.getStatus());

        TextView ClaimTitleTextView = (TextView) findViewById(R.id.claimsInformationTitle);
        ClaimTitleTextView.setText(_claimRecord.getTitle());

        TextView ClaimPlateNumberTextView = (TextView) findViewById(R.id.claimsInformationPlateNuber);
        ClaimPlateNumberTextView.setText(_claimRecord.getPlate());

        TextView ClaimSubDateTextView = (TextView) findViewById(R.id.claimsInformationSubmissionDate);
        ClaimSubDateTextView.setText( _claimRecord.getSubmissionDate());

        TextView ClaimDateTextView = (TextView) findViewById(R.id.claimsInformationDate);
        ClaimDateTextView.setText( _claimRecord.getOccurrenceDate());

        TextView ClaimDescrTextView = (TextView) findViewById(R.id.claimsInformationDescrption);
        ClaimDescrTextView.setText( _claimRecord.getDescription());

    }
}
