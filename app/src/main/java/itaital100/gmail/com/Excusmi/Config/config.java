package itaital100.gmail.com.Excusmi.Config;

import itaital100.gmail.com.Excusmi.Tools.Enums.*;

public class config
{
    private static Gender selectedGender = Gender.Male; // default value
    private static Language ExcusesLangauge = Language.English;

    public static void setSelectedGender(Gender gen)
    {
        if(gen!=null)
            selectedGender = gen;
    }
    //-----------------------------------------------------------
    static public void setExcusesLangauge(Language lang)
    {
        if(lang!=null)
            ExcusesLangauge = lang;
    }
    //-----------------------------------------------------------
    static public Gender getSelectedGender()
    {
        return selectedGender;
    }
    //-----------------------------------------------------------
    static public Language getExcusesLangauge()
    {
        return ExcusesLangauge;
    }
    //-----------------------------------------------------------
}
