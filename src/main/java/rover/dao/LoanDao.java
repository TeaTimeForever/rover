package rover.dao;

import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import rover.utils.JongoFilter;
import rover.domain.Loan;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eq on 05/03/16.
 */
public class LoanDao {

    private Jongo jongo;

    private final String COLLECTION_NAME = "loans";

    @Inject
    public LoanDao(DB db){
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

    public List<Loan> load(JongoFilter filter){
        return Lists.newArrayList((Iterator<? extends Loan>)
                getCollection()
                        .find(filter.getQuery(), filter.getArgs())
                        .as(Loan.class));
    }

    public Loan insert(Loan loan){
        getCollection().insert(loan);
        return loan;
    }

}
