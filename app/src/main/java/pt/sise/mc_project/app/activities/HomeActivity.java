package pt.sise.mc_project.app.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.app.WSCustomerInfo;
import pt.sise.mc_project.app.WSListClaims;
import pt.sise.mc_project.app.WSNewClaim;
import pt.sise.mc_project.datamodel.ClaimItem;
import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.R;
import pt.sise.mc_project.datamodel.ClaimUnprocessed;
import pt.sise.mc_project.datamodel.Customer;

public class HomeActivity extends AppCompatActivity {

    private List<ClaimItem> _claimItemList;
    private int _sessionId;
    private String _username;
    private GlobalState globalState;

    //@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("SISE", "Home Activity Created.");

        /* place the claim list in the application domain */
        globalState = (GlobalState) getApplicationContext();

        /* Get the sessionId and the username of the customer */
        _sessionId = globalState.get_sessionId();
        _username = globalState.get_username();

        //
        final Button profileButton = findViewById(R.id.homePersonalInformationButton);
        final Button newClaimButton = findViewById(R.id.homeNewClaimButton);
        final Button claimsInformationButton = findViewById(R.id.homeClaimsInformationButton);
        final Button settingsButton = findViewById(R.id.homeSettingsButton);
        //final ProgressBar pb= (ProgressBar) findViewById(R.id.homeProgressBar);

        // Process Claims
        String claimUnprocessedFile = "ClaimUnprocessed"+ _username + ".json";
        try {
            String claimUnprocessedJson = JsonFileManager.jsonReadFromFile(HomeActivity.this,claimUnprocessedFile);
            List<ClaimUnprocessed> JsonclaimUnprocessed = JsonCodec.decodeClaimUnprocessed(claimUnprocessedJson);
            for (int i=0; i<JsonclaimUnprocessed.size(); i++){
                ClaimUnprocessed claimUnpr = JsonclaimUnprocessed.get(i);
                new WSNewClaim(claimUnpr.get_title(),claimUnpr.get_date(),claimUnpr.get_plateNumber(), claimUnpr.get_description(),_sessionId, globalState, HomeActivity.this).execute();
            }
            HomeActivity.this.deleteFile(claimUnprocessedFile);
            Toast.makeText(getApplicationContext(), "Your previous claims have been submitted!.", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.d("SISE", "onCreate: No claims to process!");
        }

        // List of claims is generated as soon as the HomeActivity is created
        try {
            _claimItemList = new WSListClaims(_sessionId).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        globalState.set_claimItemList(_claimItemList);


        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE", "Profile Button Clicked.");
                try {

                    Log.d("SISE", _sessionId + "");
                    Customer customer = new WSCustomerInfo(_sessionId,_username,HomeActivity.this).execute().get();
                    if (customer==null){
                        Toast.makeText(getApplicationContext(),"Information not available. Server is down",Toast.LENGTH_LONG).show();
                    }else {
                        Log.d("SISE", customer.toString());
                        Intent intent = new Intent(HomeActivity.this, PersonalInformationActivity.class);
                        intent.putExtra(InternalProtocol.CUSTOMER_NAME, customer.getName());
                        intent.putExtra(InternalProtocol.CUSTOMER_BIRTHDATE, customer.getDateOfBirth());
                        intent.putExtra(InternalProtocol.CUSTOMER_NIF, customer.getFiscalNumber() + "");
                        intent.putExtra(InternalProtocol.CUSTOMER_ADDRESS, customer.getAddress());
                        intent.putExtra(InternalProtocol.CUSTOMER_POLICY_NUMBER, customer.getPolicyNumber() + "");
                        startActivity(intent);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        newClaimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE", "New Claim Button Clicked.");
                Intent intent = new Intent(HomeActivity.this, NewClaimActivity.class);
                startActivityForResult(intent, InternalProtocol.NEW_CLAIM_REQUEST);
            }
        });


        claimsInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE", "Claims Information Button Clicked.");

                Intent intent = new Intent(HomeActivity.this, ClaimsHistoryActivity.class);
                startActivity(intent);
            }
        });


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE", "Settings Button Clicked.");
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                intent.putExtra(InternalProtocol.SESSION_ID,_sessionId);
                startActivityForResult(intent,InternalProtocol.LOG_OUT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case InternalProtocol.NEW_CLAIM_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    String claimTitle = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE);
                    String plateNumber = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE_NUMBER);
                    String claimDate = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DATE);
                    String claimDescription = data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DESCRIPTION);
                    try {
                        boolean result = new WSNewClaim(claimTitle, claimDate, plateNumber, claimDescription, _sessionId, globalState, HomeActivity.this).execute().get();
                        if (result) {
                            Toast.makeText(getApplicationContext(), "Claim submitted.", Toast.LENGTH_LONG).show();
                            try {
                                _claimItemList = new WSListClaims(_sessionId).execute().get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            globalState.set_claimItemList(_claimItemList);
                        } else {
                            Toast.makeText(getApplicationContext(), "Claim not submitted. Waiting for server Validation", Toast.LENGTH_LONG).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.d(InternalProtocol.LOG, "Cancel pressed.");
                } else {
                    Log.d(InternalProtocol.LOG, "Internal error: unknown result code.");
                }
                break;
            case InternalProtocol.LOG_OUT_REQUEST:
                if (resultCode==Activity.RESULT_OK){
                    globalState.set_sessionId(0);
                    finish();
                }else{
                    Log.d("SISE","LOG OUT ERROR.");
                }
            default:
                Log.d(InternalProtocol.LOG, "Internal error: unknown intent message.");
        }
    }
}

