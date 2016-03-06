package rover.dao;

import com.mongodb.DB;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import rover.utils.JongoFilter;

import javax.inject.Inject;

/**
 * Created by eq on 05/03/16.
 */
public class BlacklistDao {

    private Jongo jongo;

    private final String COLLECTION_NAME = "blacklist";

    @Inject
    public BlacklistDao(DB db){
        this.jongo = new Jongo(db);
    }

    private MongoCollection getCollection(){
        return jongo.getCollection(COLLECTION_NAME);
    }

    public boolean isPersonBlacklisted(String personalId){
        JongoFilter filter = JongoFilter.get()
                                        .addParam("personalId", personalId)
                                        .buildQuery();
        long count = getCollection().count(filter.getQuery(), filter.getArgs());
        return count > 0;
    }
}
