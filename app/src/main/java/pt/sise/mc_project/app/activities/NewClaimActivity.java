package pt.sise.mc_project.app.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import pt.sise.mc_project.GlobalState;
import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.JsonCodec;
import pt.sise.mc_project.JsonFileManager;
import pt.sise.mc_project.R;
import pt.sise.mc_project.app.WSListPlates;

public class NewClaimActivity extends AppCompatActivity {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListenerDisplayDate;
    String _plateNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);
        Log.d("SISE","New Claim Activity Created.");

        GlobalState globalstate=(GlobalState) getApplicationContext();
        int _sessionId=globalstate.get_sessionId();
        String _username=globalstate.get_username();
        List<String> plates = null;

        try {
            plates=new WSListPlates(_sessionId,_username,NewClaimActivity.this).execute().get();
            if (plates==null){
                Toast.makeText(getApplicationContext(),"Impossible to access plates list.",Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_CANCELED);
                finish();
                return;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        final Button cancelButton=findViewById(R.id.newClaimCancelButton);
        final Button submitButton=findViewById(R.id.newClaimSubmitButton);
        final EditText editClaimTitle=findViewById(R.id.claimTitle);
        final Spinner editPlateNumber=findViewById(R.id.newClaimPlateNumber);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, plates);
        editPlateNumber.setAdapter(adapter);
        editPlateNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String plateNumber=(String) parent.getItemAtPosition(position);
                _plateNumber=plateNumber;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText editClaimDescription=findViewById(R.id.claimDescription);


        mDisplayDate = (TextView) findViewById(R.id.claimDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NewClaimActivity.this,android.R.style.Theme_Black, mDateSetListenerDisplayDate, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListenerDisplayDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = month +"/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("SISE","Submit Button Clicked.");
                String claimTitle=editClaimTitle.getText().toString();
                Log.d("SISE",_plateNumber);
                String claimDate=mDisplayDate.getText().toString();
                String claimDescription=editClaimDescription.getText().toString();

                if (claimDate.equals("")||claimDescription.equals("")||claimTitle.equals("")||_plateNumber.equals("")){
                    Toast.makeText(v.getContext(), "Fill all the information please", Toast.LENGTH_LONG).show();
                    return;
                }

                // return an intent containing the claim information
                Intent resultIntent = new Intent();
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_TITLE,claimTitle);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_PLATE_NUMBER,_plateNumber);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DATE,claimDate);
                resultIntent.putExtra(InternalProtocol.KEY_NEW_CLAIM_DESCRIPTION,claimDescription);
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SISE","Back Button Clicked.");
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }
}
