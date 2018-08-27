package itaital100.gmail.com.terutson;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;


public class DetailsActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

         tv = (TextView) findViewById(R.id.txt_teruson);
        Button copy = (Button) findViewById(R.id.btn_copy);

        //----------------------------------------------------------------------------------------------
    /*
       when press copy it will copy the text to the clipboard
       and it will show a msg that the text was copied.
     */

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text label", tv.getText());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(),"Text pasted",Toast.LENGTH_SHORT).show();
            }
        });

        if(getIntent().hasExtra("com.gmail.itaital100.HW"))
        {
            String text = getIntent().getExtras().getString("com.gmail.itaital100.HW");
            tv.setText(text);
        }
        if(getIntent().hasExtra("com.gmail.itaital100.meeting"))
        {
            String text = getIntent().getExtras().getString("com.gmail.itaital100.meeting");
            tv.setText(text);
        }

        if(getIntent().hasExtra("com.gmail.itaital100.ex"))
        {
            String text = getIntent().getExtras().getString("com.gmail.itaital100.ex");
            tv.setText(text);
        }

        if(getIntent().hasExtra("com.gmail.itaital100.work"))
        {
            String text = getIntent().getExtras().getString("com.gmail.itaital100.work");
            tv.setText(text);
        }
    }
}
