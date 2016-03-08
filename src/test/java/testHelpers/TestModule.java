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
public class TestModule extends AbstractModule {
    @Provides
    @Singleton
    public Jongo getDB(RoverTestConfiguration config){
        MongoClient client = new MongoClient(config.getMongoHost(), config.getMongoPort());
        DB db = client.getDB(config.getDatabaseName());
        return new Jongo(db);

    }

    @Provides
    @Singleton
    public RoverTestConfiguration getMainConfig(){
        return RoverTestConfiguration.loadFromYml();
    }

    @Override protected void configure() {}
}
