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

    public void delTicket(int idDel) throws SQLException {
        ticketRepository.delTicket(idDel);
    }

    public void showAllTicket() throws SQLException {
        ticketRepository.showAllTicket();
    }

    public void searchWithName(String name,Date date) throws SQLException {
        ticketRepository.searchWithName(name,date);
    }

    public String[] getTicketInformation(int id) throws SQLException {
        return ticketRepository.getTicketInformation(id);
    }

    public int updateNumberOfTicket(int id,Integer number) throws SQLException {
        return ticketRepository.updateNumberOfTicket(id,number);
    }

    public int allBuyTicket(int id) throws SQLException {
        return ticketRepository.allBuyTicket(id);
    }

    public void updateNumberTicketBuy(int id,int number) throws SQLException {
        ticketRepository.updateNumberTicketBuy(id,number);
    }
}
