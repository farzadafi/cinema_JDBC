package Service;

import Repository.BasketRepository;

import java.sql.SQLException;

public class BasketService {
    BasketRepository basketRepository = new BasketRepository();

    public BasketService() throws SQLException, ClassNotFoundException {
    }

    public void cancelTicket(int idDel) throws SQLException {
        basketRepository.cancelTicket(idDel);
    }
}
