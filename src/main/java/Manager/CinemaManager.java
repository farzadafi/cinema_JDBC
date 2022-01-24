package Manager;

import Service.CinemaService;

import java.sql.SQLException;
import java.util.Scanner;

public class CinemaManager {
    private Scanner input = new Scanner(System.in);
    private String input1;
    private CinemaService cinemaService = new CinemaService();

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
}
