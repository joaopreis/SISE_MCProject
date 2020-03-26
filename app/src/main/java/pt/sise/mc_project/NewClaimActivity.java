package pt.sise.mc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewClaimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);
        Log.d("SISE","New Claim Activity Created.");

        final Button cancelButton=findViewById(R.id.newClaimCancelButton);
        final Button submitButton=findViewById(R.id.newClaimSubmitButton);
        final EditText editClaimTitle=findViewById(R.id.claimTitle);
        final EditText editPlateNumber=findViewById(R.id.plateNumber);
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
                String plateNumber=editPlateNumber.getText().toString();
                String claimDate=editClaimDate.getText().toString();
                String claimDescription=editClaimDescription.getText().toString();

                if (claimDate.equals("")||claimDescription.equals("")||claimTitle.equals("")||plateNumber.equals("")){
                    Toast.makeText(v.getContext(), "Fill all the information please", Toast.LENGTH_LONG).show();
                    return;
                }

                // return an intent containing the claim information
                Intent resultIntent = new Intent();
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE,claimTitle);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE_NUMBER,plateNumber);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DATE,claimDate);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DESCRIPTION,claimDescription);
                setResult(Activity.RESULT_OK,resultIntent);
                Toast.makeText(v.getContext(), "Claim submitted", Toast.LENGTH_LONG).show();
                finish();



            }
        });
    }
}
