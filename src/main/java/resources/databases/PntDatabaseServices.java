package resources.databases;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by PIIT_NYA on 4/30/2017.
 */
public class PntDatabaseServices {

    static ConnectDB connectDB = new ConnectDB();
    static MongoDatabase mongoDatabase = null;
    public static void getProfile() throws KeyStoreException,NoSuchAlgorithmException,KeyManagementException{
       MongoClient mongoClient  = connectDB.connectToRecommendedSSLAtlasMongoClient();
       MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
       MongoCollection mongoCollection = mongoDatabase.getCollection("profile");
    }

    public static void main(String[] args)throws KeyStoreException,NoSuchAlgorithmException,KeyManagementException {
       getProfile();
    }

}
