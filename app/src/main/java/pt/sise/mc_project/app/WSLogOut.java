package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

public class WSLogOut extends AsyncTask<String, String, Boolean> {

    public final static String TAG = "CallTask";

    private int _sessionId;

    public WSLogOut(int sessionId){
        this._sessionId=sessionId;
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
            publishProgress("failed.\n");
        }
        return false;
    }
}
