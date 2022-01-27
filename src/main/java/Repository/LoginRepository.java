package Repository;

import Entity.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    private Connection connection;

    public LoginRepository() {
        try {
            connection = Singleton.getInstance().getConnection();
            String createTable = "CREATE TABLE IF NOT EXISTS Login(username varchar(50),password varchar(50),kind varchar(50))";
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void add(Login login){
        try {
            String add = "INSERT INTO Login(username,password,kind) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1,login.getUsername());
            preparedStatement.setString(2,login.getPassword());
            preparedStatement.setString(3, String.valueOf(login.getTypeUser()));
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public String find(String username,String password){
        try {
            String find = "SELECT * FROM Login WHERE username = ? AND password = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                return resultSet.getString("kind");
            }
            else{
                return "null";

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return "null";
    }

    public boolean findUsername(String username){
        try {
            String find = "SELECT * FROM Login WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst())
                return true;
            else
                return false;
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }
}
