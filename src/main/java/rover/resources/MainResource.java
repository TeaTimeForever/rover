package rover.resources;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.name.Named;
import rover.api.Saying;
import rover.services.Service;

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
    private final Service service;

    @Inject
    public MainResource(@Named("template") String template, @Named("defaultName") String defaultName, Service service) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.service = service;
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), service.sayHello());
    }
}
