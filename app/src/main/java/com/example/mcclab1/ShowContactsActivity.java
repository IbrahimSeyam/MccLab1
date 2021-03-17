package com.example.mcclab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Iterator;

public class ShowContactsActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);

        firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ArrayList<Contact> contacts = new ArrayList<>();

        ContactsAdapter adapter = new ContactsAdapter(this, contacts);

        recyclerView.setAdapter(adapter);

//        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
//        lm.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(lm);
//
        DividerItemDecoration di = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(di);


        firebaseFirestore.collection("contacts")
                .get()

                .addOnSuccessListener(runnable -> {

                    Iterator<QueryDocumentSnapshot> iterator = runnable.iterator();
                    while (iterator.hasNext()) {
                        Contact contact = iterator.next().toObject(Contact.class);
                        contacts.add(contact);
                    }
                })

                .addOnCompleteListener(runnable -> {
                    if (runnable.isSuccessful()) {
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "dfasd", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

//        CollectionReference collectionReference = firebaseFirestore.collection("contacts");
//        Task<QuerySnapshot> task = collectionReference.get();
//        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                Iterator<QueryDocumentSnapshot> iterator = queryDocumentSnapshots.iterator();
//                while (iterator.hasNext()){
//                    Contact s = iterator.next().toObject(Contact.class);
//                    contacts.add(s);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });

    }}