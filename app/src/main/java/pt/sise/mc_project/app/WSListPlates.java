package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class WSListPlates extends AsyncTask<String, String, List<String>> {

    public final static String TAG = "CallTask";

    private int _sessionId;

    public WSListPlates(int sessionId){
        this._sessionId=sessionId;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        try {
            List<String> plateList = WSHelper.listPlates(_sessionId);
            if (plateList != null) {
                String m = plateList.size() > 0 ? "" : "empty array";
                for (String plate: plateList) {
                    m += " ("+ plate + ")";
                }
                Log.d(TAG, "List plates result => " + m);
                return plateList;
            } else {
                Log.d(TAG, "List plates result => null.");
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}
