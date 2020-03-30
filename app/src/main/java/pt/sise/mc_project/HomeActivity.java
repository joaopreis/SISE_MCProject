package pt.sise.mc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<Claim> _claimList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("SISE","Home Activity Created.");

        // place the claim list in the application domain
        _claimList = new ArrayList<Claim>();
        GlobalState globalState = (GlobalState) getApplicationContext();
        globalState.set_claimList(_claimList);

        final Button profileButton=findViewById(R.id.homePersonalInformationButton);
        final Button newClaimButton=findViewById(R.id.homeNewClaimButton);
        final Button claimsInformationButton=findViewById(R.id.homeClaimsInformationButton);
        final Button settingsButton=findViewById(R.id.homeSettingsButton);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Profile Button Clicked.");
                Intent intent=new Intent(HomeActivity.this,PersonalInformationActivity.class);
                startActivity(intent);
            }
        });


        newClaimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","New Claim Button Clicked.");
                Intent intent=new Intent(HomeActivity.this,NewClaimActivity.class);
                startActivityForResult(intent,InternalProtocol.NEW_CLAIM_REQUEST);
            }
        });


        claimsInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Claims Information Button Clicked.");
                Intent intent=new Intent(HomeActivity.this,ClaimHistoryActivity.class);
                startActivity(intent);
            }
        });


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Settings Button Clicked.");
            }
        });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case InternalProtocol.NEW_CLAIM_REQUEST:
                if (resultCode== Activity.RESULT_OK){
                    String claimTitle=data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE);
                    String plateNumber=data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE_NUMBER);
                    String claimDate=data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DATE);
                    String claimDescription=data.getStringExtra(InternalProtocol.KEY_NEW_CLAIM_DESCRIPTION);
                    Claim claim= new Claim(1,claimTitle,plateNumber,claimDate,"Submitted",claimDescription);
                    _claimList.add(claim);

                }else if (resultCode==Activity.RESULT_CANCELED){
                    Log.d(InternalProtocol.LOG, "Cancel pressed.");
                }else {
                    Log.d(InternalProtocol.LOG, "Internal error: unknown result code.");
                }
                break;
            default:
                Log.d(InternalProtocol.LOG, "Internal error: unknown intent message.");

        }

    }
    }

