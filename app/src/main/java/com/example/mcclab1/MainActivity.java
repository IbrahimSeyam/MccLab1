package com.example.mcclab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    EditText nameEd, numberEd, addressEd;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseFirestore = FirebaseFirestore.getInstance();

        nameEd = findViewById(R.id.nameEd);
        numberEd = findViewById(R.id.numberEd);
        addressEd = findViewById(R.id.addressEd);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(view -> {
//            showProgress();
            saveToFB();
        });

//        saveBtn.setOnClickListener(view -> {
//
//            CollectionReference collectionReference = firebaseFirestore.collection("contacts");
//
//            Contact contact = new Contact();
//            contact.setName(nameEd.getText().toString());
//            contact.setNumber(Integer.parseInt(numberEd.getText().toString()));
//            contact.setAddress(addressEd.getText().toString());
//
//            Task<DocumentReference> task = collectionReference.add(contact);
//            task.addOnSuccessListener(documentReference -> {
//                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//            })
//                    .addOnFailureListener(e -> {
//                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    });
//        });

        Button showBtn = findViewById(R.id.showContactBtn);
        showBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ShowContactsActivity.class);
            startActivity(intent);
        });

    }

    private void saveToFB(){
        Map<String, Object> map = new HashMap<>();
        map.put("contact_name",nameEd.getText().toString());
        map.put("contact_number", Integer.parseInt(numberEd.getText().toString()));
        map.put("contact_address", addressEd.getText().toString());
        firebaseFirestore.collection("contacts")
                .add(map)
                .addOnSuccessListener(runnable -> {
                    showProgress();
//                    Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void showProgress(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Saving to Firebase"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }).start();
    }}