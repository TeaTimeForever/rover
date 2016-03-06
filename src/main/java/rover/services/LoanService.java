package rover.services;

import org.bson.types.ObjectId;
import rover.dao.CustomerDao;
import rover.domain.Customer;
import rover.utils.JongoFilter;
import rover.dao.LoanDao;
import rover.domain.Loan;

import javax.inject.Inject;
import java.math.BigDecimal;
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

    public List<Loan> load(JongoFilter loanFilter, JongoFilter customerFilter){
        customerDao.load(customerFilter)
                   .forEach(c -> loanFilter.addParam(Loan.CUSTOMER_ID, c.getId()));
        loanFilter.buildQuery();
        return loanDao.load(loanFilter);
    }

    public void processNewLoan(String personalId, String surname, String name, BigDecimal loanAmount, Long term) {
        Loan loan = new Loan();
        loan.setAmount(loanAmount);
        loan.setTerm(term);
        loan.setCurrency("LV");

        JongoFilter customerFilter = JongoFilter.get()
                .addParam(Loan.CUSTOMER_ID, personalId);
        Customer customer = customerDao.load(customerFilter).get(0);

    }
}
