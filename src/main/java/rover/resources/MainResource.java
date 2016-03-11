package rover.resources;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.name.Named;
import rover.api.Saying;
import rover.services.GeoService;
import rover.services.LoanService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/what_is_my_ip")
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {
    private final AtomicLong counter;
    private final LoanService loanService;
    private final GeoService geoService;

    @Inject
    public MainResource(LoanService loanService, GeoService geoService) {
        this.counter = new AtomicLong();
        this.loanService = loanService;
        this.geoService = geoService;
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("ip") String ip) {
        return new Saying(counter.incrementAndGet(), geoService.getCountryByIp(ip));
    }
}
