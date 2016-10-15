package com.example.jason.localdealsnotifier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private List<Promotion> promotionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PromotionsAdapter mPromotionsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private PromotionsAdapter mRecipeAdapter;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
//    private EditText editTextName;
//    private EditText editTextAddress;
//    private TextView textViewPersons;
//    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        mAdapter = new PromotionsAdapter(promotionList);



        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        childRef = mDatabaseRef.child("recipes");
        mPromotionsAdapter = new PromotionsAdapter(Promotion.class, R.layout.promotion_list_row, PromotionsAdapter.MyViewHolder.class, childRef, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mPromotionsAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

        Firebase.setAndroidContext(this);

//        buttonSave = (Button) findViewById(R.id.buttonSave);
//        editTextName = (EditText) findViewById(R.id.editTextName);
//        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
//
//        textViewPersons = (TextView) findViewById(R.id.textViewPersons);

//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Creating firebase object
                Firebase ref = new Firebase(Config.FIREBASE_URL);
//
//                //Getting values to store
//                String name = editTextName.getText().toString().trim();
//                String address = editTextAddress.getText().toString().trim();
//
//                //Creating Promotion object
//                Promotion promotion = new Promotion();
//
//                //Adding values
//                promotion.setName(name);
//                promotion.setAddress(address);
//
//                //Storing values to firebase
//                ref.child("Promotion").setValue(promotion);
//
//
                //Value event listener for realtime data update
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            //Getting the data from snapshot
                            Promotion promotion = postSnapshot.getValue(Promotion.class);

                            //Adding it to a string
                            String string = "Name: "+ promotion.getCompany()+"\nAddress: "+ promotion.getMessage()+"\n\n";

                            //Displaying it on textview
//                            textViewPersons.setText(string);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }
                });
//
//            }
//        });
    }

    private void prepareMovieData() {
        Promotion promotion = new Promotion("Subway", "50% off cold cut combo", "0.4km");
        promotionList.add(promotion);
        promotion = new Promotion("Pizza Pizza", "50% off peperoni or cheese slice", "0.3km");
        promotionList.add(promotion);
    }

}