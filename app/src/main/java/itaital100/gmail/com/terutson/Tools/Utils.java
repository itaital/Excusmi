package itaital100.gmail.com.terutson.Tools;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import java.util.Locale;

import itaital100.gmail.com.terutson.Activities.MainActivity;
import itaital100.gmail.com.terutson.Excuses.ExusesFactory;
import itaital100.gmail.com.terutson.R;
import itaital100.gmail.com.terutson.Tools.Enums.*;
import itaital100.gmail.com.terutson.Config.config;



public class Utils
{
    //local vars:
    /**
     *
     * @param lang - wanted langauge to set as fake local langauge
     * @param activity - the activity in which to set this local fake langauge
     * Effect:
     *           Some langauge are written from right to left(like hebrew), so androdi will
     *           try to opposite the gui components as well, which obviously sucks for us because
     *           it will mess our gui.
     *
     *            This functions allows us to trick the Android OS into thinking that the given activity
     *            uses whatever langauge we are giving this function.
     *            This should almost always be "en"
     */
    //----------------------------------------------------------------------------------------------
    public static void setGraphicsRegionTo(String lang, AppCompatActivity activity)
    {
        String languageToLoad  = lang; // your language

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config,
                activity.getBaseContext().getResources().getDisplayMetrics());
    }
    //----------------------------------------------------------------------------------------------
    public static void openConfirmDialog(String MainMsg, String confirmMsg, AppCompatActivity activity)
    {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(activity);
        myBuilder.setMessage(MainMsg);
        myBuilder.setPositiveButton(
                confirmMsg,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog myAlert = myBuilder.create();
        myAlert.setCancelable(false);
        myAlert.show();

    }
    //----------------------------------------------------------------------------------------------
    /*
        Input: tag - the name you give the saved variable
               var - the variable content, given as String
     */
    public static void commitVariable(String tag, String var)
    {
        SharedPreferences sharedPref = MainActivity.sharedPreferences;
        SharedPreferences.Editor editor= sharedPref.edit();
        //put your value
        editor.putString(tag,var);
        editor.commit();
    }
    //----------------------------------------------------------------------------------------------
    public static String getVariable(String tag)
    {
        SharedPreferences sharedPref = MainActivity.sharedPreferences;
        String variable = sharedPref.getString(tag, "notfound");
        return variable;
    }
    //----------------------------------------------------------------------------------------------
    public static Gender getSelectedGender()
    {
        String storedGender = getVariable("selected_gender");
        if(storedGender.equals("notfound"))
        {
            commitVariable("selected_gender","Male");
            return Gender.Male;
        }
        else
        {
            switch(storedGender)
            {
                case "Male":
                    return Gender.Male;
                case "Female":
                    return Gender.Female;
                default:
                    return Gender.Male;
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    public static void openGenderSelectDialog(AppCompatActivity activity)
    {
        final Dialog dialog = new Dialog(activity);
        final AppCompatActivity myActivity= activity;
        String gender;
        //dialog:
        dialog.setContentView(R.layout.activity_gender_change);
        dialog.setTitle("בחר מין");
        dialog.setCancelable(true);

        //buttons:
        RadioButton radioBtn_male = (RadioButton) dialog.findViewById(R.id.radioM);
        RadioButton radioBtn_female = (RadioButton) dialog.findViewById(R.id.radioF);
        Gender selectedGender = config.getSelectedGender();
        if(selectedGender == Gender.Male)
        {
            radioBtn_male.setChecked(true);
            radioBtn_female.setChecked(false);
        }
        else
        {
            radioBtn_male.setChecked(false);
            radioBtn_female.setChecked(true);
        }
        radioBtn_male.setOnClickListener
                (
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.hide();
                       config.setSelectedGender(Gender.Male);
                       commitVariable("selected_gender",config.getSelectedGender().name());

                    }});
        radioBtn_female.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.hide();
                        config.setSelectedGender(Gender.Female);
                        commitVariable("selected_gender",config.getSelectedGender().name());
                    }});
        // now that the dialog is set up, it's time to show it !
        dialog.show();
    }
}
