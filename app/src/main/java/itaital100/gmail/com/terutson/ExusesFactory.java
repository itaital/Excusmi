package itaital100.gmail.com.terutson;

import java.util.Random;

import static itaital100.gmail.com.terutson.ExcusesData.Exuces_meeting_female;

public class ExusesFactory
{
    Random rand = new Random();
    int index;

    public enum Category{Meeting,Work,Ex,Date,Occassion,Homework}
    private enum Language{Hebrew,English};
    public enum Gender{Male,Female};

    static Gender selectedGender = Gender.Male;
    private Language ExcusesLangauge = Language.Hebrew;

    //Note: the user must not get an excuse identical to the one he already have
    //      also, boundaries needs to be checked
    public  String generateNewExcuse(Category cat, int currentIndex)
    {
        int this_categorySize = getCategorySize(cat);
        do
        {
            index = rand.nextInt(this_categorySize);
        }
        while (index == currentIndex); // if it is the same excuse we take another one

        //finally, return the new generated excuse:
        return getExcuse(cat,index);
    }
    //----------------------------------------------------------------------
    /**
     * Input: Category
     * Output: return the number of possible exuses within given category
     */
    int getCategorySize(Category cat)
    {
       return getCategoryArray(cat,selectedGender).length;
    }
    //----------------------------------------------------------------------
    String getExcuse(Category cat, int index)
    {
        //Switch the category types to return different results
        switch(cat)
            {
                case Meeting:
                    switch(ExcusesLangauge)
                    {
                        case Hebrew:
                                if(selectedGender.equals(Gender.Male))
                                    return ExcusesData.Exuces_meeting[index];
                                else
                                    return ExcusesData.Exuces_meeting_female[index];
                        case English:
                                return ExcusesData.Exuces_meeting_english[index];
                                default:return null;
                    }
                case Work:
                    switch(ExcusesLangauge)
                    {
                        case Hebrew:
                            if(selectedGender.equals(Gender.Male))
                                return ExcusesData.Exuces_work[index];
                            else
                                return ExcusesData.Exuces_work_female[index];
                        case English:
                            return ExcusesData.Exuces_work_english[index];
                        default:return null;
                    }
                case Date:
                    switch(ExcusesLangauge)
                    {
                        case Hebrew:
                            if (selectedGender.equals(Gender.Male))
                                return ExcusesData.Exuces_date[index];
                            else
                                return ExcusesData.Exuces_date_female[index];
                        case English:
                            return ExcusesData.Exuces_date_english[index];
                        default:
                            return null;
                    }
                case Occassion:
                    switch(ExcusesLangauge)
                    {
                        case Hebrew:
                            if(selectedGender.equals(Gender.Male))
                                return ExcusesData.Exuces_occasion[index];
                            else
                                return ExcusesData.Exuces_occasion_female[index];
                        case English:
                            return ExcusesData.Exuces_occasion_english[index];
                        default:return null;
                    }


                case Homework:
                    switch(ExcusesLangauge)
                    {
                        case Hebrew:
                            if(selectedGender.equals(Gender.Male))
                                return ExcusesData.Exuces_hw[index];
                            else
                                return ExcusesData.Exuces_hw_female[index];
                        case English:
                            return ExcusesData.Exuces_hw_english[index];
                        default:return null;
                    }
                case Ex:
                    switch(ExcusesLangauge)
                    {
                        case Hebrew:
                            if(selectedGender.equals(Gender.Male))
                                return ExcusesData.Exuces_ex[index];
                            else
                                return ExcusesData.Exuces_ex_female[index];
                        case English:
                            return ExcusesData.Exuces_ex_english[index];
                        default:return null;
                    }
                default:
                    return  null;
            }
    }
    String[] getCategoryArray(Category cat, Gender gender)
    {
        //Switch the category types to return different results
        switch(cat)
        {
            case Meeting:
                switch(ExcusesLangauge)
                {
                    case Hebrew:
                        if(gender.equals(Gender.Male))
                            return ExcusesData.Exuces_meeting;
                        else
                            return Exuces_meeting_female;
                    case English:
                        return ExcusesData.Exuces_meeting_english;
                    default:return null;
                }
            case Work:
                switch(ExcusesLangauge)
                {
                    case Hebrew:
                        if(gender.equals(Gender.Male))
                            return ExcusesData.Exuces_work;
                        else
                            return ExcusesData.Exuces_work_female;
                    case English:
                        return ExcusesData.Exuces_work_english;
                    default:return null;
                }
            case Date:
                switch(ExcusesLangauge)
                {
                    case Hebrew:
                        if (gender.equals(Gender.Male))
                            return ExcusesData.Exuces_date;
                        else
                            return ExcusesData.Exuces_date_female;
                    case English:
                        return ExcusesData.Exuces_date_english;
                    default:
                        return null;
                }
            case Occassion:
                switch(ExcusesLangauge)
                {
                    case Hebrew:
                        if(gender.equals(Gender.Male))
                            return ExcusesData.Exuces_occasion;
                        else
                            return ExcusesData.Exuces_occasion_female;
                    case English:
                        return ExcusesData.Exuces_occasion_english;
                    default:return null;
                }

            case Homework:
                switch(ExcusesLangauge)
                {
                    case Hebrew:
                        if(gender.equals(Gender.Male))
                            return ExcusesData.Exuces_hw;
                        else
                            return ExcusesData.Exuces_hw_female;
                    case English:
                        return ExcusesData.Exuces_hw_english;
                    default:return null;
                }
            case Ex:
                switch(ExcusesLangauge)
                {
                    case Hebrew:
                        if(gender.equals(Gender.Male))
                            return ExcusesData.Exuces_ex;
                        else
                            return ExcusesData.Exuces_ex_female;
                    case English:
                        return ExcusesData.Exuces_ex_english;
                    default:return null;
                }
            default:
                return  null;
        }
    }
}
