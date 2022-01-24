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
}
