package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.app.activities.NewClaimActivity;

public class WSListPlates extends AsyncTask<String, String, List<String>> {

    public final static String TAG = "CallTask";

    private int _sessionId;
    private String _username;
    private NewClaimActivity _newClaimActivity;

    public WSListPlates(int sessionId, String username, NewClaimActivity newClaimActivity){
        this._sessionId=sessionId;
        this._username=username;
        this._newClaimActivity=newClaimActivity;
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
                String platesFileName="plates"+_username+".json";
                String platesJson= JsonCodec.encodePlateList(plateList);
                JsonFileManager.jsonWriteToFile(_newClaimActivity.getApplicationContext(),platesFileName,platesJson);
                return plateList;
            } else {
                Log.d(TAG, "List plates result => null.");
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            String platesFileName="plates"+_username+".json";
            try{
                String platesJson=JsonFileManager.jsonReadFromFile(_newClaimActivity.getApplicationContext(),platesFileName);
                List<String> jsonPlates=JsonCodec.decodePlateList(platesJson);
                return jsonPlates;
            } catch (Exception a){
                return null;
            }
        }
        return null;
    }
}
