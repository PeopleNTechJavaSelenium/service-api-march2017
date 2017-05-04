package resources.rest;

import resources.databases.EmployeeDatabaseServices;
import resources.databases.EmployeeProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by mrahman on 5/3/17.
 */

@Path("SingleEmployeeResources")
public class SingleEmployeeResources {

        EmployeeDatabaseServices employeeDatabaseServices = new EmployeeDatabaseServices();

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<EmployeeProfile> getCompanyList() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            return employeeDatabaseServices.queryListOfEmployee();
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public boolean postOrganizationProfile(EmployeeProfile profile) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            System.out.println("POST Request has come to post to Insert an Emp profile");
            boolean postMessage = employeeDatabaseServices.insertVCProfileNReturn(profile);
            return postMessage;

        }
}
