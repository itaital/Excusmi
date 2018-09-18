package itaital100.gmail.com.terutson;

import java.util.Random;

public class ExusesFactory
{
    Random rand = new Random();
    int index;

    /*
    Rules for writing excuses:
        1) dont make it over complicated.
        2) dont make it too long
        3) dont make it too detailed. its okay to "leave  things at  the dark"

     */
    public enum Category{Meeting,Work,Ex,Date,Occassion,Homework}
    public enum Gender{Male,Female};

    static Gender selectedGender = Gender.Male;



     String[] Exuces_meeting =
             {
                     "יש לי תור לרופא",
                     "היי, קמתי עם חום היום",
                     "משהוא דחוף שמצריך אותי צץ עכשיו ואני לא אוכל לבוא",
                     "יש לאח שלי יום הולדת ואנחנו חוגגים לו במסעדה",
                     "יש אזכרה לסבתא רבא שלי ולגמרי שכחתי מזה",
                     "יש לי מיגרנה ואני לא רוצה להיפגש כשאני ככה",
                     "יש לבן הדוד שלי יום הולדת",
                     "החליפו איתי משמרת, אני אעבוד בשעות האלה",
                     "טיפה הגזמתי אתמול, אני מוכרח להשלים שעות שינה",
                     "הרכבת מתעכבת, לא אספיק להגיע בזמן",
                     "יש עומס בעבודה, אני רוצה להמשיך לעבוד מהבית",
                     "יש לנו אורחים",
                     "יש לי מבחן חשוב שאני צריך להתכונן אליו, פעם אחרת ?"
             };

     String[] Exuces_work =
             {
                     "התפוצץ צינור כלשהוא בבית. כל הרצפה מלאה בסמרטוטים והטכנאי יבוא רק בין השעות.",
                     "יש לי תור לבדיקה רפואית חשובה שלא סובלת דיחוי",
                     "אני מצטער, יש לנו מקרה חירום משפחתי. אני מקווה שהכל יהיה בסדר בקרוב ושנגלה שזו בהלת שווא",
                     "יש לי שפעת, זה כנראה מידבק",
                     "הילד לא מרגיש טוב, צריך לקחת אותו לרופא"

             };

     String[] Exuces_ex = {"Exuces_ex1","Exuces_ex2","Exuces_ex3", "Exuces_ex4"};

     String[] Exuces_date = {"Exuces_date1","Exuces_date2","Exuces_date3", "Exuces_date4"};

     String[] Exuces_occasion = {"Exuces_occasion1","Exuces_occasion2","Exuces_occasion3", "Exuces_occasion4"};

     String[] Exuces_hw = {"hw1","hw2","hw3", "hw4"};

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
        //Switch the category types to return different results
        switch(cat)
        {
            case Meeting:
                return Exuces_meeting.length;
            case Work:
                return Exuces_work.length;
            case Date:
                return Exuces_date.length;
            case Occassion:
                return Exuces_occasion.length;
            case Homework:
                return Exuces_hw.length;
            case Ex:
                return Exuces_ex.length;
            default:
                return  -1;
        }
    }
    //----------------------------------------------------------------------
    String getExcuse(Category cat, int index)
    {
        //Switch the category types to return different results
        switch(cat)
            {
                case Meeting:
                    return Exuces_meeting[index];
                case Work:
                    return Exuces_work[index];
                case Date:
                    return Exuces_date[index];

                case Occassion:
                    return Exuces_occasion[index];

                case Homework:
                    return Exuces_hw[index];

                case Ex:
                    return Exuces_ex[index];

                default:
                    return  null;
            }
    }
}
