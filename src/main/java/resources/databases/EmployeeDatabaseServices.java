package resources.databases;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrahman on 5/3/17.
 */
public class EmployeeDatabaseServices {

    public ConnectDB connectDB = new ConnectDB();
    public MongoClient mongoClient = new MongoClient();
    public static EmployeeFields employeeFields = new EmployeeFields();
    public EmployeeProfile employeeProfile = null;
    public List<EmployeeProfile> employeeProfileList = null;

    public boolean insertVCProfileNReturn(EmployeeProfile profile)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        try {
            String st = profile.getEmpName() + " " + "is Inserted";
            MongoClient mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("dev");
            MongoCollection mongoCollection = mongoDatabase.getCollection("profile");
            Document vcInfoDocument = documentEmployeeInfoData(profile);

            Document preparedDocument = new Document("empInfo",vcInfoDocument);

            mongoCollection.insertOne(preparedDocument);
            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (mongoClient != null) {

                mongoClient = null;
            }
        }

        return true;
    }

    public static Document documentEmployeeInfoData(EmployeeProfile profile){
        Document document = new Document().append(employeeFields.empEmail, profile.getEmpEmail())
                .append(employeeFields.password, profile.getPassword())
                .append(employeeFields.empName, profile.getEmpName())
                .append(employeeFields.salary,profile.getSalary())
                .append(employeeFields.department, profile.getDepartment());

        return document;
    }

    public List<EmployeeProfile> queryListOfEmployeeByID(String vcID)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        List<EmployeeProfile> vcList = new ArrayList<EmployeeProfile>();
        vcList = readData(vcID);
        return vcList;
    }
    public List<EmployeeProfile> readData(String empID)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException{
        final List<EmployeeProfile> empList = new ArrayList<EmployeeProfile>();
        try{
            mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("dev");
            MongoCollection<Document> coll = mongoDatabase.getCollection("profile");
            BasicDBObject basicDBObject = new BasicDBObject("_id", new ObjectId(empID));
            FindIterable<Document> iterable = coll.find(basicDBObject);
            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    ObjectId idDocument = (ObjectId)document.get("id");
                    Document empInfoDocument = (Document) document.get("empInfo");
                    String dbID = idDocument.toString();
                    String empEmail = (String)empInfoDocument.get("empEmail");
                    String password = (String)empInfoDocument.get("password");
                    String empName = (String)empInfoDocument.get("empName");
                    String salary = (String)empInfoDocument.get("salary");
                    String department = (String)empInfoDocument.get("department");
                    employeeProfile = new EmployeeProfile(dbID,empEmail,password,empName,salary,department);
                    empList.add(employeeProfile);
                }

            });

            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {

            if (mongoClient != null) {

                mongoClient = null;
            }
        }
        return empList;
    }

    public List<EmployeeProfile> queryListOfEmployee()throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        List<EmployeeProfile> vcList = new ArrayList<EmployeeProfile>();
        vcList = readData();
        return vcList;
    }
    public List<EmployeeProfile> readData()throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException{
        final List<EmployeeProfile> empList = new ArrayList<EmployeeProfile>();
        try{
            mongoClient = connectDB.connectToRecommendedSSLAtlasMongoClient();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("dev");
            MongoCollection<Document> coll = mongoDatabase.getCollection("profile");
            FindIterable<Document> iterable = coll.find();
            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    ObjectId idDocument = (ObjectId)document.get("_id");
                    Document empInfoDocument = (Document) document.get("empInfo");
                    String dbID = idDocument.toString();
                    String empEmail = (String)empInfoDocument.get("empEmail");
                    String password = (String)empInfoDocument.get("password");
                    String empName = (String)empInfoDocument.get("empName");
                    String salary = (String)empInfoDocument.get("salary");
                    String department = (String)empInfoDocument.get("department");
                    employeeProfile = new EmployeeProfile(dbID,empEmail,password,empName,salary,department);
                    empList.add(employeeProfile);
                }

            });

            mongoClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {

            if (mongoClient != null) {

                mongoClient = null;
            }
        }
        return empList;
    }

    public static void main(String[] args) {

    }
}
