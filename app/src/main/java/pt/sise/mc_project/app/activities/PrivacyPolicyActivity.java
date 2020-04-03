package pt.sise.mc_project.app.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.sise.mc_project.R;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        final TextView TextPrivacyPolicy = findViewById(R.id.PrivacyPolicyTextView);
        TextPrivacyPolicy.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        final Button buttonBack = findViewById(R.id.buttonbackPrivacyPolicy);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // just finish the current activity
                finish();
            }
        });
    }
}
