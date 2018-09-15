package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //Variables:
    //Categories buttons:
    Button categoryButton_hw;
    Button categoryButton_meeting;
    Button categoryButton_ex;
    Button categoryButton_work;
    Button categoryButton_date;
    Button categoryButton_occassion;

    //An array to contain all categories:
    ArrayList<Button> allCategoriesButtons = new ArrayList<Button>();
//#################################################################################################

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       //Handle langauge:
                LanguageHandler.saveLocalLanguage();
                LanguageHandler.setGraphicsRegionTo("eng",this);
                //^ its not enough to do it just once in main
                //this needs to be done for each activity

        //init layout:
        setContentView(R.layout.activity_main);
        initCategoriesButtons(); // init all of the catagories button and store them inside variable: allCatagoriesButton arrayList

        //check if the user opens the app for the first time:
        if(openingForTheFirstTime())
        {
            openFirstTimeDiaolog();
        }
    }
    //----------------------------------------------------------------------------------------------
    boolean openingForTheFirstTime()
    {
        return false;
    }
    //----------------------------------------------------------------------------------------------
    void openFirstTimeDiaolog()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("שלום לכם! " + "\n" +"אנו מודים לכם שבחרתם להשתמש באפליקציה שלנו ומקווים שהיא תועיל לכם מאד" +
                "\n"+"עם זאת, אין אנו אחראים על כל נזק שעלול להיגרם כתוצאה משימוש באפליקציה שלנו, והאחריות לשימוש בתירוצים היא על המשתמש בלבד");
        builder1.setPositiveButton(
                "אני מסכים",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    //----------------------------------------------------------------------------------------------
    /*
       This function inits all categories buttons according to their id
       and attach a clickEvenet Listener to each one of them in a uniform way.
     */
    private void initCategoriesButtons()
    {
        setCategories();
        addAllButtonsToArray(allCategoriesButtons);

        //Set an action listener for each category button
        for(final Button categoryButton : allCategoriesButtons)
        {
            categoryButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent startIntent = new Intent(getApplicationContext(), DetailsActivity.class);

                            //get category name and build a key messege to send the next activity://
                            String fullResourceName = getResources().getResourceName(categoryButton.getId());
                            String categoryName = fullResourceName.substring(fullResourceName.lastIndexOf("_")+1);
                            String prefix = "com.gmail.itaital100.";
                            String finalKey = prefix + categoryName; //--> final key messege

                            //send messege to the next activity://
                            startIntent.putExtra(finalKey,categoryButton.getId());

                            //start activity://
                            startActivity(startIntent);
                        }
                    });

        }
    }
    //----------------------------------------------------------------------------------------------
    private void setCategories()
    {
        categoryButton_hw         =  (Button)findViewById(R.id.btn_hw);
        categoryButton_meeting    =  (Button)findViewById(R.id.btn_meeting);
        categoryButton_ex         =  (Button)findViewById(R.id.btn_ex);
        categoryButton_work       =  (Button)findViewById(R.id.btn_work);
        categoryButton_date       =  (Button)findViewById(R.id.btn_date);;
        categoryButton_occassion  =  (Button)findViewById(R.id.btn_occasion);
    }
    //----------------------------------------------------------------------------------------------
    private void addAllButtonsToArray(ArrayList<Button> arr)
    {
        arr.add(categoryButton_work);
        arr.add(categoryButton_hw);
        arr.add(categoryButton_meeting);
        arr.add(categoryButton_ex);
        arr.add(categoryButton_date);
        arr.add(categoryButton_occassion);
    }
}
