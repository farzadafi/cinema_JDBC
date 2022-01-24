package Service;

import Entity.Basket;
import Repository.BasketRepository;

import java.sql.SQLException;

public class BasketService {
    BasketRepository basketRepository = new BasketRepository();

    public BasketService() throws SQLException, ClassNotFoundException {
    }

    public void cancelTicket(int idDel) throws SQLException {
        basketRepository.cancelTicket(idDel);
    }

    public int importTicket(Basket basket) throws SQLException {
        return basketRepository.importTicket(basket);
    }

    public void viewMyBasket(String username) throws SQLException {
        basketRepository.viewMyBasket(username);
    }
}
