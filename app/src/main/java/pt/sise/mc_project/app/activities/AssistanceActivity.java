package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.sise.mc_project.R;

public class AssistanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance);

        final Button buttonBack = (Button) findViewById(R.id.buttonBackAssistance);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // just finish the current activity
                finish();
            }
        });

    }
}
