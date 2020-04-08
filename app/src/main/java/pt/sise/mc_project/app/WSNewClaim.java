package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.app.activities.HomeActivity;
import pt.sise.mc_project.datamodel.ClaimUnprocessed;

public class WSNewClaim extends AsyncTask<String, String, Boolean> {

    public final static String TAG = "CallTask";

    private String _title;
    private String _date;
    private String _plateNumber;
    private String _description;
    private int _sessionId;
    private GlobalState _globalState;
    private HomeActivity _homeActivity;

    public WSNewClaim(String title, String date, String plateNumber, String description, int sessionId, GlobalState globalState, HomeActivity homeActivity){
        this._title=title;
        this._date=date;
        this._plateNumber=plateNumber;
        this._description=description;
        this._sessionId=sessionId;
        this._globalState=globalState;
        this._homeActivity=homeActivity;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            boolean r = WSHelper.submitNewClaim(_sessionId, _title, _date,_plateNumber,_description);
            Log.d(TAG, "Submit new claim result => " + r);
            return r;

        } catch (Exception e) {
            ClaimUnprocessed claimUnprocessed = new ClaimUnprocessed(_title, _date, _plateNumber, _description);
            _globalState.get_ClaimUnprocessedList().add(claimUnprocessed);
            String claimUnprocessedFile = "ClaimUnprocessed"+ _globalState.get_username() + ".json";
            try {
                String claimUnprocessedJson = JsonCodec.encodeClaimUnprocessed(_globalState.get_ClaimUnprocessedList());
                JsonFileManager.jsonWriteToFile(_homeActivity.getApplicationContext(), claimUnprocessedFile, claimUnprocessedJson);
                return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}
