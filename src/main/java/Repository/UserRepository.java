package Repository;

import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection = Singleton.getInstance().getConnection();


    //::::>
   public UserRepository() throws SQLException, ClassNotFoundException {
       String create = "CREATE TABLE IF NOT EXISTS UserTable (firstName varchar(50),lastName varchar(50),username varchar(50)PRIMARY KEY,password varchar(50) ) ";
       try {
           PreparedStatement far = connection.prepareStatement(create);
           far.execute();
       }catch (SQLException sql){
           System.out.println(sql.getMessage());
       }catch (NullPointerException exception){
           System.out.println(exception.getMessage());
       }
   }

    //::::>
    public int importUser(User user) throws SQLException {
        String importValue = "INSERT INTO UserTable (firstName,lastName,username,password) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importValue);
        preparedStatement.setString(1,user.firstName);
        preparedStatement.setString(2,user.lastName);
        preparedStatement.setString(3,user.username);
        preparedStatement.setString(4,user.password);
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    //::::>
    public String findUser(String username,String password) throws SQLException {
        String findQuery = "SELECT * FROM UserTable WHERE username = ? AND password = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        if(resultSet.next())
            return resultSet.getString("username");
        else
            return null;
    }

}
