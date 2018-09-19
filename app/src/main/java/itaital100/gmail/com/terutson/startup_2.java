
package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class startup_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_2);
        Button nextButton = findViewById(R.id.next_button2);
        final RadioButton agreeButton = findViewById(R.id.agreeBtn);
        final AppCompatActivity this_start2_Activity = this;

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
}
