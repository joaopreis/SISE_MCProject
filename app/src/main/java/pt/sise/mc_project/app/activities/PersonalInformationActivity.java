package pt.sise.mc_project.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.sise.mc_project.InternalProtocol;
import pt.sise.mc_project.R;

public class PersonalInformationActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Insure - PersonalInformation";

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Intent intent=getIntent();

        TextView customerName=(TextView) findViewById(R.id.personalInfoName);
        TextView customerBirthdate=(TextView) findViewById(R.id.personalInfoBirthDate);
        TextView customerNif=(TextView) findViewById(R.id.personalInformationFiscalNumber);
        TextView customerAddress=(TextView) findViewById(R.id.personalInformationAddress);
        TextView customerPolicyNumber=(TextView) findViewById(R.id.personalInformationPolicyNumber);

        customerName.setText(intent.getStringExtra(InternalProtocol.CUSTOMER_NAME));
        customerBirthdate.setText(intent.getStringExtra(InternalProtocol.CUSTOMER_BIRTHDATE));
        customerAddress.setText(intent.getStringExtra(InternalProtocol.CUSTOMER_ADDRESS));
        customerNif.setText(intent.getStringExtra(InternalProtocol.CUSTOMER_NIF));
        customerPolicyNumber.setText(intent.getStringExtra(InternalProtocol.CUSTOMER_POLICY_NUMBER));

        buttonBack = (Button) findViewById(R.id.personalInformationBackButton);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // just finish the current activity
                finish();
            }
        });
    }
}