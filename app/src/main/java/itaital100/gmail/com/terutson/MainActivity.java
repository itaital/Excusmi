package itaital100.gmail.com.terutson;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import java.util.ArrayList;
import com.google.android.gms.ads.MobileAds;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //Variables:
    //Categories buttons:
    Button categoryButton_hw;
    Button categoryButton_meeting;
    Button categoryButton_ex;
    Button categoryButton_work;
    Button categoryButton_date;
    Button categoryButton_occassion;

    Button menuButton;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;



    //An array to contain all categories:
    ArrayList<Button> allCategoriesButtons = new ArrayList<Button>();
//#################################################################################################

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        //Handle langauge:
        LanguageHandler.saveLocalLanguage();
        LanguageHandler.setGraphicsRegionTo("eng",this);
        //^ its not enough to do it just once in main
        //this needs to be done for each activity

        //init layout:
        initCategoriesButtons(); // init all of the catagories button and store them inside variable: allCatagoriesButton arrayList
        menuButton                =  (Button)findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDrawerLayout.openDrawer(Gravity.LEFT);
                        }});
        //navigation init:
        mNavigationView = (NavigationView)findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        //check if the user opens the app for the first time:
        if(openingForTheFirstTime())
        {
            openFirstTimeDiaolog();
        }

    }

    //----------------------------------------------------------------------------------------------
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_add_excuse:

                break;
            case R.id.change_gender:

                break;

            case R.id.about:
                openConfirmDialog("אנחנו אריאל ואיתי בלה בלה בלה","אוקיי");
            default:
                return false;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        String msg ="שלום לכם! " + "\n" +"אנו מודים לכם שבחרתם להשתמש באפליקציה שלנו ומקווים שהיא תועיל לכם מאד" +
                "\n"+"עם זאת, אין אנו אחראים על כל נזק שעלול להיגרם כתוצאה משימוש באפליקציה שלנו, והאחריות לשימוש בתירוצים היא על המשתמש בלבד";
        String confirmMsg = "אני מסכים";
        openConfirmDialog(msg,confirmMsg);
    }
    void openConfirmDialog(String MainMsg,String confirmMsg)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(MainMsg);
        builder1.setPositiveButton(
                confirmMsg,
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.nav_add_excuse:

                break;
            case R.id.change_gender:

                break;

            case R.id.about:
                openConfirmDialog("אנחנו אריאל ואיתי בלה בלה בלה","אוקיי");
            default:
                return false;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
