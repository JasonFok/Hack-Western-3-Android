package com.example.jason.localdealsnotifier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Promotion> promotionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PromotionsAdapter mAdapter;
    private EditText editTextName;
    private EditText editTextAddress;
    private TextView textViewPersons;
    private Button buttonSave;

    private FirebaseDatabase mDatabase;
    private DatabaseReference userInfoRef;
    private DatabaseReference allPromotionsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        textViewPersons = (TextView) findViewById(R.id.textViewPersons);

        mDatabase = FirebaseDatabase.getInstance();
        userInfoRef = mDatabase.getReference("UserInput");
        allPromotionsRef = mDatabase.getReference("FilteredPromotions");

        //Value event listener for realtime data update
        allPromotionsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Getting the data from snapshot
                String string = "";
                final List<Promotion> promotionListTemp = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Promotion promo = postSnapshot.getValue(Promotion.class);
                    promotionListTemp.add(promo);
                    //Adding it to a string
                    string = string + "Company: " + promo.getCompany() + "\nMessage: " + promo.getMessage() + "\n\n";
                }

                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

                mAdapter = new PromotionsAdapter(promotionListTemp, MainActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

                recyclerView.setAdapter(mAdapter);

                //Displaying it on textview
                textViewPersons.setText(string);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Creating firebase object
//                // Write a message to the database
//
//                //Getting values to store
//                String location = editTextAddress.getText().toString().trim();
//
//                //Creating UserInput object
//                UserInput userInput = new UserInput();
//
//                //Adding values
//                userInput.setLocation(location);
//
//                //Storing values to firebase
//                userInfoRef.setValue(userInput);
//
//            }
//        });
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        mAdapter = new PromotionsAdapter(promotionList, this);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
////
        prepareMovieData();
    }

    private void prepareMovieData() {
        Promotion promotion = new Promotion("Subway", "50% off cold cut combo", "0.5km");
        promotionList.add(promotion);
        promotion = new Promotion("Pizza Pizza", "50% off peperoni or cheese slice", "0.5km");
        promotionList.add(promotion);
    }

}