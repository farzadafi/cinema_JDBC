package Service;

import Entity.Ticket;
import Repository.TicketRepository;

import java.sql.SQLException;
import java.util.Date;

public class TicketService {
    private TicketRepository ticketRepository = new TicketRepository();

    public TicketService() throws SQLException, ClassNotFoundException {
    }

    public int importTicket(Ticket ticket) throws SQLException {
        return ticketRepository.importTicket(ticket);
    }

    public void showCinemaTickets(String cinemaName) throws SQLException {
        ticketRepository.showCinemaTickets(cinemaName);
    }

    public Date returnDateTime(int idDel) throws SQLException {
         return ticketRepository.returnDateTime(idDel);
    }
}
