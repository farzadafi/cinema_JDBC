package Manager;

public class InvalidUsername extends RuntimeException{
    public InvalidUsername() {
    }

    public InvalidUsername(String message) {
        super(message);
    }

    public InvalidUsername(String message, Throwable cause) {
        super(message, cause);
    }

    public void checkUsername(String username){
        if(username.length() < 3 )
            throw new InvalidUsername("Username should be more than 2!");
        char ch = username.charAt(0);
        if(Character.isDigit(ch))
            throw new InvalidUsername("Username can not start with digit!");
    }
}