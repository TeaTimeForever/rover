package rover.dao;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import rover.domain.Customer;
import rover.domain.Loan;
import rover.utils.JongoFilter;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eq on 05/03/16.
 */
public class CustomerDao {

    private Jongo jongo;

    private final String COLLECTION_NAME = "customers";

    @Inject
    public CustomerDao(DB db){
        this.jongo = new Jongo(db);
    }

    private MongoCollection getCollection(){
        return jongo.getCollection(COLLECTION_NAME);
    }

    public Loan find(ObjectId id){
        return getCollection()
                .findOne(id)
                .as(Loan.class);
    }

    public List<Customer> load(JongoFilter filter){
        return Lists.newArrayList((Iterator<? extends Customer>)
                getCollection()
                        .find(filter.getQuery(), filter.getArgs())
                        .as(Customer.class));
    }


}
