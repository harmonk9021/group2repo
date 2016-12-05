
import java.util.Calendar;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

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
public class AuctionDate implements java.io.Serializable{
    private int myYear;
    private int myMonth;
    private int myDay;
    private int myHour;
    
    private static final int AM_PM_CONVERSION = 12;
    private static final int MAX_HOUR_24 = 23;
    private static final int MAX_MONTHS = 12;
    
    /**
     * Default constructor. Creates an auctionDate object with all fields initialized
     * to the current date and hour.
     */
    public AuctionDate()
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
     * @param hour An int that specifies the desired hour (24 hour clock, 0-23 values valid)
     * 
     * *Date value may be reduced to 30 or 28 depending on the inputted month
     */
    public AuctionDate(int year, int month, int day, int hour)
    {
        myYear = year;
        if (month >= 1 && month <= MAX_MONTHS)
            myMonth = month;
        else
            throw new IllegalArgumentException("Month was outside valid range (1-12)");
            //myMonth = 12;
        if (day > 31)
        {
            throw new IllegalArgumentException("Day was outside valid range (1-31)");
        }
        else if (day > Months.FEBURARY.getDays() && myMonth == Months.FEBURARY.getMonth())
            day = Months.FEBURARY.getDays(); // Not doing leap year checking, it's not worth it
        else if (day > Months.JUNE.getDays() && (myMonth == Months.SEPTEMBER.getMonth() || myMonth == Months.APRIL.getMonth() || 
                myMonth == Months.JUNE.getMonth() || myMonth == Months.NOVEMBER.getMonth()))
            day = 30;
        myDay = day;
        if (hour >= 0 && hour <= MAX_HOUR_24)
            myHour = hour;
        else
            throw new IllegalArgumentException("Hour was outside valid range (0-23)");
    }
    
    /**
     * This will take an AuctionDate object and compare it to this auctionDate object. Will
     * return true if:
     *    -The day field of the two objects is the same AND
     *    -The month field of the two objects is the same AND
     *    -The year field of the two objects is the same
     * Otherwise will return false.
     * 
     * @param theDate The date to compare against
     * @return Boolean flag stating true if both objects are on the same day
     */
    public boolean isSameDay(AuctionDate theDate)
    {
        return (theDate.myDay == myDay && 
                theDate.myMonth == myMonth && theDate.myYear == myYear);
    }
    
    /**
     * This will take an AuctionDate object and compare it to this auctionDate object. Will
     * return true if:
     *    -The month field of the two objects is the same AND
     *    -The year field of the two objects is the same
     * Otherwise will return false.
     * 
     * @param theDate The date to compare against
     * @return Boolean flag stating true if both objects are on the same month
     */
    public boolean isSameMonth(AuctionDate theDate)
    {
        return (theDate.myMonth == myMonth && theDate.myYear == myYear);
    }
    
    /**
     * This will take an AuctionDate object and check to see if the passed in object less than
     * a year in the past from this object. Will return true if:
     *      -The parameter is less than 365 days (1 year) from this object
     * Otherwise will return false.
     * 
     * @param theDate The date to compare against
     * @return Boolean flag stating true if this date is within a year of the passed in parameter
     */
    public boolean isWithinYear(AuctionDate theDate)
    {
        
        if ((theDate.myYear == myYear - 1) && (theDate.myMonth >= myMonth))
        {
            if (theDate.myDay >= myDay || (theDate.myMonth > myMonth))
                return true;
        }
     
        else if (theDate.myYear == myYear)
        {
            return true;
        }
        return false;
    }
    
    /**
     * @returns true if the date given is the same date or after this AuctionDate
     * @returns false if the date is before
     */
    public boolean isSameOrAfterDate(AuctionDate theDate){
    	int aucDate = this.myYear*1000000 + this.myMonth*10000 + this.myDay*100 + this.myHour;
    	int paramDate = theDate.myYear*1000000 + theDate.myMonth*10000 + theDate.myDay*100 + theDate.myHour;
    	if(paramDate>=aucDate) return true;
    	return false;
    	
    }
    
