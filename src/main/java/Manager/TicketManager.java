package Manager;

import Entity.Basket;
import Entity.Ticket;
import Service.BasketService;
import Service.CinemaService;
import Service.TicketService;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class TicketManager {
    Manager manager = new Manager();
    private Scanner input = new Scanner(System.in);
    private TicketService ticketService = new TicketService();
    private CinemaService cinemaService = new CinemaService();
    private String cinemaName,filmName,timeDate,clock;
    private int numberTickets,price,priceAll;
    private BasketService basketService = new BasketService();


    public TicketManager() throws SQLException, ClassNotFoundException {
    }

    public void addTicket(String username,String password) throws SQLException {
        cinemaName = cinemaService.findCinema(username,password);
        System.out.println("please enter Information of Ticket for add!");
        System.out.print("Enter film name: ");
        filmName = input.nextLine();
        System.out.print("Enter date(yyyy-dd-mm):");
        timeDate = input.nextLine();
        Date timeD;
        try {
            timeD = Date.valueOf(timeDate);
        }catch (Exception exception){
            System.out.println("open your eyes and see the format!");
            return;
        }
        System.out.print("Enter time(HH:MM:SS):");
        clock = input.nextLine();
        Time timeC;
        try {
            timeC = Time.valueOf(clock);
        }catch (Exception exception){
            System.out.println("open your eyes and see the format!");
            return;
        }
        System.out.print("how many Entity.Ticket you has(number): ");
        try {
            numberTickets = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("just enter number! it is hard?");
            input.nextLine();
            return;
        }
        System.out.print("Enter price for " + filmName + " :");
        try {
            price = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("just enter number! it is hard?");
            input.nextLine();
            return;
        }
        Ticket ticket = new Ticket(cinemaName,filmName,timeD,timeC,numberTickets,price);
        if(ticketService.importTicket(ticket) != 0 )
            System.out.println("Enter Ticket to Table successful!");
        else
            System.out.println("Unfortunately something is wrong,Please try again!");
    }

    public void delTicket(String username,String password) throws SQLException {
        cinemaName = cinemaService.findCinema(username,password);
        Date nowDate = Date.valueOf(java.time.LocalDate.now());
        Time nowTime = Time.valueOf(java.time.LocalTime.now());
        System.out.println("**Now Date is: " + nowDate + " and Now Time is: " + nowTime + " **");
        ticketService.showCinemaTickets(cinemaName);
        System.out.print("Enter Id Ticket for cancel:");
        Integer idDel = 0;
        try {
            idDel = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("invalid input!");
            input.nextLine();
        }
        Date dateTicket = (Date) ticketService.returnDateTime(idDel);
        if(dateTicket == null){
            System.out.println("You Enter a wrong id!");
            return;
        }
        if(dateTicket.after(nowDate)){
            basketService.cancelTicket(idDel);
            ticketService.delTicket(idDel);
            System.out.println("Ticket you enter successful cancel!");
        }
        else if(dateTicket.equals(nowDate)){
            Time timeTicket = ticketService.returnClock(idDel);
            if(timeTicket == null){
                System.out.println("Something wrong!");
                return;
            }
            if(timeTicket.after(nowTime)){
                basketService.cancelTicket(idDel);
                ticketService.delTicket(idDel);
                System.out.println("Ticket you enter successful cancel!");
            }
            else
                System.out.println("this time ticket you enter is Expired!");
        }
        else
            System.out.println("this time ticket you enter is Expired!");

    }

    public void showAllTicket() throws SQLException {
        ticketService.showAllTicket();
    }

    public void reserveTicket(String username) throws SQLException {
        showAllTicket();
        System.out.println("Please enter Information of ticket you want to reserve");
        System.out.println("Enter idTicket:");
        int id = 0;
        try {
            id = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("Invalid number!");
        }
        String[] ticketInformation = ticketService.getTicketInformation(id);
        if (ticketInformation == null){
            System.out.println("You enter a wrong id Ticket!");
            return;
        }
        filmName = ticketInformation[0];
        System.out.print("How many ticket you want to buy?:");
        try {
            numberTickets = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("just enter number!");
            input.nextLine();
        }
        if(numberTickets > Integer.parseInt(ticketInformation[1])){
            System.out.println("you enter a number bigger than numberOfTicket we have");
            return;
        }
        if(ticketService.updateNumberOfTicket(id,Integer.parseInt(ticketInformation[1]) - numberTickets) == 0 ){
            System.out.println("Something Wrong");
            return;
        }
        priceAll = calcPriceAll(numberTickets,Integer.parseInt(ticketInformation[2]));
        int allBuy = ticketService.allBuyTicket(id) + numberTickets;
        ticketService.updateNumberTicketBuy(id,allBuy);
        Basket basket = new Basket(username,id,filmName,numberTickets,priceAll);
        if(basketService.importTicket(basket) != 0)
            System.out.println("Ticket you Enter successful add to you Basket!");
        else
            System.out.println("Something Wrong!");
    }

    public int calcPriceAll(int number,int price){
        input.nextLine();
        return number * price;
        /*
        System.out.print("if you have a OFF code,Please enter it:");
        String off = input.nextLine();
        if(manager.searchOffCode(off) == 0 ){
            System.out.println("You dont have a OFF code,and you ticket calc with normal price!");
            return number*price;
        }
        else{
            String[] array =off.split("-");
            int mountPercentage = Integer.parseInt(array[1]);
            double finalPercentage = (100.0 - mountPercentage ) / 100;
            return (int) ((number*price) * finalPercentage);
        }

         */

    }




}
