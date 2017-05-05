package resources.rest;


import resources.databases.AdminProfile;
import resources.databases.AuthenticationDatabaseServices;
import resources.databases.EmployeeProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mrahman on 5/2/17.
 */

@Path("SignUp")
public class SignUp {

    AuthenticationDatabaseServices userDatabaseServices = new AuthenticationDatabaseServices();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean postIt(AdminProfile adminProfile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return userDatabaseServices.adminRegistration(adminProfile);
    }

    @PUT
    public String updateProfile(AdminProfile adminProfile) {
        return userDatabaseServices.updateAdminUserProfile(adminProfile);
    }

}
