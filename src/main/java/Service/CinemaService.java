package Service;

import Entity.Cinema;
import Repository.CinemaRepository;

import java.sql.SQLException;

public class CinemaService {
    private CinemaRepository cinemaRepository = new CinemaRepository();

    public CinemaService() throws SQLException, ClassNotFoundException {
    }

    public int hasCinema(String cinemaName) throws SQLException {
        return cinemaRepository.hasCinema(cinemaName);
    }

    public int importCinema(Cinema cinema) throws SQLException {
        return cinemaRepository.importCinema(cinema);
    }

    public String findCinema(String username,String password) throws SQLException {
        return cinemaRepository.findCinema(username,password);
    }

    public void showUnconfirmCinema() throws SQLException {
         cinemaRepository.showUnconfirmCinema();
    }

    public int confirmCinema(String input) throws SQLException {
        return cinemaRepository.confirmCinema(input);
    }

    public int isConfirm(String name) throws SQLException {
        return cinemaRepository.isConfirm(name);
    }
}
