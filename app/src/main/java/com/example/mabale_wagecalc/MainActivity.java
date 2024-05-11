package com.example.mabale_wagecalc;

import
        androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    EditText HrlyRte, NoofHrs;

    TextView payment, overtime, taxPaym, totalPaym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inputs
        HrlyRte = findViewById(R.id.Hrly_Rteinput);
        NoofHrs = findViewById(R.id.NoOfhrsinput);

        //Outputs

        payment = findViewById(R.id.PaymentOut);
        overtime = findViewById(R.id.OvertimeOut);
        taxPaym = findViewById(R.id.TaxOut);
        totalPaym = findViewById(R.id.TotalOut);

    }

    public void calculation(View view) {
        try {
            double workedHours = Double.parseDouble(NoofHrs.getText().toString());
            double hourlyRate = Double.parseDouble(HrlyRte.getText().toString());

            double paymentP = 0;
            double overtimeP = 0;
            double totalP = 0;
            double tax = 0;

            if (workedHours <= 40) {
                paymentP = workedHours * hourlyRate;
                overtimeP = 0;
                totalP = paymentP;
            } else {
                paymentP = 40 * hourlyRate;
                overtimeP = (workedHours - 40) * hourlyRate;
                totalP = paymentP + overtimeP;
            }

            tax = totalP * 0.18;

            payment.setText(String.format("%.2f", paymentP));
            overtime.setText(String.format("%.2f", overtimeP));
            totalPaym.setText(String.format("%.2f", totalP));
            taxPaym.setText(String.format("%.2f", tax));

        } catch (Exception e) {

            TextView err = findViewById(R.id.error);
            err.setText("Opps! Enter numbers to calculate!");
            payment.setText("");
            overtime.setText("");
            totalPaym.setText("");
            taxPaym.setText("");

        } finally {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    TextView err = findViewById(R.id.error);
                    err.setText("");
                }
            }, 2000);
        }

    }
}
