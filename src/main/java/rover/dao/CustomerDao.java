package rover.dao;

import com.mongodb.DB;

import javax.inject.Inject;

/**
 * Created by eq on 05/03/16.
 */
public class CustomerDao {

    private final DB db;

    @Inject
    public CustomerDao(DB db){
        this.db = db;
    }


}
