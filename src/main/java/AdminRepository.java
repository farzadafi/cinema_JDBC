import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
        private Connection connection;


    //::::>
    public AdminRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String createTable = "CREATE TABLE IF NOT EXISTS Admin(firstName varchar(50),lastName varchar(50),username varchar(50)not null, password varchar(50) )";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importAdmin(Admin admin) throws SQLException {
        String importValue = "INSERT INTO Admin(firstName,lastName,username,password) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importValue);
        preparedStatement.setString(1,admin.firstName);
        preparedStatement.setString(2,admin.lastName);
        preparedStatement.setString(3,admin.username);
        preparedStatement.setString(4,admin.password);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public String findAdmin(String username,String password) throws SQLException {
        String findQuery = "SELECT * FROM Admin WHERE username = ? AND password = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return resultSet.getString("username");
        else
            return null;
    }







}
