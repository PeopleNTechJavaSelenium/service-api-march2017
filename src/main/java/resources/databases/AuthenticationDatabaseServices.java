package resources.databases;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mrahman on 5/2/17.
 */
public class AuthenticationDatabaseServices {
        public ConnectDB connectMongo = new ConnectDB();
        public MongoClient mongoClient = null;
        public MongoDatabase mongoDatabase = null;
        public List<String> userNameList = Arrays.asList("rahmanww@gmail.com");


        public boolean userRegistration(EmployeeProfile user) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            boolean message = false;
            try {
                mongoClient = connectMongo.connectToRecommendedSSLAtlasMongoClient();
                MongoDatabase mongoDatabase = mongoClient.getDatabase("ProfileDB");
                MongoCollection<Document> collection = mongoDatabase.getCollection("admin_login");
                if (userNameList.contains(user.getEmpEmail())) {
                    Document document = new Document().append("empEmail", user.getEmpEmail()).append("password", user.getPassword());
                    collection.insertOne(document);
                    message = true;
                } else {
                    message = false;
                }
                mongoClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (mongoClient != null) {

                    mongoClient = null;
                }
            }
            return message;
        }

        public String updateAdminUserProfile(EmployeeProfile user) {
            String profile = user.getEmpEmail();
            try {
                mongoClient = connectMongo.connectToRecommendedSSLAtlasMongoClient();
                mongoDatabase = mongoClient.getDatabase("AdminProfileDB");
                MongoCollection<Document> collection = mongoDatabase.getCollection("admin_login");
                Document document = new Document().append("empEmail", user.getEmpEmail()).append("password", user.getPassword());
                mongoClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (mongoClient != null) {

                    mongoClient = null;
                }
            }

            return profile + " has been updated";
        }


        public EmployeeProfile register(String email) {
            EmployeeProfile user = new EmployeeProfile();

            try {
                mongoClient = connectMongo.connectToRecommendedSSLAtlasMongoClient();
                MongoDatabase mongoDatabase = mongoClient.getDatabase("AdminProfileDB");
                MongoCollection<Document> collection = mongoDatabase.getCollection("admin_login");
                Document document = new Document().append("empEmail", user.getEmpEmail()).append("password", user.getPassword());
                collection.insertOne(document);
                mongoClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (mongoClient != null) {

                    mongoClient = null;
                }
            }

            return user;
        }


        public boolean loginVerify(String email, String password) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            EmployeeProfile user = new EmployeeProfile();
            boolean verify = false;
            try {

                mongoClient = connectMongo.connectToRecommendedSSLAtlasMongoClient();
                MongoDatabase mongoDatabase = mongoClient.getDatabase("AdminProfileDB");
                MongoCollection<Document> collection = mongoDatabase.getCollection("admin_login");
                BasicDBObject basicDBObject = new BasicDBObject();
                basicDBObject.put("empEmail", email);
                FindIterable<Document> iterable = collection.find(basicDBObject);
                for (Document doc : iterable) {
                    String emailPosted = (String) doc.get("empEmail");
                    String passwordPosted = (String) doc.get("password");
                    user.setEmpEmail(emailPosted);
                    user.setPassword(passwordPosted);
                    //user.setValue(true);
                }
                if (user.getEmpEmail().equals(email) && user.getPassword().equals(password)) {
                    verify = true;
                } else {
                    verify = false;
                }

                mongoClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (mongoClient != null) {

                    mongoClient = null;
                }
            }
            return verify;

        }
}

