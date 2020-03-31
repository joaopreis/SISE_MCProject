package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSLogIn;

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
                EditText username=findViewById(R.id.username);
                EditText password=findViewById(R.id.password);


                // create separate AsynkTasks that behave differently for each request in different app
                try {
                    int id=new WSLogIn(username.getText().toString(),password.getText().toString()).execute().get();
                    Log.d("SISE","ID VALUE:"+id);
                    if (id==0){
                        Toast.makeText(logInButton.getContext(),"Invalid log in. Try again!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(logInButton.getContext(),"Log In successful!",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(LogInActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d("SISE","Log In Button Clicked.");
                Log.d("SISE",username.getText().toString());
                Log.d("SISE",password.getText().toString());
            }
        });


    }
}
