package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSLogOut;

public class SettingsActivity extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                        Toast.makeText(getApplicationContext(),"Log out successful",Toast.LENGTH_LONG);
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
    }
}
