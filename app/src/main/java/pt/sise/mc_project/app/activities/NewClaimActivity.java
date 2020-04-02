package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSListPlates;

public class NewClaimActivity extends AppCompatActivity {

    String _plateNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GlobalState globalstate=(GlobalState) getApplicationContext();
        int _sessionId=globalstate.get_sessionId();
        List<String> plates = null;

        try {
            plates=new WSListPlates(_sessionId).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);
        Log.d("SISE","New Claim Activity Created.");

        final Button cancelButton=findViewById(R.id.newClaimCancelButton);
        final Button submitButton=findViewById(R.id.newClaimSubmitButton);
        final EditText editClaimTitle=findViewById(R.id.claimTitle);
        final Spinner editPlateNumber=findViewById(R.id.newClaimPlateNumber);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, plates);
        editPlateNumber.setAdapter(adapter);
        editPlateNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String plateNumber=(String) parent.getItemAtPosition(position);
                _plateNumber=plateNumber;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText editClaimDate=findViewById(R.id.claimDate);
        final EditText editClaimDescription=findViewById(R.id.claimDescription);



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Back Button Clicked.");
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("SISE","Submit Button Clicked.");
                String claimTitle=editClaimTitle.getText().toString();
                Log.d("SISE",_plateNumber);
                String claimDate=editClaimDate.getText().toString();
                String claimDescription=editClaimDescription.getText().toString();

                if (claimDate.equals("")||claimDescription.equals("")||claimTitle.equals("")||_plateNumber.equals("")){
                    Toast.makeText(v.getContext(), "Fill all the information please", Toast.LENGTH_LONG).show();
                    return;
                }

                // return an intent containing the claim information
                Intent resultIntent = new Intent();
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE,claimTitle);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE_NUMBER,_plateNumber);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DATE,claimDate);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DESCRIPTION,claimDescription);
                setResult(Activity.RESULT_OK,resultIntent);
                finish();



            }
        });
    }
}
