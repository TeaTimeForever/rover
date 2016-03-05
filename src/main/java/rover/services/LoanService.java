package rover.services;

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

    @Inject
    public LoanService(LoanDao loanDao){
        this.loanDao = loanDao;
    }

    public List<Loan> load(JongoFilter filter){
        return loanDao.load(filter);
    }

}
