package pt.sise.mc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    //change

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("SISE","Home Activity Created.");

        final Button profileButton=findViewById(R.id.homePersonalInformationButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Profile Button Clicked.");
            }
        });

        final Button newClaimButton=findViewById(R.id.homeNewClaimButton);
        newClaimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","New Claim Button Clicked.");
                Intent intent=new Intent(HomeActivity.this,NewClaimActivity.class);
                startActivityForResult(intent,1);
            }
        });

        final Button claimsInformationButton=findViewById(R.id.homeClaimsInformationButton);
        claimsInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Claims Information Button Clicked.");
            }
        });

        final Button settingsButton=findViewById(R.id.homeSettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Settings Button Clicked.");
            }
        });
        }
    }

