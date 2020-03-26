package pt.sise.mc_project;

import android.app.Application;

import java.util.ArrayList;

public class GlobalState extends Application {

    private ArrayList<Claim> _claimList;

    public void set_claimList(ArrayList<Claim> _claimList) {
        this._claimList = _claimList;
    }

    public ArrayList<Claim> get_claimList() {
        return _claimList;
    }
}
