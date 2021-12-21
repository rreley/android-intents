package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.ualr.intentsassignment.model.Contact;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.ualr.intentsassignment.databinding.ContactFormActivityBinding;

public class ContactFormActivity extends AppCompatActivity {

    Button save_contact;
    EditText first, last, phone, email, address, website;
    private ContactFormActivityBinding mBinding;
    private List<Contact> ContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    // TODO 01. Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
        mBinding = ContactFormActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        ContactList = new ArrayList<>();

        first = findViewById(R.id.first_name);
        last = findViewById(R.id.last_name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        website = findViewById(R.id.website);
        save_contact = findViewById(R.id.save_contact);
        save_contact.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switchSubmit();
        }
    });
}
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity
    private void switchSubmit() {
        String f, l, p, e, a, w;
        f = first.getText().toString();
        l = last.getText().toString();
        p = phone.getText().toString();
        e = email.getText().toString();
        a = address.getText().toString();
        w = website.getText().toString();

        Contact contact = new Contact();
        contact.setFirstName(f);
        contact.setLastName(l);
        contact.setPhoneNumber(p);
        contact.setEmailAddress(e);
        contact.setAddress(a);
        contact.setWebsite(w);
        ContactList.add(contact);

        Intent switchActivityIntent = new Intent(this, ContactInfoActivity.class);
        switchActivityIntent.putExtra("array", (Serializable) contact);
        startActivity(switchActivityIntent);
    }
}
