package Repository;

import Entity.Ticket;

import java.sql.*;
import java.util.Date;

public class TicketRepository {
    private Connection connection = Singleton.getInstance().getConnection();

    //::::>
    public TicketRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS TicketTable(id serial PRIMARY KEY,cinemaName varchar(50),filmName varchar(50),datetime date,clock time,numberTicket int,price int,numberBuy int " +
         ",CONSTRAINT fk_cinemaName FOREIGN KEY(cinemaName) REFERENCES Cinema (cinemaName))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }catch (NullPointerException exception){
            System.out.println(exception.getMessage());
        }
    }

    //::::>
    public int importTicket(Ticket ticket) throws SQLException {
        String importTic = "INSERT INTO TicketTable(cinemaName,filmName,datetime,clock,numberTicket,price,numberBuy) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(importTic);
        preparedStatement.setString(1,ticket.getCinemaName());
        preparedStatement.setString(2,ticket.getFilmName());
        preparedStatement.setDate(3,ticket.getDatetime());
        preparedStatement.setTime(4,ticket.getClock());
        preparedStatement.setInt(5,ticket.getNumberTickets());
        preparedStatement.setInt(6,ticket.getPrice());
        preparedStatement.setInt(7,0);
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    //::::>
    public void showCinemaTickets(String cinemaName) throws SQLException {
        String showCinema = "SELECT * FROM TicketTable WHERE cinemaName = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(showCinema);
        preparedStatement.setString(1,cinemaName);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                    + "    |filmName =" + resultSet.getString("filmName")+"    |date= "+ resultSet.getString("datetime") +
                      "    |clock =" + resultSet.getString("clock"));
    }

    //::::>
    public void delTicket(Integer id) throws SQLException {
        String del = "DELETE FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(del);
        preparedStatement.setInt(1,id);
        try {
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    //::::>
    public Date returnDateTime(Integer id) throws SQLException {
        String dateTime = "SELECT datetime FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(dateTime);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        if(resultSet.next()) {
            return resultSet.getDate("datetime");
        }
        else
            return null;
    }

    public Time returnClock(Integer id) throws SQLException {
        String clock = "SELECT clock FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(clock);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        if(resultSet.next()) {
            return resultSet.getTime("clock");
        }
        else
            return null;
    }
    
    
    //::::>
    public void showAllTicket() throws SQLException {
        String show = "SELECT * FROM TicketTable ";
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                    + "    |filmName =" + resultSet.getString("filmName")+"    |Date= "+ resultSet.getString("datetime") + "    |clock=" + resultSet.getString("clock")
                    + "    |numberTicket= " + resultSet.getInt("numberTicket") + "     |price= " + resultSet.getInt("price") + "     |numberOfBuy= " + resultSet.getInt("numberBuy"));
    }

    //::::>
    public void searchWithName(String filmName,Date timeDate) throws SQLException {
        String searchName = " SELECT * FROM TicketTable WHERE filmName = ? AND datetime = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(searchName);
        preparedStatement.setString(1,filmName);
        preparedStatement.setDate(2, (java.sql.Date) timeDate);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            exception.getMessage();
        }
            while(resultSet.next())
                System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                        + "    |filmName =" + resultSet.getString("filmName")+"    |datetime= "+ resultSet.getString("datetime")
                        + "    |clock = " + resultSet.getString("clock")
                        + "    |numberTicket= " + resultSet.getInt("numberTicket") + "     |price= " + resultSet.getInt("price") + "    | numberOfBuy= " + resultSet.getInt("numberBuy"));

    }

    //::::>
    public String[] getTicketInformation(int id) throws SQLException {
        String[] resultArray = new String[3];
        String information = "SELECT * FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(information);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        if(resultSet.next()){
            resultArray[0] = resultSet.getString("filmName");
            resultArray[1] = String.valueOf(resultSet.getInt("numberTicket"));
            resultArray[2] = String.valueOf(resultSet.getInt("price"));
            return resultArray;
        }
        else
            return null;
    }

    //::::>
    public int updateNumberOfTicket(int id,Integer number) throws SQLException {
        String updateNumberTicket = "UPDATE TicketTable SET numberTicket = ? WHERE id = ?  ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateNumberTicket);
        preparedStatement.setInt(1,number);
        preparedStatement.setInt(2,id);
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    //::::>
    public int allBuyTicket(int id) throws SQLException {
        String number = " SELECT * FROM TicketTable WHERE id = ? " ;
        PreparedStatement preparedStatement = connection.prepareStatement(number);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        resultSet.next();
        return resultSet.getInt("numberBuy");
    }

    //::::>
    public void updateNumberTicketBuy(int id,int number) throws SQLException {
        String updateNumber = " UPDATE TicketTable SET numberBuy = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateNumber);
        preparedStatement.setInt(1,number);
        preparedStatement.setInt(2,id);
        try {
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    //::::>
    public void showListHighPurchase(String cinemaName) throws SQLException {
        String show = "SELECT * FROM ticketTable WHERE cinemaName = ? ORDER BY numberBuy DESC" ;
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        preparedStatement.setString(1,cinemaName);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                    + "    |filmName =" + resultSet.getString("filmName")+"    |Date= "+ resultSet.getString("datetime") + "    |clock=" + resultSet.getString("clock")
                    + "    |numberTicket= " + resultSet.getInt("numberTicket") + "     |price= " + resultSet.getInt("price") + "     |numberOfBuy= " + resultSet.getInt("numberBuy"));
    }






}
