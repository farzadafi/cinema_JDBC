package Manager;

import Entity.Admin;
import Entity.Login;
import Entity.TypeUser;
import Service.AdminService;
import Service.LoginService;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminManger {
    private Scanner input = new Scanner(System.in);
    private LoginService loginService = new LoginService();
    private Utility utility = new Utility();
    private String firstName,lastName,username,password;
    private AdminService adminService = new AdminService();

    public AdminManger() throws SQLException, ClassNotFoundException {
    }

    public void registerAdmin() throws SQLException {
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
        Admin admin = new Admin(firstName,lastName,username,password);
        int result =  adminService.importAdmin(admin);
        if(result != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else
            System.out.println("Sing up is ERROR!,please try again");
        Login login = new Login(username,password, TypeUser.ADMIN);
        loginService.add(login);
    }
}
