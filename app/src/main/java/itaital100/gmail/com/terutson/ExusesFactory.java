package itaital100.gmail.com.terutson;

import java.util.Random;

public class ExusesFactory
{
    Random rand = new Random();
    int index;
    public enum Category{Meeting,Work,Ex,Date,Occassion,Homework}


     String[] Exuces_meeting = {"Exuces_meeting1","Exuces_meeting2","Exuces_meeting3", "Exuces_meeting4"};

     String[] Exuces_work = {"Exuces_work1","Exuces_work2","Exuces_work3", "Exuces_work4"};

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
