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
 * Created by mrahman on 5/4/17.
 */

@Path("AllEmployeeResources")
public class AllEmployeeResources {


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
            boolean postMessage = employeeDatabaseServices.insertVCProfileNReturn(profile);
            return postMessage;

        }
}
