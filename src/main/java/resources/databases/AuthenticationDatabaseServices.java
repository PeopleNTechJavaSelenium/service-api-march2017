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


        public boolean adminRegistration(AdminProfile user) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            boolean message = false;
            try {
                mongoClient = connectMongo.connectToRecommendedSSLAtlasMongoClient();
                MongoDatabase mongoDatabase = mongoClient.getDatabase("admin_profile");
                MongoCollection<Document> collection = mongoDatabase.getCollection("login");
                if (userNameList.contains(user.getEmail())) {
                    Document document = new Document().append("email", user.getEmail()).append("password", user.getPassword());
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

        public String updateAdminUserProfile(AdminProfile user) {
            String profile = user.getEmail();
            try {
                mongoClient = connectMongo.connectToRecommendedSSLAtlasMongoClient();
                mongoDatabase = mongoClient.getDatabase("admin_profile");
                MongoCollection<Document> collection = mongoDatabase.getCollection("login");
                Document document = new Document().append("email", user.getEmail()).append("password", user.getPassword());
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


        public AdminProfile register(String email) {
            AdminProfile user = new AdminProfile();

            try {
                mongoClient = connectMongo.connectToRecommendedSSLAtlasMongoClient();
                MongoDatabase mongoDatabase = mongoClient.getDatabase("admin_profile");
                MongoCollection<Document> collection = mongoDatabase.getCollection("login");
                Document document = new Document().append("email", user.getEmail()).append("password", user.getPassword());
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
                MongoDatabase mongoDatabase = mongoClient.getDatabase("admin_profile");
                MongoCollection<Document> collection = mongoDatabase.getCollection("login");
                BasicDBObject basicDBObject = new BasicDBObject();
                basicDBObject.put("email", email);
                FindIterable<Document> iterable = collection.find(basicDBObject);
                for (Document doc : iterable) {
                    String emailPosted = (String) doc.get("email");
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

