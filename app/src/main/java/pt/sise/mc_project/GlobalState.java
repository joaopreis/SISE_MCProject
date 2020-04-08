package pt.sise.mc_project;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pt.sise.mc_project.datamodel.ClaimItem;
import pt.sise.mc_project.datamodel.ClaimUnprocessed;

public class GlobalState extends Application {

    private List<ClaimItem> _claimItemList;
    private List<ClaimUnprocessed> _ClaimUnprocessedList= new ArrayList<ClaimUnprocessed>();
    private int _sessionId;
    private String _username;

    public Context get_logInContext() {
        return _logInContext;
    }

    public void set_logInContext(Context _logInContext) {
        this._logInContext = _logInContext;
    }

    private Context _logInContext;

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    private String _password;

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

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

    public List<ClaimUnprocessed> get_ClaimUnprocessedList() {
        return _ClaimUnprocessedList;
    }

    public void set_ClaimUnprocessedList(List<ClaimUnprocessed> _ClaimUnprocessedList) {
        this._ClaimUnprocessedList = _ClaimUnprocessedList;
    }
}
