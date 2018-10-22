
package itaital100.gmail.com.terutson.Startup;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import itaital100.gmail.com.terutson.R;
import itaital100.gmail.com.terutson.Tools.Utils;

public class startup_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_2);
        Button nextButton = findViewById(R.id.next_button2);
        final RadioButton agreeButton = findViewById(R.id.agreeBtn);
        final AppCompatActivity this_start2_Activity = this;
        final TextView thanksText =findViewById(R.id.textView2);
        Utils.setGraphicsRegionTo("en",this);
        thanksText.setShadowLayer(2, 0, 0, Color.BLACK);

        nextButton.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!agreeButton.isChecked())
                {
                    Utils.openConfirmDialog("יש ללחוץ על כפתור ההסכמה בכדי להמשיך","אוקיי",this_start2_Activity);
                }
                else
                {
                     Intent startIntent = new Intent(getApplicationContext(), startup_3.class);
                    //start activity://
                    startActivity(startIntent);
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}
