package pt.sise.mc_project.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.app.activities.SettingsActivity;

public class WSLogOut extends AsyncTask<String, String, Boolean> {

    public final static String TAG = "CallTask";

    private int _sessionId;
    private String _username;
    private Context _context;

    public WSLogOut(int sessionId, String _username, Context context){
        this._sessionId=sessionId;
        this._username=_username;
        this._context=context;
    }


    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            boolean result = WSHelper.logout(_sessionId);
            Log.d(TAG, "Logout result => " + result);
            publishProgress("    ok.\n");
            return result;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            String logOutFileName="logOut"+_username+".json";
            try {
                String logOutJson= JsonCodec.encodeLogOut(_sessionId);
                JsonFileManager.jsonWriteToFile(_context,logOutFileName,logOutJson);
                Log.d("SISE","Log out file written");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

        }
        return false;
    }
}
