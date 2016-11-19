/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Enum containing info and functions to help when working with months.
 * 
 * @version 11.19.2016.001A
 * @author Jacob Ackerman
 */
public enum Months {
    JANUARY(1),
    FEBURARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);
    
    
    private final int myMonth;
    
    private Months(final int theMonth)
    {
        myMonth = theMonth;
    }
    
    /**
     * Convert this month into an int
     * 
     * @return Int representation of this month
     */
    public int getMonth()
    {
        return myMonth;
    }
    
    /**
     * Get the number of days in this month.
     * 
     * @return the number of days in this month
     */
    public int getDays()
    {
        int days = 31;
        switch(this)
        {
            case SEPTEMBER:
                days = 30;
                break;
            case APRIL:
                days = 30;
                break;
            case JUNE:
                days = 30;
                break;
            case NOVEMBER:
                days = 30;
                break;
            case FEBURARY:
                days = 28;
                break;
            default:
                break;
                
        }
        return days;
        
    }
    
}
