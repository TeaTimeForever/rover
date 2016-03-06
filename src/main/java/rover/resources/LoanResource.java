package rover.resources;

import com.codahale.metrics.annotation.Timed;
import org.bson.types.ObjectId;
import rover.dto.NewLoanRequestDTO;
import rover.utils.JongoFilter;
import rover.domain.Loan;
import rover.services.LoanService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/loans")
@Produces(MediaType.APPLICATION_JSON)
public class LoanResource {

    private final LoanService loanService;

    @Inject
    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    @GET
    @Timed
    public List<Loan> getLoans(
            @QueryParam("personal_id") String personalId,
            @QueryParam("status") Boolean status) {
        JongoFilter customerFilter = JongoFilter.get()
                .addParam("personalId", personalId)
                .buildQuery();

        JongoFilter loanFilter = JongoFilter.get()
                .addParam("status", status)
                .buildQuery();
        return loanService.load(loanFilter, customerFilter);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public NewLoanRequestDTO saveLoan(NewLoanRequestDTO newLoan){
        loanService.processNewLoan(
                newLoan.getPersonalId(),
                newLoan.getSurname(),
                newLoan.getName(),
                newLoan.getLoanAmount(),
                newLoan.getTerm());
        return newLoan;
    }

    @GET
    @Path("/{loanId}")
    @Timed
    public Loan getLoansByCustomerPId(@PathParam("loanId") String loanId){
        return loanService.find(new ObjectId(loanId));
    }
}
