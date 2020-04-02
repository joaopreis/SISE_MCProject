package pt.sise.mc_project;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import pt.sise.mc_project.datamodel.ClaimItem;

public class GlobalState extends Application {

    private List<ClaimItem> _claimItemList;
    private int _sessionId;

    public int get_sessionId(){
        return this._sessionId;
    }

    public void set_sessionId(int sessionId){
        this._sessionId=sessionId;

    }

    public void set_claimItemList(List<ClaimItem> _claimItemList) {
        this._claimItemList = _claimItemList;
    }

    public List<ClaimItem> get_claimItemList() {
        return _claimItemList;
    }
}
