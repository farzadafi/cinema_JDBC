package Repository;

import Entity.Cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaRepository {
    private Connection connection = Singleton.getInstance().getConnection();

    //::::>
    public CinemaRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS Cinema (cinemaName varchar(50)PRIMARY KEY,cinemaNumber varchar(50),username varchar(50),password varchar(50),confirm int )";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importCinema(Cinema cinema) throws SQLException {
        String importValue = "INSERT INTO Cinema (cinemaName,cinemaNumber,username,password,confirm) VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(importValue);
        preparedStatement.setString(1,cinema.getCinemaName());
        preparedStatement.setString(2,cinema.getCinemaNumber());
        preparedStatement.setString(3,cinema.getUsername());
        preparedStatement.setString(4,cinema.getPassword());
        preparedStatement.setInt(5,0);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public String findCinema(String username,String password) throws SQLException {
        String findQuery = "SELECT * FROM Cinema WHERE username = ? AND password = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return resultSet.getString("cinemaName");
        else
            return null;
    }

    //::::>
    public void showUnconfirmCinema() throws SQLException {
        String findQuery = "SELECT * FROM Cinema WHERE confirm = 0 ";
        PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("Entity.Cinema Name[" + resultSet.getString("cinemaName") + "] and Entity.Cinema Number[" + resultSet.getString("cinemaNumber") + "]");
        }
    }

    //::::>
    public int confirmCinema(String cinemaName) throws SQLException {
        String confirm = "UPDATE Cinema SET confirm = ? WHERE cinemaName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(confirm);
        preparedStatement.setInt(1,1);
        preparedStatement.setString(2,cinemaName);
        if(preparedStatement.executeUpdate() == 0 )
            return 0;
        else
            return 1;
    }

    //::::>
    public int hasCinema(String cinemaName) throws SQLException {
        String has = "SELECT * FROM Cinema WHERE Cinemaname = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(has);
        preparedStatement.setString(1,cinemaName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }

    //::::>
    public int isConfirm(String CinemaName) throws SQLException {
        String isConfirmCinema = "SELECT confirm FROM Cinema WHERE Cinemaname = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(isConfirmCinema);
        preparedStatement.setString(1,CinemaName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("confirm");
    }






}
