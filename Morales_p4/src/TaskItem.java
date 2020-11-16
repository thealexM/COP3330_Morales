import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskItem {

    protected String title;

    protected String description;

    protected String due_date;

    protected boolean complete;

    protected TaskItem(String titleIn, String descriptionIn, String due_dateIn, boolean completeIN){

        title           =   titleIn;

        description     =   descriptionIn;

        due_date        =   due_dateIn;

        complete        =   completeIN;
    }

    protected static boolean Due_DateReport(int mistakes) {

        if(mistakes % 5 == 0) System.out.println("\nThe entry does not follow the required formula.\n");

        if(mistakes % 2 == 0) System.out.println("\nThe entry is written in a form that is in the past already.\n");

        if(mistakes % 3 == 0) System.out.println("\nThe entry is not a valid date.\n");

        if(mistakes % 7 == 0) System.out.println("\nThe entry is not the right length.\n");

        return mistakes == 1;
    }


    protected static int Due_DateHandler(String due_dateIn){

        int mistakes, year, yearNow, month, monthNow, day, dayNow;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String current_date = formatter.format(new Date());

        year        = IsolateUnit(0,4,due_dateIn);

        month       = IsolateUnit(5,7,due_dateIn);

        day         = IsolateUnit(8,10,due_dateIn);

        yearNow     = IsolateUnit(0,4,current_date);

        monthNow    = IsolateUnit(5,7,current_date);

        dayNow      = IsolateUnit(8,10,current_date);

        mistakes  = PastDateHandler(year, yearNow, month, monthNow, day, dayNow);

        mistakes *= CalendarHandler(year, month, day);

        mistakes *= FormulaHandler(due_dateIn);

        return mistakes;
    }

    protected static boolean DescriptionHandler(String description){
        return true;
    }

    protected static boolean TitleHandler(String titleIn) {

        if(titleIn.length()==0) {

            System.out.println("\n One character is required.\n");

            return false;
        }
        return true;
    }

    protected static int IsolateUnit(int start, int end, String Date) {

        int output = 0, digit;

        for(int index = start; index < end; index ++){

            digit = Date.charAt(index)-48;

            if((digit)<0||digit > 9) return 0;

            output = 10 * output + digit;
        }
        return output;
    }

    protected static int PastDateHandler(int year, int yearNow, int month, int monthNow, int day, int dayNow){

        if(year<yearNow||(year==yearNow&&((month<monthNow)||(month==monthNow&&day<dayNow)))) return 2;

        return 1;
    }

    protected static int CalendarHandler(int year, int month, int day) {

        int[] validDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0) validDays[1] = 29;

        if (month > 12 || month < 1) return 3;

        if (day > validDays[month - 1] | day < 1) return 3;

        return 1;
    }

    protected static int FormulaHandler(String input) {

        int mistake1 = 1, mistake2 = 1;

        if (input.charAt(4) != 45 || input.charAt(7) != 45) mistake1 = 5;   

        if (input.length() != 10)   mistake2 = 7;

        return mistake1*mistake2;
    }
}

