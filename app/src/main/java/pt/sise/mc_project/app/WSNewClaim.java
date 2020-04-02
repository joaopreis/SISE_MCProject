package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

public class WSNewClaim extends AsyncTask<String, String, Boolean> {

    public final static String TAG = "CallTask";


    private String _title;
    private String _date;
    private String _plateNumber;
    private String _description;
    private int _sessionId;

    public WSNewClaim(String title, String date, String plateNumber, String description, int sessionId){
        this._title=title;
        this._date=date;
        this._plateNumber=plateNumber;
        this._description=description;
        this._sessionId=sessionId;
    }


    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            boolean r = WSHelper.submitNewClaim(_sessionId, _title, _date,_plateNumber,_description);
            Log.d(TAG, "Submit new claim result => " + r);
            return r;

        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}
