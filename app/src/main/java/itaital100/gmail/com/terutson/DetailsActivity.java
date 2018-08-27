package itaital100.gmail.com.terutson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import itaital100.gmail.com.terutson.ExusesFactory.Category;


public class DetailsActivity extends AppCompatActivity
{
    //Vars:
            Category ActivityCategory;
            int currentExcuseIndex;
    //Components:
            TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tv = (TextView) findViewById(R.id.txt_teruson);
        ActivityCategory = getExcuseCategoryType();
        int i;
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


}
