package com.predator.medicon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private ListView infoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user= auth.getCurrentUser();

        textViewUserEmail = findViewById(R.id.textViewUserEmail);

        textViewUserEmail.setText("Welcome " + user.getEmail());

        buttonLogout = findViewById(R.id.buttonLog);

        buttonLogout.setOnClickListener(this);

        infoListView = findViewById(R.id.infoListView);

        String[] Option = {"Specialist","Blood Bank","Hospital","Ambulance"};

        ListAdapter optionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Option);
        infoListView.setAdapter(optionAdapter);

        infoListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String Option = String.valueOf(parent.getItemAtPosition(position));

                        if(position == 0){
                            Intent intent = new Intent(view.getContext(), SpecialistActivity.class);
                            startActivityForResult(intent,0);
                        }
                        if(position == 1){
                            Intent intent = new Intent(view.getContext(), BloodBank.class);
                            startActivityForResult(intent,1);
                        }
                        if(position == 2){
                            Intent intent = new Intent(view.getContext(), HospitalActivity.class);
                            startActivityForResult(intent,2);
                        }
                        if (position == 3) {
                            Intent intent = new Intent(view.getContext(), AmbulanceActivity.class);
                            startActivityForResult(intent,3);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogout){
            auth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
