package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSLogIn;

public class LogInActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button logInButton;
    private int _sessionId;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private  void checkFieldsForEmptyValues(){
        Button btn = (Button) findViewById(R.id.log_in_button);

        String s1 = username.getText().toString();
        String s2 = password.getText().toString();

        if (s1.length() > 0 && s2.length() > 0) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Log.d("SISE","Log In Created!");

        final GlobalState globalState = (GlobalState) getApplicationContext();

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        logInButton= (Button) findViewById(R.id.log_in_button);


        username.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        checkFieldsForEmptyValues();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create separate AsynkTasks that behave differently for each request in different app
                try {
                    int id=new WSLogIn(username.getText().toString(),password.getText().toString()).execute().get();
                    Log.d("SISE","ID VALUE:"+id);
                    if (id==0) {
                        Toast.makeText(logInButton.getContext(), "Invalid log in: Try again!", Toast.LENGTH_LONG).show();
                    }else if (id == -1){
                        Toast.makeText(logInButton.getContext(), "Network Error: Failed to access database!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(logInButton.getContext(),"Log In successful!",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(LogInActivity.this, HomeActivity.class);
                        _sessionId=id;
                        globalState.set_sessionId(_sessionId);
                        startActivity(intent);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                username.setText("");
                password.setText("");

            Log.d("SISE","Log In Button Clicked.");
            Log.d("SISE",username.getText().toString());
            Log.d("SISE",password.getText().toString());
            }
        });

    }
}
