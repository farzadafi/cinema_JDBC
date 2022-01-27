package Entity;

public class Login {
    private String username;
    private String password;
    private TypeUser typeUser;

    public Login(String username, String password, TypeUser typeUser) {
        this.username = username;
        this.password = password;
        this.typeUser = typeUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}
