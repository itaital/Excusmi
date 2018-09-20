package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class startup_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_1);

            Button nextButton = findViewById(R.id.next_button);
            final AppCompatActivity this_start2_Activity = this;

            nextButton.setOnClickListener(   new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     Intent startIntent = new Intent(getApplicationContext(), startup_2.class);
                    startActivity(startIntent);
                }
            });
        }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}


