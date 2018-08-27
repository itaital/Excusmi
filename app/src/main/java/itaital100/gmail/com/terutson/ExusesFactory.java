package itaital100.gmail.com.terutson;

public class ExusesFactory
{
    public enum Category{Meeting,Work,Ex,Date,Occassion,Homework}


    String[] Exuces_meeting = {

    };


    String[] Exuces_work = {

    };

    String[] Exuces_ex = {

    };

    String[] Exuces_date = {

    };
    String[] Exuces_occasion = {

    };

    String[] Exuces_hw = {

    };

    //Note: the user must not get an excuse identical to the one he already have
    //      also, boundaries needs to be checked
    static String generateNewExcuse(Category cat, int currentInde)
    {
                int index = 0 ; //mock
                return getExcuse(cat,index);
    }
    static String getExcuse(Category cat, int index)
    {
            switch(cat)
            {
                case Meeting:

                    break;
                case Work:

                    break;
                case Date:

                    break;
                case Occassion:

                    break;
                case Homework:

                    break;
                case Ex:

                    break;
                default:
                    return  null;
            }
            return  ""; //mock output;
    }






}
