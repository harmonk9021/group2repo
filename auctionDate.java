
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is a custom built version of the otherwise cryptic and cumbersome to
 * use Date class that comes in the standard Java library. It is intended to
 * provide a quick and easy way to create and compare specific dates.
 * 
 * @version 11.17.2016.001A
 * @author Jacob Ackerman
 */
public class auctionDate {
    private int myYear;
    private int myMonth;
    private int myDay;
    private int myHour;
    
    private static final int AM_PM_CONVERSION = 12;
    
    
    /**
     * Default constructor. Creates an auctionDate object with all fields initialized
     * to the current date and hour.
     */
    public auctionDate()
    {
        Calendar cal = Calendar.getInstance();
        myYear = cal.get(Calendar.YEAR);
        myMonth = cal.get(Calendar.MONTH)+1;
        myDay = cal.get(Calendar.DAY_OF_MONTH);
        myHour = cal.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * Alternate constructor. This will create a new auctionDate object with
     * all fields specified by the user.
     * 
     * @param year An int that specifies the desired year
     * @param month An int that specifies the desired month (1-12 values valid)
     * @param day An int that specifies the desired day of the month (1-31* values valid)
     * @param hour An int that specifies the desired hour (24 hour clock, 0-24 values valid)
     * 
     * *Date value may be reduced to 30 or 28 depending on the inputted month
     */
    public auctionDate(int year, int month, int day, int hour)
    {
        myYear = year;
        myMonth = month;
        myDay = day;
        myHour = hour;
    }
    
    /**
     * This will take a <value/object to be specified, depending on what is considered
     * important and/or useful> and compare it to this auctionDate object. Will
     * return true if:
     *    -The day field of the two objects is the same AND
     *    -The month field of the two objects is the same AND
     *    -The year field of the two objects is the same
     * Otherwise will return false.
     * 
     * @param [TO BE DETERMINED]
     * @return Boolean flag stating true if both objects are on the same day
     */
    public boolean isSameDay()
    {
        return true;
    }
    
    /**
     * This will take a <value/object to be specified, depending on what is considered
     * important and/or useful> and compare it to this auctionDate object. Will
     * return true if:
     *    -The month field of the two objects is the same AND
     *    -The year field of the two objects is the same
     * Otherwise will return false.
     * 
     * @param [TO BE DETERMINED]
     * @return Boolean flag stating true if both objects are on the same month
     */
    public boolean isSameMonth()
    {
        return true;
    }
    
    /**
     * This will take a <value/object to be specified, depending on what is considered
     * important and/or useful> and check to see if the passed in object less than
     * a year in the past from this object. Will return true if:
     *      -The parameter is less than 365 days (1 year) from this object
     * Otherwise will return false.
     * 
     * @param [TO BE DETERMINED]
     * @return Boolean flag stating true if this date is within a year of the passed in parameter
     */
    public boolean isWithinYear()
    {
        return true;
    }
    
    /**
     * This function will return an int array containing the days of the month
     * from this date to the specified number of days in the future, rolling 
     * back to 1 when a new month happens.
     * 
     * @param days An int specifying the number of days in the future to go
     * @return An int array containing all the days of the month from this date to X
     */
    public int[] getNextXDays(int days)
    {
        return null;
    }
    
    @Override
    public String toString()
    {
        int hour12 = myHour;
        String am_pm;
        if (myHour > AM_PM_CONVERSION)
        {
            hour12 -= AM_PM_CONVERSION;
            am_pm = "PM";
        }
        else
            am_pm = "AM";
        String output = myMonth + "/" + myDay + "/" + myYear + " - " + hour12 + ":00" + am_pm;
        return output;
    }
}
