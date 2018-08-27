package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //Categories buttons:
            ImageButton categoryButton_hw;
            ImageButton categoryButton_meeting;
            ImageButton categoryButton_ex;
            ImageButton categoryButton_work;
    //An array to contain all categories:
    ArrayList<ImageButton> allCategoriesButtons = new ArrayList<ImageButton>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCategoriesButtons();

    }
    //----------------------------------------------------------------------------------------------
    /*
       This function inits all categories buttons according to their id
       and attach a clickEvenet Listener to each one of them in a uniform way.
     */
    private void initCategoriesButtons()
    {
        //InitCategories:
        setCategories();
        addAllButtonsToArray(allCategoriesButtons);


        for(final ImageButton categoryButton :allCategoriesButtons)
        {
            categoryButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent startIntent = new Intent(getApplicationContext(), DetailsActivity.class);

                            //*******************************************************************
                            //Notice this area:
                            //******   This is broken on purpose, the implementation of details activity need to be changed a bit
                            startIntent.putExtra(Integer.toString(categoryButton.getId()),categoryButton.getId());
                            //*******************************************************************

                            startActivity(startIntent);
                        }
                    });
        }
    }
    //----------------------------------------------------------------------------------------------
    private void setCategories()
    {
        categoryButton_hw = (ImageButton)findViewById(R.id.btn_hw);
        categoryButton_meeting = (ImageButton)findViewById(R.id.btn_meeting);
        categoryButton_ex = (ImageButton)findViewById(R.id.btn_ex);
        categoryButton_work = (ImageButton)findViewById(R.id.btn_work);
    }
    //----------------------------------------------------------------------------------------------
    private void addAllButtonsToArray(ArrayList<ImageButton> arr)
    {
        arr.add(categoryButton_work);
        arr.add(categoryButton_hw);
        arr.add(categoryButton_meeting);
        arr.add(categoryButton_ex);
    }
}
