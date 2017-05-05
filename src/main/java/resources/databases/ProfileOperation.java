package resources.databases;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mrahman on 5/3/17.
 */
public class ProfileOperation {

    AuthenticationDatabaseServices UserDatabaseServices = new AuthenticationDatabaseServices();

    public boolean verifyUser(AdminProfile user)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        boolean loginUser = UserDatabaseServices.loginVerify(user.getEmail(), user.getPassword());

        return loginUser;
    }
    public boolean verifyUser(String userProfile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        AdminProfile user = new AdminProfile();
        boolean loginUser = UserDatabaseServices.loginVerify(user.getEmail(), user.getPassword());

        return loginUser;
    }

    public boolean registerProfile(AdminProfile user)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        boolean message = UserDatabaseServices.adminRegistration(user);
        return message;
    }


    public String updateUserProfileSetting(AdminProfile user){
        String message = UserDatabaseServices.updateAdminUserProfile(user);

        return message;
    }
}
