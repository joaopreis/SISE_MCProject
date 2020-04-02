package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.sise.mc_project.datamodel.ClaimItem;

public class WSListClaims  extends AsyncTask<String, String, List<ClaimItem>> {

    public final static String TAG = "CallTask";

    private int _sessionId;

    public WSListClaims(int sessionId){
     this._sessionId=sessionId;
    }

    @Override
    protected List<ClaimItem> doInBackground(String... strings) {
        try {
            List<ClaimItem> claimItemList = WSHelper.listClaims(_sessionId);
            if (claimItemList != null) {
                String m = claimItemList.size() > 0 ? "" : "empty array";
                for (ClaimItem claimItem : claimItemList ) {
                    m += " ("+ claimItem.toString() + ")";
                }
                Log.d(TAG, "List claim item result => " + m);
            } else {
                Log.d(TAG, "List claim item result => null.");
            }
            return claimItemList;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }
}
