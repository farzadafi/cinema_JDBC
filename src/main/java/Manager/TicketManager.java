package Manager;

import Entity.Ticket;
import Service.CinemaService;
import Service.TicketService;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class TicketManager {
    private Scanner input = new Scanner(System.in);
    private TicketService ticketService = new TicketService();
    private CinemaService cinemaService = new CinemaService();
    private String cinemaName,filmName,timeDate,clock;
    private int numberTickets,price;


    public TicketManager() throws SQLException, ClassNotFoundException {
    }

    public void addTicket(String username,String password) throws SQLException {
        cinemaName = cinemaService.findCinema(username,password);
        System.out.println("please enter Information of Ticket for add!");
        System.out.print("Enter film name: ");
        filmName = input.nextLine();
        System.out.print("Enter date(yyyy-dd-mm):");
        timeDate = input.nextLine();
        Date timeD = Date.valueOf(timeDate);
        System.out.print("Enter time(HH:MM:SS):");
        clock = input.nextLine();
        Time timeC = Time.valueOf(clock);
        System.out.print("how many Entity.Ticket you has(number): ");
        numberTickets = input.nextInt();
        System.out.print("Enter price for " + filmName + " :");
        price = input.nextInt();
        input.nextLine();
        Ticket ticket = new Ticket(cinemaName,filmName,timeD,timeC,numberTickets,price);
        if(ticketService.importTicket(ticket) != 0 )
            System.out.println("Enter Ticket to Table successful!");
        else
            System.out.println("Unfortunately something is wrong,Please try again!");
    }
}
