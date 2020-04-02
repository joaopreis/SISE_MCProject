package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

public class WSLogIn  extends AsyncTask<String, String, Integer> {

    public final static String TAG = "CallTask";

    private String _username;
    private String _password;

    public WSLogIn(String username,String password){
        this._username=username;
        this._password=password;

    }
    @Override
    protected Integer doInBackground(String... strings) {
        int sessionId=-1;

        publishProgress("Testing method call login wrong...");
        try {
            sessionId = WSHelper.login(_username, _password);        // username doesn't exist
            Log.d(TAG, "Login result => " + sessionId);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return sessionId;
    }

}
