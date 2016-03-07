package rover.dao;

import com.mongodb.DB;
import rover.domain.Loan;

import javax.inject.Inject;

/**
 * Created by eq on 05/03/16.
 */
public class LoanDao extends GenericDao<Loan>{

    private final String COLLECTION_NAME = "loans";

    @Inject
    public LoanDao(DB db){
        super(db);
    }

    @Override protected String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override protected Class<Loan> getTargetClass() {
        return Loan.class;
    }
}
