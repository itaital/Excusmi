package itaital100.gmail.com.terutson.Activities;

///Android imports:
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import com.google.android.gms.ads.MobileAds;


//Terutson imports:
import itaital100.gmail.com.terutson.Config.config;
import itaital100.gmail.com.terutson.R;
import itaital100.gmail.com.terutson.Startup.startup_1;
import itaital100.gmail.com.terutson.Tools.Utils;
import itaital100.gmail.com.terutson.Tools.Enums.*;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener
{
    //                                  Categories                          //
    public Button categoryButton_meeting;        public Button categoryButton_work;
    public Button categoryButton_event;          public Button categoryButton_hw;
    public Button categoryButton_chore;          public Button categoryButton_date;

    ArrayList<Button> allCategoriesButtons = new ArrayList<>();    //An array to contain all categories:

    //                          Menu:                               //
    private DrawerLayout   myMenu_drawer;
    public NavigationView  myMenu_navigation;
    private Button         myMenu_btn;

    // Preference is a place to store all application related data that you want
    // to be saved even after the app is closed
    public static SharedPreferences sharedPreferences = null;
    private static boolean sharedInitialized = false;


//Start of the program:
//--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Utils.disableLandscapeMode(this);
        setContentView(R.layout.activity_main);
        Utils.setGraphicsRegionTo("eng",this);
        //initialize:
            init_pref();
            init_gender();
            init_Ads();
            init_GraphicOriention();
            init_Categories();
            init_Menu();

        if(openingForTheFirstTime())
        {
            openFirstTimeDiaolog();
        }

    }
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------

    /**
     *
     * @return true if the app hadnt been opend before
     *  or false if the app had been opened atleast once before
     */
    private boolean openingForTheFirstTime()
    {
        String isFirstTime = Utils.getVariable("Opened");
        if(isFirstTime.equals("notfound"))
        {
            return true;
        }
        else return false;
    }
    //----------------------------------------------------------------------------------------------
    void openFirstTimeDiaolog()
    {
        Intent firstTimeActivity = new Intent(getApplicationContext(), startup_1.class);
        //start activity://
        startActivity(firstTimeActivity);
    }
    //----------------------------------------------------------------------------------------------
    private void init_Menu()
    {
        myMenu_drawer = findViewById(R.id.drawer_layout);
        myMenu_btn =  (Button)findViewById(R.id.btn_menu);
        myMenu_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myMenu_drawer.openDrawer(Gravity.LEFT);
                    }});
        //navigation init:
        myMenu_navigation = (NavigationView)findViewById(R.id.nav_view);
        myMenu_navigation.setNavigationItemSelectedListener(this);
        myMenu_navigation.bringToFront();
    }
    //----------------------------------------------------------------------------------------------
    private void init_GraphicOriention()
    {
        Utils.setGraphicsRegionTo("en",this);
    }
    //----------------------------------------------------------------------------------------------
    private void init_Ads()
    {
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
    }
    //----------------------------------------------------------------------------------------------
    /*
       This function inits all categories buttons according to their id
       and attach a clickEvenet Listener to each one of them in a uniform way.
     */
    private void init_Categories()
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

                            //get category based on the button pressed
                            String fullResourceName = getResources().getResourceName(categoryButton.getId());
                            String categoryName = fullResourceName.substring(fullResourceName.lastIndexOf("_")+1);

                            /*
                            Category btnCategory = null;
                            switch(categoryName)
                            {
                                case "work":       btnCategory = Category.Work;      break;
                                case "hw":         btnCategory = Category.Homework;  break;
                                case "meeting":    btnCategory = Category.Meeting;   break;
                                case "ex":         btnCategory = Category.Ex;        break;
                                case "occasion":   btnCategory = Category.Occassion; break;
                                case "date":       btnCategory = Category.Date;      break; }

                            DetailsActivity.ActivityCategory = btnCategory;
                            */
                            startIntent.putExtra(categoryName,categoryButton.getId());
                            //start activity://
                            startActivity(startIntent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                    });

        }
    }
    //----------------------------------------------------------------------------------------------
    private void setCategories()
    {
        categoryButton_hw         =  (Button)findViewById(R.id.btn_hw);
        categoryButton_meeting    =  (Button)findViewById(R.id.btn_meeting);
        categoryButton_chore      =  (Button)findViewById(R.id.btn_ex);
        categoryButton_work       =  (Button)findViewById(R.id.btn_work);
        categoryButton_date       =  (Button)findViewById(R.id.btn_date);;
        categoryButton_event      =  (Button)findViewById(R.id.btn_occasion);
    }
    //----------------------------------------------------------------------------------------------
    private void addAllButtonsToArray(ArrayList<Button> arr)
    {
        arr.add(categoryButton_work);
        arr.add(categoryButton_hw);
        arr.add(categoryButton_meeting);
        arr.add(categoryButton_chore);
        arr.add(categoryButton_date);
        arr.add(categoryButton_event);
    }
    //----------------------------------------------------------------------------------------------
    //Change back button fucntionality so it will close the menu if its open
    @Override
    public void onBackPressed()
    {
        if (this.myMenu_drawer.isDrawerOpen(GravityCompat.START))
        {
            this.myMenu_drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            this.moveTaskToBack(true);
        }
    }
    //----------------------------------------------------------------------------------------------
    /**
     *   This tells the menu how to react when an item is clicked
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_add_excuse:
                myMenu_drawer.closeDrawer(GravityCompat.START);
                Intent startIntent = new Intent(this.getApplicationContext(),Suggestion_Activity.class);
                startActivity(startIntent);
               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
           // case R.id.change_gender:
           //     myMenu_drawer.closeDrawer(GravityCompat.START);
           //     Utils.openGenderSelectDialog(this);
            //    return true;
            case R.id.nav_add_report:
                myMenu_drawer.closeDrawer(GravityCompat.START);
                startIntent = new Intent(this.getApplicationContext(),Report_Activity.class);
                startActivity(startIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.about:
                String ourmsg = "Excusmi is an excuse provider.\n" +
                        "its purpose is to provide excuses for a wide range of situations from everyday life.\n" +
                        "Thank you for using this app, we hope that you will find it useful.\n" +
                        "We are open to any criticism, so, please, feel free to do so.\n" +
                        "\nThank you and have a nice day.";
                Utils.openConfirmDialog(ourmsg,"Okay",this);
            default:
                return false;
        }
        myMenu_drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //----------------------------------------------------------------------------------------------
    private void init_pref()
    {
        if(sharedInitialized) return;
        sharedPreferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        sharedInitialized = true;
    }
    //----------------------------------------------------------------------------------------------
    private void init_gender()
    {
        Gender gen = Utils.getSelectedGender();
        config.setSelectedGender(gen);
    }
    //----------------------------------------------------------------------------------------------
}

