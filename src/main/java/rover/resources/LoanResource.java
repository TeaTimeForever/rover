package rover.resources;

import com.codahale.metrics.annotation.Timed;
import rover.dao.LoanDao;
import rover.domain.Loan;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {
    private final LoanDao loanDao;

    @Inject
    public LoanResource(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    @GET
    @Timed
    public List<Loan> getAllLoans() {
        return loanDao.load();
    }
}
