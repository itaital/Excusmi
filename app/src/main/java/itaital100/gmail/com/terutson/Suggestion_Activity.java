package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Suggestion_Activity extends AppCompatActivity {

    //ComboBox items:
    String[] items = {"עבודה", "פגישה", "בריחה מדייט", "מטלה", "שיעורי בית", "אירוע"};
    Spinner myComboBox;
    //email:
    Button btn_sendEmail;
    EditText myEditBox;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        System.out.println("debug: Starting suggestion activity");
        setContentView(R.layout.activity_suggestion);
        Utils.setGraphicsRegionTo("eng",this);
        System.out.println("debug: .xml loaded");
        //init all components:
        init_editBox();
        init_ComboBox();
        init_SendButton();
    }
    //----------------------------------------------------------------------------------------------
    void init_editBox()
    {
        myEditBox = (EditText) findViewById(R.id.txt_edit);
    }
    //----------------------------------------------------------------------------------------------
    void init_SendButton()
    {
        btn_sendEmail = (Button) findViewById(R.id.btn_email);
        btn_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }
    //----------------------------------------------------------------------------------------------
    void init_ComboBox()
    {
        //get the spinner from the xml.
        myComboBox = findViewById(R.id.spinner);
        //create an adapter to describe how the items are displayed, adapters
        // are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        myComboBox.setAdapter(adapter);
    }
    //----------------------------------------------------------------------------------------------
    protected void sendEmail() {
        if(myEditBox.length() ==0)
        {
            Utils.openConfirmDialog("נא להכניס תירוץ בבקשה","אישור",this);
            return;
        }
        String comboTxt = myComboBox.getSelectedItem().toString();
        Log.i("Send email", "");
        String SmyEditBox = myEditBox.getText().toString();
        String[] TO = {"terutson@gmail.com"};
        String[] CC = {"ArielBerkovich1@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Terutson support");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "הצעה לתירוץ חדש:" + "\n" + "קטגוריה: " + comboTxt +"\n" + SmyEditBox);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }
}