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

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSHelper;
import pt.sise.mc_project.app.WSLogIn;
import pt.sise.mc_project.app.WSLogOut;

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
                
                try {
                    String fileName="logOut"+username.getText().toString()+".json";
                    try{
                        String logOutJson=JsonFileManager.jsonReadFromFile(globalState.get_logInContext(),fileName);
                        Log.d("SISE","Não cheguei aqui.");
                        int jsonLogOut= JsonCodec.decodeLogOut(logOutJson);
                        Log.d("SISE","O erro é aqui??");
                        new WSLogOut(jsonLogOut,username.getText().toString(),globalState.get_logInContext());
                        Log.d("SISE","O erro é aqui aqui??");
                        LogInActivity.this.deleteFile(fileName);
                        Log.d("SISE","Ou é aqui??");
                    }catch (Exception e){
                        Log.d("SISE","Não me encontraram.");
                    }
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
                        globalState.set_username(username.getText().toString());
                        globalState.set_password(password.getText().toString());
                        globalState.set_logInContext(LogInActivity.this);
                        startActivity(intent);
                    }
                } catch (ExecutionException a) {
                    a.printStackTrace();
                } catch (InterruptedException b) {
                    b.printStackTrace();
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
