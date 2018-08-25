package itaital100.gmail.com.terutson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tv = (TextView) findViewById(R.id.txt_teruson);
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
