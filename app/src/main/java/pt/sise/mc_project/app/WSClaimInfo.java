package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.app.activities.ClaimInformationActivity;
import pt.sise.mc_project.datamodel.ClaimRecord;
import pt.sise.mc_project.datamodel.Customer;

public class WSClaimInfo extends AsyncTask<String, String, ClaimRecord> {

    public final static String TAG = "CallTask";

    private int _sessionId;
    private int _claimId;
    private String _username;
    private ClaimInformationActivity _claimInformationActivity;

    public WSClaimInfo(int sessionId,int claimId, String username, ClaimInformationActivity claimInformationActivity){
        this._sessionId=sessionId;
        this._claimId=claimId;
        this._username=username;
        this._claimInformationActivity=claimInformationActivity;

    }

    @Override
    protected ClaimRecord doInBackground(String... strings) {
        try {
            ClaimRecord claimRecord = WSHelper.getClaimInfo(_sessionId, _claimId);
            String claimRecordFileName = "claim"+_claimId+_username+".json";
            String claimJson= JsonCodec.encodeClaimRecord(claimRecord);
            JsonFileManager.jsonWriteToFile(_claimInformationActivity.getApplicationContext(), claimRecordFileName, claimJson);
            return claimRecord;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            String claimRecordFileName = "claim"+_claimId+_username+".json";
            try {
                String claimRecordJson = JsonFileManager.jsonReadFromFile(_claimInformationActivity.getApplicationContext(), claimRecordFileName);
                ClaimRecord jsonClaimRecord= JsonCodec.decodeClaimRecord(claimRecordJson);
                return jsonClaimRecord;
            }catch (Exception a){
                return null;

            }
        }
    }
}