    /**
     * @returns true if the date given is the before this AuctionDate
     * @returns false if the date is the same date of after this AuctionDate
     */
//    public boolean isBeforeDate(AuctionDate theDate){
//    	int aucDate = this.myYear*1000000 + this.myMonth*10000 + this.myDay*100 + this.myHour;
//    	int paramDate = theDate.myYear*1000000 + theDate.myMonth*10000 + theDate.myDay*100 + theDate.myHour;
//    	if(paramDate < aucDate) return true;
//    	return false;
//    	
//    }
    
    /**
     * @return true if the param date is 2 days or more before this date 
     * @return false if the param date is 1 day before, is the day, of after this date
     */
    public boolean isTwoOrMoreDaysBefore(AuctionDate theDate)
    {
    	//LocalDate thisDate = LocalDate.of(this.myYear, Month.of(this.myMonth), this.myDay);
    	//LocalDate paramDate = LocalDate.of(theDate.myYear, Month.of(theDate.myMonth), theDate.myDay);
    	//long daysBetween = ChronoUnit.DAYS.between(paramDate, thisDate);
        
        if (isSameMonth(theDate) && ((theDate.myDay - myDay) >= 2 || (myDay - theDate.myDay) >= 2))
            return true;
        
//    	if (daysBetween == 1 || daysBetween == 0) return 0;
//    	if(daysBetween > 1) return 1;
//    	return 2;
    	//if(daysBetween >= 2) return true;
    	return false;
    }
    
    public AuctionDate getAuctionDateXDaysAway(int daysOut){
    	LocalDate temp1 = this.toLocalDate();
    	LocalDate temp2;
    	if(daysOut < 0) temp2 = temp1.minusDays(Math.abs(daysOut));
    	else temp2 = temp1.plusDays(daysOut);
    	return new AuctionDate(temp2.getYear(), temp2.getMonthValue(), temp2.getDayOfMonth(), this.myHour);
    }
    
    public int getYear(){
    	return myYear;
    }
    
    public int getMonth(){
    	return myMonth;
    }
    
    public int getDay(){
    	return myDay;
    }
    
    public int getHour(){
    	return myHour;
    }
    
    public int toInt(){
    	return this.myYear*1000000 + this.myMonth*10000 + this.myDay*100 + this.myHour;
    }
    
    public LocalDate toLocalDate(){
    	return LocalDate.of(this.myYear, Month.of(this.myMonth), this.myDay);
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
        int currDay = myDay;
        Months currMonth = null;
        currMonth = Months.convertToMonth(myMonth);
        int nextDays[] = new int[days];
        nextDays[0] = currDay;
        currDay++;
        for (int i = 1; i < days; i++)
        {
            if(currDay > currMonth.getDays())
            {
                int tmp = currMonth.getMonth();
                tmp = (tmp + 1) % 12;
                currMonth = Months.convertToMonth(tmp);
                currDay = 1;
            }
            nextDays[i] = currDay;
            currDay++;
        }
        return nextDays;
    }
    
    /**
     * This function will take an AuctionDate object and check whether it is 
     * within a specified amount of hours, positive and negative, from the date
     * object it is called on. Will return true if:
     *      -The passed in AuctionDate is more than X hours before, or X hours
     *       after this AuctionDate
     *      -The passed in AuctionDate is exactly X hours before, or X hours
     *       after this AuctionDate
     * Otherwise will return false.
     * 
     * @param theDate The date to compare against
     * @param hours The amount of hours away to check (represents the X value)
     * @return Boolean flag stating true if the passed in date is X hours away
     *         from this date, and false otherwise
     */
    public boolean isWithinXHours(AuctionDate theDate, int hours)
    {
        if ((theDate.myHour - myHour <= hours) && (theDate.myHour - myHour > 0))
            return true; // This doesn't actually account for rollovers into the 
                         // next day at the moment, but I can't actually think of 
                         // a realistic situation where that needs to be accounted for.
        else if ((myHour - theDate.myHour <= hours) && (myHour - theDate.myHour > 0))
            return true;
        else
            return false;
    }
    
    /**
     * DO NOT USE!
     * FOR TESTING PURPOSES ONLY!
     */
    public int[] conTest()
    {
        int[] stuff = {myYear, myMonth, myDay, myHour};
        return stuff;
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
