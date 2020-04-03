package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSLogOut;

public class SettingsActivity extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SISE", "Settings Activity Created.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent=getIntent();

        final int _sessionId=intent.getIntExtra(InternalProtocol.SESSION_ID,0);

        buttonBack = (Button) findViewById(R.id.settingsBackButton);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // just finish the current activity
                finish();
            }
        });

        buttonLogOut = (Button) findViewById(R.id.logOutButton);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean result=new WSLogOut(_sessionId).execute().get();
                    if(result){
                        Intent intent = new Intent();
                        Toast.makeText(getApplicationContext(),"Log out successful",Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        final Button TermsAndConditionsButton = (Button) findViewById(R.id.TermsAndConditionsbutton);
        TermsAndConditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE", "Terms and Conditions Button Clicked.");
                Intent intent = new Intent(SettingsActivity.this, TermsAndConditionsActivity.class);
                startActivity(intent);
            }
        });

        final Button AssistanceButton = (Button) findViewById(R.id.AssistanceButton);
        AssistanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE", "Assistance Button Clicked.");
                Intent intent = new Intent(SettingsActivity.this, AssistanceActivity.class);
                startActivity(intent);
            }
        });

        final Button PrivacyPolicybutton = (Button) findViewById(R.id.Privacy);
        PrivacyPolicybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE", "Privacy Policy Button Clicked.");
                Intent intent = new Intent(SettingsActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        final Button InsureWebSiteButton = (Button) findViewById(R.id.InsureWebSite);
        InsureWebSiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://localhost:8070/"));
                startActivity(intent);
            }
        });

    }
}
