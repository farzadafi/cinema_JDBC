package Manager;

import Entity.*;
import Repository.*;
import Service.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Manager {
    private InvalidUsername invalidUsername = new InvalidUsername();
    private InvalidName invalidName = new InvalidName();
    private AdminService adminService = new AdminService();
    private UserService userService = new UserService();
    private CinemaService cinemaService = new CinemaService();
    private TicketService ticketService = new TicketService();
    private BasketService basketService = new BasketService();
    private Scanner input = new Scanner(System.in);
    private String input1,firstName,lastName,username,password,cinemaName,cinemaNumber,timeDate;
    public String[] offCode = new String[1000];
    private static Integer emptyHomeIndexOffCode = 0;
    private String[] usernameArray = new String[1000];
    private static Integer emptyHomeIndex = 0;

    //::::>
    public Manager() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public void registerAdmin() throws SQLException {
        System.out.print("Please enter your first name(just alpha):");
        try {
            firstName = input.nextLine();
            invalidName.checkName(firstName);
        }catch (InvalidName except){
            System.out.println(except.getMessage());
            return;
        }
        System.out.print("Enter your last name(just alpha):");
        try {
            lastName = input.nextLine();
            invalidName.checkName(lastName);
        }catch (InvalidName except){
            System.out.println(except.getMessage());
            return;
        }
        while(true){
            System.out.print("Enter your user name(start with alpha):");
            try {
                username = input.nextLine();
                invalidUsername.checkUsername(username);
            }catch (InvalidUsername except){
                System.out.println(except.getMessage());
                return;
            }
            int result = findInArray(username);
            if(result != -1 )
                System.out.println("this user name is defined before,select another username");
            else{
                usernameArray[emptyHomeIndex] = username;
                emptyHomeIndex++;
                break;
            }
        }
        System.out.print("Enter your password:");
        password = input.nextLine();
        Admin admin = new Admin(firstName,lastName,username,password);
        int result =  adminService.importAdmin(admin);
        if(result != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else
            System.out.println("Sing up is ERROR!,please try again");
    }

    //::::>
    public void registerCinema() throws SQLException {
        while(true)
        {
            System.out.print("Please enter your Cinema name(just alpha):");
            try {
                cinemaName = input.nextLine();
                invalidName.checkName(cinemaName);
            }catch (InvalidName except){
                System.out.println(except.getMessage());
                return;
            }
            if(cinemaService.hasCinema(cinemaName) == 1)
                System.out.println("this cinema name is defined before,select another Name");
            else
                break;
        }
        System.out.print("Enter your Cinema number:");
        cinemaNumber = input.nextLine();
        while(true) {
            System.out.print("Enter your user name(start with alpha):");
            try {
                username = input.nextLine();
                invalidUsername.checkUsername(username);
            }catch (InvalidUsername except){
                System.out.println(except.getMessage());
                return;
            }
            if( findInArray(username) != -1 )
                System.out.println("this user name is defined before,select another username");
            else{
                usernameArray[emptyHomeIndex] = username;
                emptyHomeIndex++;
                break;
            }
        }
        System.out.print("Enter your password:");
        password = input.nextLine();
        Cinema cinema = new Cinema(cinemaName,cinemaNumber,username,password);
        if(cinemaService.importCinema(cinema) != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else
            System.out.println("Sing up is ERROR!,please try again");
    }

    //::::>
    public void registerUser() throws SQLException {
        System.out.print("Please enter your first name(just alpha):");
        try {
            firstName = input.nextLine();
            invalidName.checkName(firstName);
        }catch (InvalidName except){
            System.out.println(except.getMessage());
            return;
        }
        System.out.print("Enter your last name(just alpha):");
        try {
            lastName = input.nextLine();
            invalidName.checkName(lastName);
        }catch (InvalidName except){
            System.out.println(except.getMessage());
            return;
        }
        while(true){
            System.out.print("Enter your user name(start with alpha):");
            try {
                username = input.nextLine();
                invalidUsername.checkUsername(username);
            }catch (InvalidUsername except){
                System.out.println(except.getMessage());
                return;
            }
            if(findInArray(username) != -1 )
                System.out.println("this user name is defined before,select another username");
            else{
                usernameArray[emptyHomeIndex] = username;
                emptyHomeIndex++;
                break;
            }
        }
        System.out.print("Enter your password:");
        password = input.nextLine();
        User user = new User(firstName,lastName,username,password);
        if(userService.importUser(user) != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else
            System.out.println("Sing up is ERROR!,please try again");
    }

    //::::>
    public int findInArray(String username){
        for(int i=0;i<emptyHomeIndex;i++)
            if(usernameArray[i].equals(username))
                return i;
            return -1;
    }

    //::::>
    public int selectMenu(String username,String password) throws SQLException {
        if(adminService.findAdmin(username,password) != null )
            return 1;
        else if(cinemaService.findCinema(username,password) != null)
            return 2;
        else if(userService.findUser(username,password) != null)
            return 3;
        else
            return 0;
    }

    //::::>
    public void searchFilm() throws SQLException {
        System.out.print("Please enter film name:");
        input1 = input.nextLine();
        System.out.print("Please enter film date(yyyy-dd-mm):");
        timeDate = input.nextLine();
        Date timeD = Date.valueOf(timeDate);
        ticketService.searchWithName(input1,timeD);
    }

    //::::>
    public void viewMyBasket(String username) throws SQLException {
        basketService.viewMyBasket(username);
    }

    //::::>
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

    //::::>
    public void listHighPurchase(String username,String password) throws SQLException {
        cinemaName = cinemaService.findCinema(username,password);
        ticketService.showListHighPurchase(cinemaName);
    }

    //::::>
    public int isConfirm(String username,String password) throws SQLException {
        cinemaName = cinemaService.findCinema(username,password);
        if(cinemaService.isConfirm(cinemaName) == 0 )
            return 0;

        return 1;
    }



}
