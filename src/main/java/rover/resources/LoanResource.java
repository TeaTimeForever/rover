package rover.resources;

import com.codahale.metrics.annotation.Timed;
import rover.utils.JongoFilter;
import rover.domain.Loan;
import rover.services.LoanService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {

    private final LoanService loanService;

    @Inject
    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    @GET
    @Timed
    public List<Loan> getAllLoans(@QueryParam("personal_id") String personalId) {
        JongoFilter filter = JongoFilter.get().addParam("personalId", personalId);
        return loanService.load(filter);
    }


    //particular loan
    @GET
    @Path("/{loanId}")
    @Timed
    public String getLoansByCustomerPId(@PathParam("loanId") String loanId){
        //return loanService.find(new ObjectId(loanId));
        return "in development";
    }


}
