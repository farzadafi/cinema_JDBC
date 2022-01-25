package Manager;

public class InvalidName extends RuntimeException{
    public InvalidName() {
    }

    public InvalidName(String message) {
        super(message);
    }

    public InvalidName(String message, Throwable cause) {
        super(message, cause);
    }

    public void checkName(String name){
        if(name.length() < 3 )
            throw new InvalidName("name should be more than 2 character!");
        for (Character ch:name.toCharArray()
        ) {
            if(Character.isDigit(ch))
                throw new InvalidName("name can not have number!");
        }
        for (Character ch:name.toCharArray()
        ) {
            if(!Character.isAlphabetic(ch))
                throw new InvalidName("name can't have Sign(!,@,#,%,...)");

        }
    }
}