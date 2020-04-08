package pt.sise.mc_project.app;

import android.os.AsyncTask;

public class WSHello extends AsyncTask<Void,Void,Boolean> {

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            WSHelper.hello("online");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
