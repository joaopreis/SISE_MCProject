package pt.sise.mc_project;

import java.util.Date;

public class Claim {
    private int _id;
    private String _title;
    private String _plateNUmber;
    private Date _date;
    private String _status;
    private String _description;

    public Claim(int id, String title){
        this._id=id;
        this._title=title;
    }

    public int get_id() {
        return _id;
    }

    public String get_title() {
        return _title;
    }

    public String get_plateNUmber() {
        return _plateNUmber;
    }

    public Date get_date() {
        return _date;
    }

    public String get_status() {
        return _status;
    }

    public String get_description() {
        return _description;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_plateNUmber(String _plateNUmber) {
        this._plateNUmber = _plateNUmber;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
