package pt.sise.mc_project.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.app.activities.HomeActivity;
import pt.sise.mc_project.datamodel.Customer;

public class WSCustomerInfo extends AsyncTask<String, String, Customer> {

    public final static String TAG = "CallTask";

    private int _sessionId;
    private String _username;
    private HomeActivity _homeActivity;

    public WSCustomerInfo(int sessionId,String username,HomeActivity homeActivity) {
        this._sessionId = sessionId;
        this._username=username;
        this._homeActivity=homeActivity;
    }



    @Override
    protected Customer doInBackground(String... strings) {
        try {
            Customer customer = WSHelper.getCustomerInfo(_sessionId);
            Log.d(TAG, "customerInfo: read from - " + customer.toString());
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");
                String customerFileName = "customer"+_username+".json";
                String customerJson = JsonFileManager.jsonReadFromFile(_homeActivity.getApplicationContext(), customerFileName);
                Log.d(TAG, "customerInfo: read from - " + customerFileName);

                Customer jsonCustomer = JsonCodec.decodeCustomerInfo(customerJson);
                Log.d(TAG, "customerInfo: jsonCustomer - " + jsonCustomer);
                return jsonCustomer;

            } else {
                Log.d(TAG, "Get customer info result => " + customer.toString());
                String customerFileName = "customer"+_username+".json";

                Log.d(TAG, "File name: " + customerFileName);

                String customerJson = JsonCodec.encodeCustomerInfo(customer);
                Log.d(TAG, "Encode customer: " + customerJson);

                JsonFileManager.jsonWriteToFile(_homeActivity.getApplicationContext(), customerFileName, customerJson);
                Log.d(TAG, "customerInfo: written to - " + customerFileName);
                return customer;
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }
}
