package rover.dao;

import com.google.api.client.util.Lists;
import com.mongodb.DB;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import rover.utils.JongoFilter;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eq on 07/03/16.
 */
public abstract class GenericDao<T> {
    protected final Jongo jongo;

    protected abstract String getCollectionName();
    protected abstract Class<T> getTargetClass();

    @Inject
    protected GenericDao(DB db){
        this.jongo = new Jongo(db);
    }

    protected MongoCollection getCollection(){
        return jongo.getCollection(getCollectionName());
    }

    public T find(ObjectId id){
        return getCollection()
                .findOne(id)
                .as(getTargetClass());
    }

    public List<T> load(JongoFilter filter) {
        return Lists.newArrayList((Iterator<T>)
                getCollection()
                        .find(filter.getQuery(), filter.getArgs())
                        .as(getTargetClass()));
    }

    public T insert(T obj) {
        getCollection().insert(obj);
        return obj;
    }

    public T findOne(JongoFilter filter) {
        return getCollection()
                .findOne(filter.getQuery(), filter.getArgs())
                .as(getTargetClass());
    }

    public T upsert(JongoFilter filter, T obj) {
        getCollection()
                .update(filter.getQuery(), filter.getArgs())
                .upsert()
                .with(obj);

        //TODO: that a shame, mongos shame, not mine
        return findOne(filter);
    }
}
