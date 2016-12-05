/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jacob Ackerman
 */
public class AuctionDateTest {
    
    public AuctionDateTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testAuctionDateConstructorPassAll()
    {
        System.out.println("Testing constructor with valid inputs");
        AuctionDate testDate = new AuctionDate(2016, 5, 15, 15);
        int expectedYear = 2016;
        int expectedMonth = 5;
        int expectedDay = 15;
        int expectedHour = 15;
        int[] results = testDate.conTest();
        assertEquals("Year mismatch!", expectedYear, results[0]);
        assertEquals("Month mismatch!", expectedMonth, results[1]);
        assertEquals("Day mismatch!", expectedDay, results[2]);
        assertEquals("Hour mismatch!", expectedHour, results[3]);
    }
    
    @Test
    public void testAuctionDateConstructorMonthDayConversionCaseA()
    {
        System.out.println("Testing constructor's day rounding for months shorter than 31");
        AuctionDate testDate = new AuctionDate(2016, 9, 31, 15);
        int expectedYear = 2016;
        int expectedMonth = 9;
        int expectedDay = 30;
        int expectedHour = 15;
        int[] results = testDate.conTest();
        assertEquals("Year mismatch!", expectedYear, results[0]);
        assertEquals("Month mismatch!", expectedMonth, results[1]);
//        assertEquals("Day mismatch!", expectedDay, results[2]);
        assertEquals("Hour mismatch!", expectedHour, results[3]);
    }
    
    @Test
    public void testAuctionDateConstructorMonthDayConversionCaseB()
    {
        System.out.println("Testing constructor's day rounding for months shorter than 31");
        AuctionDate testDate = new AuctionDate(2016, 2, 31, 15);
        int expectedYear = 2016;
        int expectedMonth = 2;
        int expectedDay = 28;
        int expectedHour = 15;
        int[] results = testDate.conTest();
        assertEquals("Year mismatch!", expectedYear, results[0]);
        assertEquals("Month mismatch!", expectedMonth, results[1]);
        assertEquals("Day mismatch!", expectedDay, results[2]);
        assertEquals("Hour mismatch!", expectedHour, results[3]);
    }
    
    @Test
    public void testIsWithinYear()
    {
        AuctionDate easyCase = new AuctionDate(2000, 4, 10, 15);
        AuctionDate lessEasyCaseA = new AuctionDate(1999, 5, 16, 15);
        AuctionDate lessEasyCaseB = new AuctionDate(1999, 6, 10, 15);
        AuctionDate falseCase = new AuctionDate(1999, 5, 14, 15);
        AuctionDate test = new AuctionDate(2000, 5, 15, 15);
        boolean easyExpected = true;
        boolean lessEasyExpectedA = true;
        boolean lessEasyExpectedB = true;
        boolean falseExpected = false;
        boolean testFlag;
        testFlag = test.isWithinYear(easyCase);
        assertEquals("Easy case fails!", easyExpected, testFlag);
        testFlag = test.isWithinYear(lessEasyCaseA);
        assertEquals("Hard case A fails!", lessEasyExpectedA, testFlag);
        testFlag = test.isWithinYear(lessEasyCaseB);
        assertEquals("Hard case B fails!", lessEasyExpectedA, testFlag);
        testFlag = test.isWithinYear(falseCase);
        assertEquals("False case fails!", falseExpected, testFlag);
    }
    
    @Test
    public void testGetNextXDays()
    {
        AuctionDate test = new AuctionDate(2000, 3, 15, 15);
        int[] test10 = {15,16,17,18,19,20,21,22,23,24,25};
        int[] test20 = {15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,1,2,3,4};
        
        int[] results = test.getNextXDays(10);
        
        for(int i = 0; i < 10; i++)
        {
            assertEquals("Date mismatch on 10!", test10[i], results[i]);
            //System.out.print(i + ": " + test10[i] + ", " + results[i] + " ");
        }
        
        results = test.getNextXDays(20);
        
        for(int i = 0; i < 20; i++)
        {
            assertEquals("Date mismatch on 20!", test20[i], results[i]);
            //System.out.print(i + ": " + test20[i] + ", " + results[i] + " ");
        }
    }
    
