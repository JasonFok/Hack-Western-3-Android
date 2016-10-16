package com.example.jason.localdealsnotifier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PromoDetailsActivity extends AppCompatActivity {

    public static final String COMPANY_DATA = "companyData";
    public static final String MESSAGE_DATA = "messageData";
    public static final String DISTANCE_DATA = "distanceData";

    private String company;
    private String message;
    private String distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_details);

        if (getIntent() != null) {
            company = getIntent().getStringExtra(COMPANY_DATA);
            message = getIntent().getStringExtra(MESSAGE_DATA);
            distance = getIntent().getStringExtra(DISTANCE_DATA);
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_promo_details);
        TextView companyTextView = (TextView)linearLayout.findViewById(R.id.companyD);
        TextView messageTextView = (TextView)linearLayout.findViewById(R.id.messageD);
        TextView distanceTextView = (TextView)linearLayout.findViewById(R.id.distanceD);

        companyTextView.setText(company);
        messageTextView.setText(message);
        distanceTextView.setText(distance);





    }
}
