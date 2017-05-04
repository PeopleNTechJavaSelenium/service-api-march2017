package resources.databases;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mrahman on 5/3/17.
 */
public class ProfileOperation {
    AuthenticationDatabaseServices UserDatabaseServices = new AuthenticationDatabaseServices();

//    public EmployeeProfile getUserProfile(String empEmail){
//        EmployeeProfile user =  AuthenticationDatabaseServices.login(empEmail);
//
//        return user;
//    }
//    public EmployeeProfile verifyUserProfile(EmployeeProfile user)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        EmployeeProfile adminUserProfile = AuthenticationDatabaseServices.login(user.getEmpEmail());
//        verifyUser(user);
//        return adminUserProfile;
//    }
//    public EmployeeProfile verifyValidUser(EmployeeProfile user){
//        EmployeeProfile adminUserProfile = AuthenticationDatabaseServices.login(user.getEmpEmail());
//
//        if(adminUserProfile.empEmail.equals(user.getEmpEmail())) {
//
//            if (adminUserProfile.password.equals(user.password)) {
//
//                return adminUserProfile;
//
//            }else{
//                return null;
//            }
//        }else{
//
//            return null;
//        }
//    }
    public boolean verifyUser(EmployeeProfile user)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        boolean loginUser = UserDatabaseServices.loginVerify(user.getEmpEmail(), user.getPassword());

        return loginUser;
    }
    public boolean verifyUser(String userProfile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        EmployeeProfile user = new EmployeeProfile();
        boolean loginUser = UserDatabaseServices.loginVerify(user.getEmpEmail(), user.getPassword());

        return loginUser;
    }

    public boolean registerProfile(EmployeeProfile user)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        boolean message = UserDatabaseServices.userRegistration(user);
        return message;
    }


    public String updateUserProfileSetting(EmployeeProfile user){
        String message = UserDatabaseServices.updateAdminUserProfile(user);

        return message;
    }
}