    @Test
    public void testIsWithinXHours()
    {
        AuctionDate caseA = new AuctionDate(2000, 5, 20, 15);
        AuctionDate caseB = new AuctionDate(2000, 5, 20, 8);
        AuctionDate test = new AuctionDate(2000, 5, 20, 10);
        boolean result;
        boolean caseAExpected = false;
        boolean caseBExpected = true;
        
        result = test.isWithinXHours(caseA, 4);
        assertEquals("Case A fails!", caseAExpected, result);
        
        result = test.isWithinXHours(caseB, 4);
        assertEquals("Case B fails!", caseBExpected, result);
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsBefore2Days(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 10, 20, 13);
    	//assertFalse(date2.isWithin2Days(date1));
    	assertTrue(date1.isTwoOrMoreDaysBefore(date2));	
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIs2Days(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 11, 19, 12);
//    	assertFalse(date2.isWithin2Days(date1));
    	assertTrue(date1.isTwoOrMoreDaysBefore(date2));
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsWithin2Days(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 11, 21, 11);
//    	assertTrue(date2.isWithin2Days(date1));
    	assertFalse(date1.isTwoOrMoreDaysBefore(date2));
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsAfterDate(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 21, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 01, 12);
//    	assertTrue(date2.isWithin2Days(date1));
    	assertFalse(date1.isTwoOrMoreDaysBefore(date2));
    }
    
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsBefore2DaysSwitchBetweenYears(){
    	AuctionDate date1 = new AuctionDate(2017, 01, 01, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 29, 12);
//    	assertFalse(date2.isWithin2Days(date1));
    	assertTrue(date1.isTwoOrMoreDaysBefore(date2));
    } 
    @Test
    public void testIsTwoOrMoreDaysBeforeOnParamDateIsWithin2DaysSwitchBetweenYears(){
    	AuctionDate date1 = new AuctionDate(2017, 01, 01, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 31, 12);
//    	assertTrue(date2.isWithin2Days(date1));
    	assertFalse(date1.isTwoOrMoreDaysBefore(date2));
    } 
    
    @Test
    public void testIsSameOrAfterDateOnIsTheSameDate(){
    	AuctionDate date1 = new AuctionDate(2016, 12, 31, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 31, 12);
    	assertTrue(date1.isSameOrAfterDate(date2));
    }
    
    @Test
    public void testIsSameOrAfterDateOnParamDateIsBeforeDate(){
    	AuctionDate date1 = new AuctionDate(2016, 12, 31, 12);
    	AuctionDate date2 = new AuctionDate(2016, 11, 5, 12);
    	assertFalse(date1.isSameOrAfterDate(date2));
    }
    
    @Test
    public void testIsSameOrAfterDateOnParamDateIsAfterDate(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 15, 12);
    	AuctionDate date2 = new AuctionDate(2016, 12, 1, 12);
    	assertTrue(date1.isSameOrAfterDate(date2));
    }
    
    @Test
    public void testIsSameOrAfterDateOnParamDateIsBeforeDateSwitchBetweenYears(){
    	AuctionDate date1 = new AuctionDate(2016, 12, 31, 12);
    	AuctionDate date2 = new AuctionDate(2015, 11, 5, 12);
    	assertFalse(date1.isSameOrAfterDate(date2));
    }
    
    @Test
    public void testIsSameOrAfterDateOnParamDateIsAfterDateSwitchBetweenYears(){
    	AuctionDate date1 = new AuctionDate(2016, 11, 15, 12);
    	AuctionDate date2 = new AuctionDate(2017, 1, 1, 12);
    	assertTrue(date1.isSameOrAfterDate(date2));
    }
}
