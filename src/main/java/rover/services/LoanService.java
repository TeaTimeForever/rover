package rover.services;

import org.bson.types.ObjectId;
import rover.dao.BlacklistDao;
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
    private final BlacklistDao blacklistDao;

    @Inject
    public LoanService(LoanDao loanDao, CustomerDao customerDao, BlacklistDao blackListDao){
        this.loanDao = loanDao;
        this.customerDao = customerDao;
        this.blacklistDao = blackListDao;
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

    public Loan processNewLoan(String personalId, String surname, String name, BigDecimal loanAmount, Long term) {
        if(blacklistDao.isPersonBlacklisted(personalId)) return null;

        JongoFilter customerFilter = JongoFilter.get()
                                                .addParam(Customer.PERSONAL_ID, personalId)
                                                .buildQuery();

        Customer customer = new Customer();
        customer.setName(name, surname);
        customer.setPersonalId(personalId);
        customer = customerDao.upsert(customerFilter, customer);

        Loan loan = new Loan();
        loan.setAmount(loanAmount);
        loan.setTerm(term);
        loan.setCurrency("EUR");
        loan.setCustomerId(customer.getId());

        return loanDao.insert(loan);
    }
}
