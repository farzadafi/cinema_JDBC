//In the name of God!

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Menu menu = new Menu();

        while(true)
        {
            switch(menu.publicMenu())
            {
                case 1:
                    menu.enterMenu();
                    break;

                case 2:
                    menu.RegisterMenu();
                    break;

                case 3:
                    System.out.println("Have a nice day!");
                    System.exit(0);

                case 0 :
                    System.out.println("You enter a wrong number!");

            }
        }
    }
}
