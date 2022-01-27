package Manager;

import java.util.Scanner;

public class Utility {
    private Scanner input = new Scanner(System.in);
    private InvalidName invalidName = new InvalidName();
    private InvalidUsername invalidUsername = new InvalidUsername();
    private InvalidPassword invalidPassword = new InvalidPassword();
    private String firstName,lastName,password,username;


    public String setFirstName(){
        while(true){
            System.out.print("Enter first name(just alpha):");
            try {
                firstName = input.nextLine();
                invalidName.checkName(firstName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return firstName;
    }

    public String setLastName(){
        while(true){
            System.out.print("Enter last name(just alpha):");
            try {
                firstName = input.nextLine();
                invalidName.checkName(firstName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return firstName;
    }

    public String setPassword(){
        while(true) {
            System.out.print("Enter your password:");
            try {
                password = input.nextLine();
                invalidPassword.passwordCheck(password);
                break;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
        return password;
    }

    public String setUsername(){
        while(true){
            System.out.print("Enter user name:");
            try {
                username = input.nextLine();
                invalidUsername.checkUsername(username);
                break;
            }catch (InvalidUsername except){
                System.out.println(except.getMessage());
            }
        }
        return username;
    }
}
