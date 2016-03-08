package services;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import rover.dao.CustomerDao;
import rover.domain.Customer;
import rover.domain.Loan;
import rover.services.LoanService;
import rover.utils.JongoFilter;
import testHelpers.TestModule;

import java.math.BigDecimal;

/**
 * Created by eq on 08/03/16.
 */
@RunWith(JukitoRunner.class)
@UseModules(TestModule.class)
public class LoanServiceTest extends Assert {
    private final String LOANS = "loans";
    private final String CUSTOMERS = "customers";
    private final String BLACKLIST = "blacklist";

    @Before
    public void cleanDB(Jongo jongo){
        jongo.getCollection(LOANS).drop();
        jongo.getCollection(CUSTOMERS).drop();
        jongo.getCollection(BLACKLIST).drop();
        jongo.getDatabase().createCollection(LOANS, new BasicDBObject());
        jongo.getDatabase().createCollection(CUSTOMERS,new BasicDBObject());
        jongo.getDatabase().createCollection(BLACKLIST, null);
    }

    @Test
    public void processNewLoan(LoanService loanService, CustomerDao customerDao){
        Loan insertedLoan = loanService.processNewLoan("111-11", "Spock", "Vulcan", BigDecimal.TEN, 5l, "LV");
        Loan foundLoan = loanService.find(new ObjectId(insertedLoan.getId()));
        Customer foundCustomer = customerDao.find(new ObjectId(foundLoan.getCustomerId()));

        assertEquals(insertedLoan.getId(), foundLoan.getId());
        assertEquals(insertedLoan.getCustomerId(), foundLoan.getCustomerId());
        assertEquals(insertedLoan.getStatus(), foundLoan.getStatus());
        assertEquals("Vulcan Spock", foundCustomer.getName());
        assertEquals("111-11", foundCustomer.getPersonalId());
    }

    @Test
    public void twoLoansForCustomer(LoanService loanService, CustomerDao customerDao) {
        loanService.processNewLoan("111-11", "Spock", "Vulcan", BigDecimal.TEN, 5l, "LV");
        Loan lastLoan = loanService.processNewLoan("111-11", "Data", "Mr", BigDecimal.ONE, 5l, "LV");
        Customer foundCustomer = customerDao.find(new ObjectId(lastLoan.getCustomerId()));

        assertEquals(customerDao.load(JongoFilter.get()).size(), 1);
        assertEquals(loanService.load(JongoFilter.get()).size(), 2);
        assertEquals("Mr Data", foundCustomer.getName());
    }

}
