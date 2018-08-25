package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hw = (Button)findViewById(R.id.btn_hw);
        Button meeting = (Button)findViewById(R.id.btn_meeting);
        Button ex = (Button)findViewById(R.id.btn_ex);
        Button work = (Button)findViewById(R.id.btn_work);

        hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                startIntent.putExtra("com.gmail.itaital100.HW", "תירוץ כלשהו של שיעורי בית");
                startActivity(startIntent);
            }
        });

        meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                startIntent.putExtra("com.gmail.itaital100.meeting", "תירוץ כלשהו על פגישה");
                startActivity(startIntent);
            }
        });

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                startIntent.putExtra("com.gmail.itaital100.ex", "תירוץ כלשהו על מטלה");
                startActivity(startIntent);
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                startIntent.putExtra("com.gmail.itaital100.work", "תירוץ כלשהו על עבודה");
                startActivity(startIntent);
            }
        });

    }
}
