package sg.edu.rp.c346.id20009530.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView userAmount;
    TextView numberPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView discount;
    RadioGroup rgPayment;
    Button split;
    Button reset;
    TextView totalBill; // to store the total bill
    TextView pays; // stores how much 1 person should pay


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userAmount = findViewById(R.id.amount);
        numberPax = findViewById(R.id.numberPax);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        discount = findViewById(R.id.discount);
        rgPayment = findViewById(R.id.rgPayment);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        totalBill = findViewById(R.id.totalBill);
        pays = findViewById(R.id.pays);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userAmount.getText().toString().isEmpty()) {
                    double amount = 0.0; // assigning variable

                    if (!svs.isChecked() && !gst.isChecked()) { // checks if the option is selected by user
                        amount = Double.parseDouble(userAmount.getText().toString());
                    } else if (svs.isChecked() && gst.isChecked()) { // includes gst and svs charge
                        amount = Double.parseDouble(userAmount.getText().toString()) * 1.1;
                    } else if (svs.isChecked() && !gst.isChecked()) { // includes only svs charge
                        amount = Double.parseDouble(userAmount.getText().toString()) * 1.07;
                    } else {
                        amount = Double.parseDouble(userAmount.getText().toString()) * 1.17;
                    }
                    if (!discount.getText().toString().isEmpty()) {
                        amount = 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    totalBill.setText("Total Bill: $" + String.format("%.2f", amount));
                    int perPerson = Integer.parseInt(numberPax.getText().toString());
                    if (perPerson != 1) {
                        pays.setText("Each Pays: $" + String.format("%.2f", amount / perPerson));
                    } else {
                        pays.setText("Each Pays: $" + amount);
                    }
                }
                if (userAmount.getText().toString().isEmpty() && numberPax.getText().toString().isEmpty() && discount.getText().toString().isEmpty()) {
                    System.out.println("Please fill up all the blanks");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // brings everything back to normal
                userAmount.setText("");
                numberPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });


    }
}