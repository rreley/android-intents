package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.ualr.intentsassignment.databinding.ContactInfoActivityBinding;

import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {

    TextView name, phone, email, address, website;
    private ContactInfoActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
        mBinding = ContactInfoActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        populateLayout();
    }
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    public void populateLayout() {
        name = findViewById(R.id.full_name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        website = findViewById(R.id.website);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("array");
        String n = (contact.getFirstName() + " " + contact.getLastName());
        String p = contact.getPhoneNumber();
        String e = contact.getEmailAddress();
        String a = contact.getAddress();
        String w = contact.getWebsite();

        name.setText(n);
        phone.setText(p);
        email.setText(e);
        address.setText(a);
        website.setText(w);
    }
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    public void onPhoneChipClick(View view) {
        Intent i = getIntent();
        Contact contact = (Contact) i.getSerializableExtra("array");
        String phoneNumberUri = "tel:";
        phoneNumberUri = phoneNumberUri.concat(contact.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
        startActivity(intent);
    }
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    public void onTextChipClick(View view) {
        Intent i = getIntent();
        Contact contact = (Contact) i.getSerializableExtra("array");
        String smsUri = "smsto:";
        smsUri = smsUri.concat(contact.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
        intent.putExtra("sms_body","");
        startActivity(intent);
    }
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    public void onEmailChipClick(View view) {
        Intent i = getIntent();
        Contact contact = (Contact) i.getSerializableExtra("array");
        String[] emailReceiverList = {contact.getEmailAddress()};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        startActivity(intent);
    }
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    public void onMapChipClick(View view) {
        Intent i = getIntent();
        Contact contact = (Contact) i.getSerializableExtra("array");
        String place = contact.getAddress();
        String placeUri = String.format("geo:0,0?q=(%s)", place);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(intent);
    }
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step
    public void onWebChipClick(View view) {
        Intent i = getIntent();
        Contact contact = (Contact) i.getSerializableExtra("array");
        String webUri = "https://";
        webUri= webUri.concat(contact.getWebsite());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
        startActivity(intent);
    }
}
