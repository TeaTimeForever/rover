package testHelpers;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;

/**
 * Created by eq on 08/03/16.
 */
public class MongoTestModule extends AbstractModule {

    private final String HOST = "192.168.99.100:32770";
    private final int PORT = 27017;
    private final String DB_NAME = "rover-test";


    @Provides
    @Singleton
    public Jongo getDB(){
        MongoClient client = new MongoClient(HOST, PORT);
        DB db = client.getDB(DB_NAME);
        return new Jongo(db);

    }

    @Override protected void configure() {}
}
