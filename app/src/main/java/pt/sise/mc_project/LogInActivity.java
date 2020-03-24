package pt.sise.mc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Log.d("SISE","Log In Created!");

        final Button logInButton=findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText username=findViewById(R.id.username);
                TextInputEditText password=findViewById(R.id.password);
                Log.d("SISE","Log In Button Clicked.");
                Log.d("SISE",username.toString());
                Log.d("SISE",password.toString());

                Toast.makeText(logInButton.getContext(),"Log in successful!",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
