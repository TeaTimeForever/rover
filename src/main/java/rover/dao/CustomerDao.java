package rover.dao;

import com.mongodb.DB;
import rover.domain.Customer;

import javax.inject.Inject;

/**
 * Created by eq on 05/03/16.
 */
public class CustomerDao extends GenericDao<Customer>{

    private final String COLLECTION_NAME = "customers";

    @Inject
    public CustomerDao(DB db){
        super(db);
    }

    @Override protected String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override protected Class<Customer> getTargetClass() {
        return Customer.class;
    }
}
