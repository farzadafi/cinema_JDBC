package Repository;

import Entity.Basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRepository {
    private Connection connection;

    public BasketRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String createTable = " CREATE TABLE IF NOT EXISTS Basket(id serial,username varchar(50) REFERENCES UserTable(username)," +
                       "idTicket Integer REFERENCES TicketTable(id),filmName varchar(50),numberTicket Integer,priceAll Integer) ";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importTicket(Basket basket) throws SQLException {
        String importBasket = "INSERT INTO Basket (username,idTicket,filmName,numberTicket,priceall) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importBasket);
        preparedStatement.setString(1,basket.getUsername());
        preparedStatement.setInt(2,basket.getIdTicket());
        preparedStatement.setString(3,basket.getFilmName());
        preparedStatement.setInt(4,basket.getNumber());
        preparedStatement.setInt(5,basket.getPriceAll());
        return preparedStatement.executeUpdate();
    }

    //::::>
    public void cancelTicket(Integer id) throws SQLException {
        String cancel = "DELETE FROM Basket WHERE idTicket = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(cancel);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    //::::>
    public void viewMyBasket(String username) throws SQLException {
        String finduser = " SELECT * FROM Basket WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(finduser);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("id=" + resultSet.getInt("id") + "  |filmName=" + resultSet.getString("filmName") +
                                   "   |numberTicket=" + resultSet.getInt("numberTicket") + "   |priceAll=" + resultSet.getInt("priceall"));

            }
        System.out.println("That's all");
    }







}
