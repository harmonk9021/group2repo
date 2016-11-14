import java.util.*;

/**
 * The user interface for Calendar
 * 
 * @author Cody Arnold 
 * @version 11/11/2016
 */
public class CalendarUI {
	
	public static void displayCalendar(Date date, List <Auction> auctions) {
      if (date.getMonth() == 0) {
         System.out.println("January");
      } else if(date.getMonth() == 1) {
         System.out.println("February");
      } else if(date.getMonth() == 2) {
         System.out.println("March");
      } else if(date.getMonth() == 3) {
         System.out.println("April");
      } else if(date.getMonth() == 4) {
         System.out.println("May");
      } else if(date.getMonth() == 5) {
         System.out.println("June");
      } else if(date.getMonth() == 6) {
         System.out.println("July");
      } else if(date.getMonth() == 7) {
         System.out.println("August");
      } else if(date.getMonth() == 8) {
         System.out.println("September");
      } else if(date.getMonth() == 9) {
         System.out.println("October");
      } else if(date.getMonth() == 10) {
         System.out.println("November");
      } else if(date.getMonth() == 11) {
         System.out.println("December");
      }
      for (int i = 1; i <= 31; i++) {
         System.out.print(i);
         for (Auction a : auctions) {
            if (a.getDate().getDate() == i && i >= date.getDate()) {
               System.out.print("*");
            }
         }
         System.out.println();
      }
	}
}
