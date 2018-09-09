package itaital100.gmail.com.terutson;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

public class AppUtilities
{
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



}
