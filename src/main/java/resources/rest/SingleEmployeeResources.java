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
        @Path("/{empId}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<EmployeeProfile> getCompanyList(@PathParam("empId") String empId) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            return employeeDatabaseServices.queryListOfEmployeeByID(empId);
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public boolean postOrganizationProfile(EmployeeProfile profile) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            System.out.println("POST Request has come to post to Insert an Emp profile");
            boolean postMessage = employeeDatabaseServices.insertVCProfileNReturn(profile);
            return postMessage;

        }

        @PUT
        @Path("/{empId}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public boolean updateOrganizationProfile(EmployeeProfile profile) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            boolean postMessage = employeeDatabaseServices.updateEmpProfileByIDNReturn(profile);
            return postMessage;
        }

        @DELETE
        @Path("/{vcId}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public void deleteOrganizationProfile(@PathParam("vcId")String profile) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            employeeDatabaseServices.deleteEmpProfileByIDNReturn(profile);

        }
}
