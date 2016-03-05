package rover.services;

import org.bson.types.ObjectId;
import rover.dao.CustomerDao;
import rover.utils.JongoFilter;
import rover.dao.LoanDao;
import rover.domain.Loan;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by eq on 05/03/16.
 */
public class LoanService {

    private final LoanDao loanDao;
    private final CustomerDao customerDao;

    @Inject
    public LoanService(LoanDao loanDao, CustomerDao customerDao){
        this.loanDao = loanDao;
        this.customerDao = customerDao;
    }

    public List<Loan> load(JongoFilter filter){
        return loanDao.load(filter);
    }

    public Loan find(ObjectId id){
        return loanDao.find(id);
    }


    public List<Loan> loadByCustomer(JongoFilter customerFilter){
        JongoFilter loanFilter = JongoFilter.get();
        System.out.println("\n");
        customerDao.load(customerFilter)
                   .forEach(c -> {
                       System.out.println(c.getName() + " " + c.getId() + " " + c.getPersonalId());
                       loanFilter.addParam("customerId", c.getId());
                   });
        loanFilter.buildQuery();
        return loanDao.load(loanFilter);
    }
}
