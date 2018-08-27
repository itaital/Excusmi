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

        do {
            index = rand.nextInt(4);
        } while (index == currentIndex); // if it is the same excuse we take another one
        return getExcuse(cat,index);
    }
      String getExcuse(Category cat, int index)
    {
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
