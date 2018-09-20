package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class startup_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_3);
        Button finishButton = findViewById(R.id.finish_button);
        final RadioButton radioButtonMale= findViewById(R.id.radioM);
        final RadioButton radioButtonFemale= findViewById(R.id.radioF);
        finishButton.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(radioButtonMale.isChecked())
                {
                    Utils.commitVariable("selected_gender","Male");
                }
                else if(radioButtonFemale.isChecked())
                {
                    Utils.commitVariable("selected_gender","Female");
                }
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
