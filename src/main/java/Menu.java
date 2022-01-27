import Manager.*;
import Service.LoginService;

import java.sql.SQLException;
import java.util.*;

public class Menu {
    int command;
    String input1,input2;
    Scanner input = new Scanner(System.in);
    private CinemaManager cinemaManager = new CinemaManager();
    private TicketManager ticketManager = new TicketManager();
    private AdminManger adminManger = new AdminManger();
    private UserManager userManager = new UserManager();
    private LoginService loginService = new LoginService();

    public Menu() throws SQLException, ClassNotFoundException {
    }

    //:::::>
    public int publicMenu() throws SQLException {
        System.out.println("**********WELCOME**********");
        System.out.println("1-Sign in(Enter).");
        System.out.println("2-Sign up(Register).");
        System.out.println("3-Exit.");
        System.out.print("Please select a number:");
        try {
            command = input.nextInt();
        }catch (InputMismatchException exception){
            input.nextLine();
            System.out.println("You can just Enter number!");
            return -1;
        }
        input.nextLine();
        switch(command)
        {
            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            default:
                return 0;
        }
    }

    //:::::>
    public void RegisterMenu() throws SQLException {
        System.out.println("Who are you?");
        System.out.println("1-Admin.");
        System.out.println("2-Cinema.");
        System.out.println("3-User.");
        System.out.print("Please select a number:");
        try {
            command = input.nextInt();
        }catch (InputMismatchException exception){
            input.nextLine();
            System.out.println("You can just Enter number!");
            return;
        }
        input.nextLine();
        switch(command)
        {

            case 1:
                adminManger.registerAdmin();
                break;

            case 2:
                cinemaManager.registerCinema();
                break;

            case 3:
                userManager.registerUser();
                break;
        }
    }

    public int selectMenu(String username,String password) throws SQLException {
        String result = loginService.find(username,password);
        if(result.equals("ADMIN"))
            return 1;
        else if (result.equals("CINEMA"))
            return 2;
        else if (result.equals("USER"))
            return 3;
        else
            return 0;
    }


        //:::::>
    public void enterMenu() throws SQLException {
        System.out.print("Please enter your user name:");
        input1 = input.nextLine();
        System.out.print("Please enter your password:");
        input2 = input.nextLine();
        //switch (manager.selectMenu(input1, input2))
        switch (selectMenu(input1,input2))
        {
            case 0:
                System.out.println("You enter a wrong user name and password!");
                break;

            case 1:
                boolean isFalse = true;
                while(isFalse)
                {

                    System.out.println("**********WELCOME Admin**********");
                    System.out.println("1-Confirm Cinema Account.");
                    System.out.println("2-Exit.");
                    System.out.print("Please select a number:");
                    try {
                        command = input.nextInt();
                    }catch (InputMismatchException exception){
                        input.nextLine();
                        System.out.println("You can just Enter number!");
                        return;
                    }
                    input.nextLine();
                    switch(command)
                    {
                        case 1:
                            cinemaManager.confirmCinema();
                            break;

                        case 2:
                            System.out.println("Good luck!");
                            isFalse = false;
                            break;

                        default :
                            System.out.println("You enter a wrong number!");

                    }
                }
                break;

            case 2:
                if(cinemaManager.isConfirm(input1,input2) == 0 ) {
                    System.out.println("Unfortunately Admin not confirm you,please call to Admin!");
                    break;
                }

                isFalse = true;
                while(isFalse) {
                    System.out.println("**********WELCOME Cinema Manager**********");
                    System.out.println("1-add Ticket.");
                    System.out.println("2-cancel Ticket.");
                    //System.out.println("3-*Add OFF code*");
                    System.out.println("4-*view high purchase film*");
                    System.out.println("5-Exit.");
                    System.out.print("Please select a number:");
                    try {
                        command = input.nextInt();
                    }catch (InputMismatchException exception){
                        input.nextLine();
                        System.out.println("You can just Enter number!");
                        return;
                    }
                    input.nextLine();
                    switch (command) {
                        case 1:
                            ticketManager.addTicket(input1,input2);
                            break;

                        case 2:
                            ticketManager.delTicket(input1,input2);
                            break;

                        case 3:
                            cinemaManager.addOffCode();
                            break;

                        case 4:
                            ticketManager.listHighPurchase(input1,input2);
                            break;

                        case 5:
                            System.out.println("Good luck!");
                            isFalse = false;
                            break;

                        default:
                            System.out.println("You enter a wrong number!");
                    }
                }
                break;

            case 3:
                isFalse = true;
                while(isFalse)
                {
                    System.out.println("**********WELCOME User**********");
                    System.out.println("1-Reserve Ticket.");
                    System.out.println("2-View all Tickets");
                    System.out.println("3-Search with name and date.");
                    System.out.println("4-*view my Basket*");
                    System.out.println("5-Exit.");
                    System.out.print("Please select a number:");
                    try {
                        command = input.nextInt();
                    }catch (InputMismatchException exception){
                        input.nextLine();
                        System.out.println("You can just Enter number!");
                        return;
                    }
                    input.nextLine();
                    switch(command)
                    {
                        case 1:
                            ticketManager.reserveTicket(input1);
                            break;

                        case 2:
                            ticketManager.showAllTicket();
                            break;

                        case 3:
                            ticketManager.searchFilm();
                            break;

                        case 4:
                            userManager.viewMyBasket(input1);
                            break;

                        case 5:
                            System.out.println("Good luck!");
                            isFalse = false;
                            break;

                        default :
                            System.out.println("You enter a wrong number!");
                    }
                }
                break;

            default:
                System.out.println("You enter a wrong number");
        }
    }
}
