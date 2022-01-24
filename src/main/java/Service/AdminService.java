package Service;

import Entity.Admin;
import Repository.AdminRepository;

import java.sql.SQLException;

public class AdminService {
    private AdminRepository adminRepository = new AdminRepository();

    public AdminService() throws SQLException, ClassNotFoundException {
    }

    public int importAdmin(Admin admin) throws SQLException {
        return adminRepository.importAdmin(admin);
    }
}
