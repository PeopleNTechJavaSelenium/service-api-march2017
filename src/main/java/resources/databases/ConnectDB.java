package resources.databases;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * Created by PIIT_NYA on 4/30/2017.
 */
public class ConnectDB {

    private static SocketFactory _sf = null;
    private static MongoDatabase mongoDatabase = null;
    private static MongoClient mongoClient = null;

    public MongoClient connectToRecommendedSSLAtlasMongoClient() {
        try {

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, trustAllCerts, null);
            _sf = context.getSocketFactory();

        } catch (GeneralSecurityException e) {
            System.out.println(e.getStackTrace());
        }
        String userName = "syhoss";
        String authDB = "admin";
        char[] password = new char[]{'S','e','l','e','n','i','u','m','2','!'};
        MongoCredential credential = MongoCredential.createCredential(userName, authDB, password);

        MongoClientOptions.Builder optionBuilder = new MongoClientOptions.Builder();
        optionBuilder.sslEnabled(true);
        optionBuilder.socketFactory(_sf);
        MongoClientOptions options = optionBuilder.build();

        mongoClient = new MongoClient(
                Arrays.asList(
                        new ServerAddress("cluster0-shard-00-00-es9ob.mongodb.net", 27017),
                        new ServerAddress("cluster0-shard-00-01-es9ob.mongodb.net", 27017),
                        new ServerAddress("cluster0-shard-00-02-es9ob.mongodb.net", 27017)
                ),
                Arrays.asList(credential), options);
       // mongoDatabase = mongoClient.getDatabase("dev");

        return mongoClient;
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
    }};

}
