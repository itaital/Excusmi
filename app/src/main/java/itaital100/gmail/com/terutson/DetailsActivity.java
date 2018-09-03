package itaital100.gmail.com.terutson;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

import java.util.Stack;

import itaital100.gmail.com.terutson.ExusesFactory.Category;


public class DetailsActivity extends AppCompatActivity
{
    //Vars:
        public int    currentExcuseIndex=-1;
        String        currentExcuse; // we don't need this!
        Category      ActivityCategory;
        ExusesFactory myExcuseFactory = new ExusesFactory();
        Stack st = new Stack();
        int flag =1; // this flag is to check if we press back after forwated or not


    //Components:
        TextView myTextBox;
        Button copy_Button;
        Button forward_Button;
        Button backward_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        myTextBox = (TextView) findViewById(R.id.txt_teruson);
        copy_Button = (Button) findViewById(R.id.btn_copy);
        forward_Button = (Button) findViewById(R.id.btn_forward);
        backward_Button = (Button) findViewById(R.id.btn_backward);
        //backward_Button.setClickable(false);
        backward_Button.setEnabled(false);
        ActivityCategory = getExcuseCategoryType();
        currentExcuse = myExcuseFactory.generateNewExcuse(ActivityCategory,currentExcuseIndex);
        st.push(currentExcuse);
        myTextBox.setText(currentExcuse);
        initCopyButton();
        initforwardButton();
        initbackwardButton();
    }
//-----------------------------------------------------------------------
      /*
           when press copy it will copy the text to the clipboard
           and it will show a msg that the text was copied.
         */
    private void initCopyButton()
    {
        copy_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text label", myTextBox.getText());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(),"הטקסט הועתק",Toast.LENGTH_SHORT).show();
            }
        });
    }
//-------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------
      /*
           show next excuse that is different then the previous
       */
    private void initforwardButton()
    {
        forward_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //generate new Excuse
                flag=1;
                String newExcuse = "";
                 do{
                     newExcuse = myExcuseFactory.generateNewExcuse(ActivityCategory,currentExcuseIndex);
                 }
                 while(currentExcuse == newExcuse);
                 myTextBox.setText(newExcuse);
                 currentExcuse = newExcuse;
                st.push(currentExcuse);

                backward_Button.setEnabled(true);

            }
        });
    }

    private void initbackwardButton(){
        backward_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(st.size()==0) {
                    //backward_Button.setClickable(false);
                    backward_Button.setEnabled(false);
                    return;
                }
              //  if(flag ==1)
                    st.pop();
                flag=0;
                currentExcuse = (String)st.peek();
                // only for the first time
               // if(currentExcuse.compareTo(myTextBox.getText().toString())==0)
               //     currentExcuse = (String)st.pop();
                myTextBox.setText(currentExcuse);

                if(st.size()==1) {
                    //backward_Button.setClickable(false);
                    backward_Button.setEnabled(false);
                    return;
                }
            }
        });
    }


    //-------------------------------------------------------------------------------------------
    /*
        Checks if this activity got any messege about the type of the category that it is meant for.
        Output: if messege is valid -> returns the category type
                else                -> returns null;
     */
    private Category getExcuseCategoryType()
    {
        if(getIntent().hasExtra("com.gmail.itaital100.hw"))
        {
            return Category.Homework;
        }
        if(getIntent().hasExtra("com.gmail.itaital100.meeting"))
        {
            return Category.Meeting;
        }
        if(getIntent().hasExtra("com.gmail.itaital100.ex"))
        {

            return Category.Ex;
        }
        if(getIntent().hasExtra("com.gmail.itaital100.work"))
        {
            return Category.Work;
        }
        if(getIntent().hasExtra("com.gmail.itaital100.occasion"))
        {
            return Category.Occassion;
        }
        if(getIntent().hasExtra("com.gmail.itaital100.date"))
        {
            return Category.Date;
        }
        return null;
    }
//-------------------------------------------------------------------------------------------

}
