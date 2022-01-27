package Manager;

import Entity.Login;
import Entity.TypeUser;
import Entity.User;
import Service.BasketService;
import Service.LoginService;
import Service.UserService;

import java.sql.SQLException;

public class UserManager {
    private String firstName,lastName,username,password;
    private Utility utility = new Utility();
    private LoginService loginService = new LoginService();
    private UserService userService = new UserService();
    private BasketService basketService = new BasketService();

    public UserManager() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public void registerUser() throws SQLException {
        firstName = utility.setFirstName();
        lastName = utility.setLastName();
        while(true){
            username = utility.setUsername();
            if(loginService.findUsername(username))
                System.out.println("this user name is defined before,select another username");
            else
                break;
        }
        password = utility.setPassword();
        User user = new User(firstName,lastName,username,password);
        if(userService.importUser(user) != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else {
            System.out.println("Sing up is ERROR!,please try again");
        }
        Login login = new Login(username,password, TypeUser.USER);
        loginService.add(login);
    }

    public void viewMyBasket(String username) throws SQLException {
        basketService.viewMyBasket(username);
    }


}
