
package itaital100.gmail.com.Excusmi.Startup;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import itaital100.gmail.com.Excusmi.Activities.MainActivity;
import itaital100.gmail.com.Excusmi.R;
import itaital100.gmail.com.Excusmi.Tools.Utils;

public class startup_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_2);
        Button nextButton = findViewById(R.id.next_button2);
        final CheckBox agreeButton = findViewById(R.id.agreeBtn);
        final AppCompatActivity this_start2_Activity = this;
        final TextView thanksText = findViewById(R.id.textView2);
        Utils.setGraphicsRegionTo("en", this);
        thanksText.setShadowLayer(2, 0, 0, Color.BLACK);
        nextButton.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Utils.commitVariable("Opened","yes");
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                //start activity://
                startActivity(startIntent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }


}
