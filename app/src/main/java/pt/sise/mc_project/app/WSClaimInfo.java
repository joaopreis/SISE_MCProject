package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

import pt.sise.mc_project.datamodel.ClaimRecord;

public class WSClaimInfo extends AsyncTask<String, String, ClaimRecord> {

    public final static String TAG = "CallTask";

    private int _sessionId;
    private int _claimId;

    public WSClaimInfo(int sessionId,int claimId){
        this._sessionId=sessionId;
        this._claimId=claimId;

    }

    @Override
    protected ClaimRecord doInBackground(String... strings) {
        publishProgress("Testing method call getClaimInfo...");
        try {
            int claimId = 1;
            ClaimRecord claimRecord = WSHelper.getClaimInfo(_sessionId, _claimId);
            if (claimRecord != null) {
                Log.d(TAG, "Get Claim Info result claimId " + claimId + " => " + claimRecord.toString());
            } else {
                Log.d(TAG, "Get Claim Info result claimId " + claimId + " => null.");
            }
            publishProgress("ok.\n");
            return claimRecord;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}
