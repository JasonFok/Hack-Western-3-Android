package com.example.jason.localdealsnotifier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PromoDetailsActivity extends AppCompatActivity {

    public static final String COMPANY_DATA = "companyData";
    public static final String MESSAGE_DATA = "messageData";
    public static final String DISTANCE_DATA = "distanceData";

    private String company;
    private String message;
    private String distanceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_details);

        if (getIntent() != null) {
            company = getIntent().getParcelableExtra(COMPANY_DATA);
            message = getIntent().getParcelableExtra(MESSAGE_DATA);
            distanceData = getIntent().getParcelableExtra(DISTANCE_DATA);

        }
    }
}
