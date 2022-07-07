package cinema;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.HashMap;

public class Cinema {
     static HashMap<Integer,HashMap<Integer, Character>> rowMap = new HashMap<>();
    static int totalSeats, rows, seats, totalTickets, totalSales, totalNet;
    public static void main(String[] args) {
       //init

        Scanner scanner = new Scanner(System.in);

        boolean on = true;
        
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        totalSeats=rows*seats;
        totalNet();
        
        //Building Room


        
        
        HashMap<Integer, Character> headMap = new HashMap<>();
        
        headMap.put(0,' ');
        for(int h=1;h<=seats;h++){
            headMap.put(h,GetChar(h));
            
        }
        
        rowMap.put(0,headMap);
        
        for(int i=1;i<=rows;i++){
            HashMap<Integer, Character> seatMap = new HashMap<>();
            
            
            
            seatMap.put(0,GetChar(i));
            
            for(int j=1;j<=seats;j++){
                seatMap.put(j,'S');
            }
            
            rowMap.put(i,seatMap);
            
        }



        String menu = "1. Show the seats \n2. Buy a ticket \n3. Statistics\n0. Exit";


        while(on){


            System.out.println(menu);

            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    //Print Room
                    PrintMap(rowMap);
                    break;
                case 2:
                    BuyTicket();
                    break;
                case 3:
                    Stats();
                    break;
                case 0:
                    on=false;
                    break;
                default:
                    System.out.println("Please make a choice from the menu");
                    break;
            }


        }
        

        

        

    }


    public static void Stats(){
        System.out.println("Number of purchased tickets: "+totalTickets);
        float percent;


        percent =(100.0f* totalTickets) / totalSeats;

        String temp = String.format("Percentage: %1$,.2f",percent);
        temp += "%";


       System.out.println(temp);





        System.out.println("Current income: $"+totalSales);
        System.out.println("Total income: $"+totalNet);
    }

    public static void totalNet(){
        int ticketPrice = 0;
        if (totalSeats<61){
            ticketPrice=10;
            totalNet=totalSeats*ticketPrice;
        } else {

            boolean even = rows % 2 == 0;

            if (even) {
                ticketPrice=9;
                totalNet=totalSeats*ticketPrice;
            } else {
                int half = (rows - 1) / 2;
                totalNet = half * 10 * seats;
                totalNet += (rows - half) * 8 * seats;

            }


        }
    }

    public static void BuyTicket(){
        //Choose Seat





        Scanner scanner = new Scanner(System.in);


        boolean conti = false;

        int row = 0;
        int seat = 0;
        while (!conti){
            PrintMap(rowMap);
            System.out.println("Enter a row number:");
            row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();


            if(row>rows||row<=0||seat>seats||seat<=0) {
                System.out.println("Wrong input!");
            } else if(rowMap.get(row).get(seat)=='S'){
                 conti=true;
             } else {
                 System.out.println("That ticket has already been purchased!");
             }


        }





        //Get Ticket Price
        int ticketPrice;

        if (totalSeats<61){
            ticketPrice=10;

        } else {
            //extra row
            boolean even = (rows) % 2 == 0;
            int half;

            if (!even) {
                half=(rows-1)/2;
            } else {
                half = (rows) / 2;
            }

            if(row>half){
                ticketPrice=8;
            } else {
                ticketPrice = 10;
            }

        }

        //Print TicketPrice

        System.out.println("Ticket price: $"+ticketPrice);
        totalSales+=ticketPrice;
        totalTickets++;

        rowMap.get(row).put(seat,'B');


    }


    public static char GetChar(int i){
        String hld = i+"";
        return hld.charAt(0);
    }
    
    public static void PrintMap(HashMap<Integer,HashMap<Integer, Character>> rowMap){
        System.out.println("Cinema:");
        int rows = rowMap.size();
        
        for(int i=0;i<rows;i++){
            HashMap<Integer, Character> seatMap = rowMap.get(i);
            int seats = seatMap.size();
            StringBuilder rowStr = new StringBuilder();
            for(int j=0;j<seats;j++){
                rowStr.append(seatMap.get(j)).append(" ");
            }
            System.out.println(rowStr);
        }
        
    }
    
    
    
    
    
}