package pt.sise.mc_project;

import android.app.Application;

import java.util.ArrayList;

import pt.sise.mc_project.datamodel.ClaimItem;

public class GlobalState extends Application {

    private ArrayList<ClaimItem> _claimItemList;

    public void set_claimItemList(ArrayList<ClaimItem> _claimItemList) {
        this._claimItemList = _claimItemList;
    }

    public ArrayList<ClaimItem> get_claimItemList() {
        return _claimItemList;
    }
}
