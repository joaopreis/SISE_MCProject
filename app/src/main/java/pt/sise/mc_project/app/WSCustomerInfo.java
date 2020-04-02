package pt.sise.mc_project.app;

import android.os.AsyncTask;
import android.util.Log;

import pt.sise.mc_project.datamodel.Customer;

public class WSCustomerInfo extends AsyncTask<String, String, Customer> {

    public final static String TAG = "CallTask";

    private int _sessionId;

    public WSCustomerInfo(int sessionId) {
        this._sessionId = sessionId;
    }



    @Override
    protected Customer doInBackground(String... strings) {
        try {
            Customer customer = WSHelper.getCustomerInfo(_sessionId);
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");

            } else {
                Log.d(TAG, "Get customer info result => " + customer.toString());
                return customer;
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }
}
