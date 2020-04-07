package pt.sise.mc_project.datamodel;

import java.io.Serializable;

public class ClaimUnprocessed implements Serializable {

    private String _title;
    protected String _date;
    protected String _plateNumber;
    protected String _description;

    public ClaimUnprocessed(String title, String date, String plateNumber, String description ) {
        this._title = title;
        this._date = date;
        this._plateNumber = plateNumber;
        this._description = description;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_plateNumber() {
        return _plateNumber;
    }

    public void set_plateNumber(String _plateNumber) {
        this._plateNumber = _plateNumber;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
