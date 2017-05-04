package resources.rest;

import resources.databases.EmployeeProfile;
import resources.databases.ProfileOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mrahman on 5/3/17.
 */


@Path("LogIn")
public class LogIn {

    ProfileOperation profileOperation = new ProfileOperation();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean postIt(EmployeeProfile employeeProfile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return profileOperation.verifyUser(employeeProfile);
    }

    @GET
    @Path("/{profileId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean loginProfile(@PathParam("profileId")String profile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return profileOperation.verifyUser(profile);
    }

}
