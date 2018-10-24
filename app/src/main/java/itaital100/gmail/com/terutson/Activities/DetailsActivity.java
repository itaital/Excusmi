package itaital100.gmail.com.terutson.Activities;

import android.content.ClipData;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import itaital100.gmail.com.terutson.Excuses.ExusesFactory;
import itaital100.gmail.com.terutson.Tools.Enums.*;
import itaital100.gmail.com.terutson.R;
import itaital100.gmail.com.terutson.Tools.Utils;


public class DetailsActivity extends AppCompatActivity
{
    //Vars:
        Category  ActivityCategory;
        public int    currentExcuseIndex = -1;
        String        currentExcuse;
        ExusesFactory myExcuseFactory = new ExusesFactory();
        HashSet<String> currentExcusesInStack = new HashSet<String>(); // to avoid duplicates
        Stack excusesStack = new Stack();
        ArrayList<Integer> index = new ArrayList<Integer>();

    //Components:
        TextView myTextBox;
        TextView myTextBox2;
    TextView myTopLabel;
        Button copy_Button;
        ImageButton forward_Button;
        ImageButton backward_Button;
        AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Utils.disableLandscapeMode(this);
        //sets graphic to be fixed the same as in the android studio editor
        setContentView(R.layout.activity_details);
        Utils.setGraphicsRegionTo("eng",this);

        //get type of activity:
        myTopLabel = (TextView) findViewById(R.id.topLabel);
        mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ActivityCategory = getExcuseCategoryType();
        for(int i=0; i< ExusesFactory.getCategorySize(ActivityCategory); i++){index.add(i);}

        //init components:
            initExcuseTextBox();
            initCopyButton();
            initForwardButton();
            initBackwardButton();
            initLable(ActivityCategory);

    }
    //-------------------------------------------------------------------------------------
    private void initExcuseTextBox()
    {
        myTextBox = (TextView) findViewById(R.id.txt_teruson);
        myTextBox.setMovementMethod(new ScrollingMovementMethod());//enable scroll down without scrollview
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
     /*
           show next excuse that is different then the previous
       */
    private void initForwardButton()
    {
        forward_Button = (ImageButton) findViewById(R.id.btn_forward);

        forward_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //generate new Excuse
                    String newExcuse = "";

                    if(excusesStack.size()==ExusesFactory.getCategorySize(ActivityCategory)-1)
                    {
                        forward_Button.setEnabled(false);
                       forward_Button.setBackgroundResource(R.drawable.forward_button_unchecked);
                        return;
                    }

                     do
                     {
                         newExcuse = myExcuseFactory.generateNewExcuse(ActivityCategory,currentExcuseIndex);
                     }
                     while(currentExcuse == newExcuse || excusesStack.search(newExcuse) != -1);

                     //push to stack while preventing duplicates:
                   //  if( !currentExcusesInStack.contains(currentExcuse) )
                // {
                        //After we got the new Excuse:
                        excusesStack.push(currentExcuse);
                        currentExcusesInStack.add(currentExcuse);
                    //}
                myTextBox.clearAnimation();
                myTextBox.setMovementMethod(new ScrollingMovementMethod());//renable scroll down without scrollview
                myTextBox.setText(newExcuse);
                currentExcuse = newExcuse;
                //Theres no reason to keep the backward button  disabled if the stack isnt empty:
                backward_Button.setEnabled(true);
                backward_Button.setBackgroundResource(R.drawable.backwards_button);
                myTextBox.setMovementMethod(null);

                myTextBox.setMovementMethod(new ScrollingMovementMethod());//enable scroll down without scrollview

            }
        });
    }
    @Override public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    private void initBackwardButton()
    {
        backward_Button = (ImageButton) findViewById(R.id.btn_backward);
        backward_Button.setEnabled(false);
        backward_Button.setBackgroundResource(R.drawable.backwards_button_unchecked);

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
                        backward_Button.setBackgroundResource(R.drawable.backwards_button_unchecked);
                        break;
                    default:
                        break;
                }
                //backward the excuse:
                        currentExcuse = (String)excusesStack.peek();
                        excusesStack.pop();
                        currentExcusesInStack.remove(currentExcuse);
                        myTextBox.setText(currentExcuse);
                    forward_Button.setEnabled(true);
                    forward_Button.setBackgroundResource(R.drawable.forward_button);
                myTextBox.setMovementMethod(null);

                myTextBox.setMovementMethod(new ScrollingMovementMethod());//enable scroll down without scrollview


            }
        });
    }

    private void initLable(Category cat)
    {
        switch(cat){
            case Homework:
                myTopLabel.setText("אני לא יכול להגיש שיעורי בית כי:");
                break;
            case Meeting:
                myTopLabel.setText("אני לא יכול להגיע לפגישה כי:");
                break;
            case Ex:
                myTopLabel.setText("אני לא יכול לעשות את המטלה כי:");
                break;
            case Work:
                myTopLabel.setText("אני לא יכול להגיע לעבודה כי:");
                break;
            case Occassion:
                myTopLabel.setText("אני לא יכול להגיע לאירוע כי:");
                break;
            case Date:
                myTopLabel.setText("אני לא יכול להגיע לדייט כי:");
                break;
            default: myTopLabel.setText("error in catagory");
        }
    }
//-------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------
    /*
        Checks if this activity got any messege about the type of the category that it is meant for.
        Output: if messege is valid -> returns the category type
                else                -> returns null;
     */
private Category getExcuseCategoryType()
{
    if(getIntent().hasExtra("hw"))
    {
        return Category.Homework;
    }
    if(getIntent().hasExtra("meeting"))
    {
        return Category.Meeting;
    }
    if(getIntent().hasExtra("ex"))
    {
        return Category.Ex;
    }
    if(getIntent().hasExtra("work"))
    {
        return Category.Work;
    }
    if(getIntent().hasExtra("occasion"))
    {
        return Category.Occassion;
    }
    if(getIntent().hasExtra("date"))
    {
        return Category.Date;
    }
    return null;
    }
}
