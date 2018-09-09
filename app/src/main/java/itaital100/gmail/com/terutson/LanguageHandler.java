package itaital100.gmail.com.terutson;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.os.ConfigurationCompat;
import android.support.v4.os.LocaleListCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

public class LanguageHandler
{
    //local vars:
    private static LocaleListCompat systemDefaultLangauge;
    private static boolean alreadyExecuted = false;


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
    static void setGraphicsRegionTo(String lang, AppCompatActivity activity)
    {
        String languageToLoad  = "en"; // your language

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config,
                activity.getBaseContext().getResources().getDisplayMetrics());
    }
    //---------------------------------------------------------------------------------------------
    /**
     *  This functions saves the local langauge and should be called once before any
     *  changes to the local langauge(via the above function: setGraphicsRegionTo) is called
     *  This could be done to determine what langauge the user is using and change the gui accordingly
     *  on the fly.
     */
    //---------------------------------------------------------------------------------------------
    static void saveLocalLanguage()
    {
        if(alreadyExecuted) return;
        else
         {
            LocaleListCompat localLanguage = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            systemDefaultLangauge = localLanguage;
             System.out.println(systemDefaultLangauge.toString()); // print local langauge
             alreadyExecuted = true;
        }
    }
    //---------------------------------------------------------------------------------------------


}
