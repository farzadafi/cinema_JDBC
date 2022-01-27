package Manager;

import Entity.Cinema;
import Entity.Login;
import Entity.TypeUser;
import Service.CinemaService;
import Service.LoginService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class CinemaManager {
    private Scanner input = new Scanner(System.in);
    private String input1,cinemaName,cinemaNumber,password,username;
    private LoginService loginService = new LoginService();
    private Utility utility = new Utility();
    private CinemaService cinemaService = new CinemaService();
    public String[] offCode = new String[1000];
    private static Integer emptyHomeIndexOffCode = 0;

    public CinemaManager() throws SQLException, ClassNotFoundException {
    }

    public void confirmCinema() throws SQLException {
        System.out.println("list of unConfirm cinema are:");
        cinemaService.showUnconfirmCinema();
        System.out.println("That's All!");
        System.out.print("Please enter name of cinema you want to confirm:");
        input1 = input.nextLine();
        if(cinemaService.confirmCinema(input1) == 0 )
            System.out.println("Sorry,something is wrong and your Cinema enter is not confirmed");
        else
            System.out.println(input1 + " is successful is confirmed!");
    }

    //::::>
    public void registerCinema() throws SQLException {
        cinemaName = utility.setFirstName();
        System.out.print("Enter your Cinema number:");
        cinemaNumber = input.nextLine();
        while(true) {
            username = utility.setUsername();
            if(loginService.findUsername(username))
                System.out.println("this user name is defined before,select another username");
            else
                break;
        }
        password = utility.setPassword();
        Cinema cinema = new Cinema(cinemaName,cinemaNumber,username,password);
        if(cinemaService.importCinema(cinema) != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else {
            System.out.println("Sing up is ERROR!,please try again");
            return;
        }
        Login login = new Login(username,password, TypeUser.CINEMA);
        loginService.add(login);
    }

    public int isConfirm(String username,String password) throws SQLException {
        cinemaName = cinemaService.findCinema(username,password);
        if(cinemaService.isConfirm(cinemaName) == 0 )
            return 0;

        return 1;
    }

    public void addOffCode(){
        System.out.print("Please enter name of OFF Code(name-percentage):");
        offCode[emptyHomeIndexOffCode] = input.nextLine();
        emptyHomeIndexOffCode++;
        System.out.println("this off code successful add!");
    }

    public int searchOffCode(String off){
        for(int i=0;i<emptyHomeIndexOffCode;i++)
            if(offCode[i].equals(off))
                return 1;
        return 0;
    }
}
