package Service;

import Entity.Login;
import Repository.LoginRepository;

public class LoginService {
    private LoginRepository loginRepository = new LoginRepository();

    public void add(Login login){
        loginRepository.add(login);
    }

    public String find(String username,String password){
        return loginRepository.find(username,password);
    }

    public boolean findUsername(String username){
        return loginRepository.findUsername(username);
    }

}
