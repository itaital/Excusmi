package itaital100.gmail.com.Excusmi.Startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import itaital100.gmail.com.Excusmi.R;
import itaital100.gmail.com.Excusmi.Tools.Utils;

public class startup_1 extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_1);
        Utils.setGraphicsRegionTo("en",this);

            Button nextButton = findViewById(R.id.next_button);
            final AppCompatActivity this_start2_Activity = this;

            nextButton.setOnClickListener(
                    new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(getApplicationContext(), startup_2.class);
                    startActivity(startIntent);
                }
            });
    }
    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}


