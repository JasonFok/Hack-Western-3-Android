package com.example.jason.localdealsnotifier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

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

        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);

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
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    FilteredPromotions filteredPromotions = postSnapshot.getValue(FilteredPromotions.class);
                    //Adding it to a string
                    string = string + "Company: " + filteredPromotions.getCompanyName() + "\nLocation: " + filteredPromotions.getLocation() + "\n\n";
                }
                //Displaying it on textview
                textViewPersons.setText(string);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating firebase object
                // Write a message to the database

                //Getting values to store
                String location = editTextAddress.getText().toString().trim();

                //Creating UserInput object
                UserInput userInput = new UserInput();

                //Adding values
                userInput.setLocation(location);

                //Storing values to firebase
                userInfoRef.setValue(userInput);

            }
        });
    }


}