package itaital100.gmail.com.terutson;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import itaital100.gmail.com.terutson.ExusesFactory.Category;


public class DetailsActivity extends AppCompatActivity
{
    //Vars:
        public int      currentExcuseIndex=-1;
        String          currentExcuse; // we don't need this!
        Category        ActivityCategory;
        ExusesFactory   myExcuseFactory = new ExusesFactory();
        HashSet<String> currentExcusesInStack = new HashSet<String>();
        Stack excusesStack = new Stack();


    //Components:
        TextView myTextBox;
        Button copy_Button;
        ImageButton forward_Button;
        ImageButton backward_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //get type of activity:
        ActivityCategory = getExcuseCategoryType();

        //init components:
            initExcuseTextBox();
            initCopyButton();
            initforwardButton();
            initbackwardButton();
    }
    //-------------------------------------------------------------------------------------
    private void initExcuseTextBox()
    {
        myTextBox = (TextView) findViewById(R.id.txt_teruson);
        currentExcuse = myExcuseFactory.generateNewExcuse(ActivityCategory,currentExcuseIndex);
        myTextBox.setText(currentExcuse);
    }
    //-----------------------------------------------------------------------
      /*
           when press copy it will copy the text to the clipboard
           and it will show a msg that the text was copied.
         */
    private void initCopyButton()
    {
        copy_Button = (Button) findViewById(R.id.btn_copy);
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
        forward_Button = (ImageButton) findViewById(R.id.btn_forward);

        forward_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //generate new Excuse
                    String newExcuse = "";
                     do
                     {
                         newExcuse = myExcuseFactory.generateNewExcuse(ActivityCategory,currentExcuseIndex);
                     }
                     while(currentExcuse == newExcuse);

                     //push to stack while preventing duplicates:
                    if( !currentExcusesInStack.contains(currentExcuse) )
                    {
                        //After we got the new Excuse:
                        excusesStack.push(currentExcuse);
                        currentExcusesInStack.add(currentExcuse);
                    }

                myTextBox.setText(newExcuse);
                currentExcuse = newExcuse;
                //Theres no reason to keep the backward button  disabled if the stack isnt empty:
                backward_Button.setEnabled(true);
                backward_Button.setImageResource(R.drawable.backwards_button);
            }
        });
    }

    private void initbackwardButton()
    {
        backward_Button = (ImageButton) findViewById(R.id.btn_backward);
        backward_Button.setEnabled(false);
        backward_Button.setImageResource(R.drawable.backwards_button_unchecked);

        backward_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int stackSize;
                stackSize = excusesStack.size();

                switch(stackSize)
                {
                    case 1:
                        backward_Button.setEnabled(false);
                        backward_Button.setImageResource(R.drawable.backwards_button_unchecked);
                        break;
                    default:
                        break;
                }
                //backward the excuse:
                        currentExcuse = (String)excusesStack.peek();
                        excusesStack.pop();
                        currentExcusesInStack.remove(currentExcuse);
                        myTextBox.setText(currentExcuse);
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
