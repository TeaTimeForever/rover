package rover.resources;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.name.Named;
import rover.api.Saying;
import rover.services.LoanService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    private final LoanService loanService;

    @Inject
    public MainResource(@Named("template") String template, @Named("defaultName") String defaultName, LoanService loanService) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.loanService = loanService;
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), "lalala");
    }
}
