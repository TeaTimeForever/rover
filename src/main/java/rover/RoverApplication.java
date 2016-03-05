package rover;

import com.meltmedia.dropwizard.mongo.MongoBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import rover.health.RoverHealthCheck;
import rover.resources.MainResource;

/**
 * Created by eq on 05/03/16.
 */
public class RoverApplication extends Application<RoverConfiguration>{

    private MongoBundle<RoverConfiguration> mongoBundle;

    public static void main(String[] args) throws Exception {
        new RoverApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<RoverConfiguration> bootstrap) {
        bootstrap.addBundle(mongoBundle = MongoBundle.<RoverConfiguration>builder()
                .withConfiguration(RoverConfiguration::getMongo)
                        .build());
    }

    @Override
    public void run(RoverConfiguration configuration, Environment environment) {
        final MainResource resource = new MainResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final RoverHealthCheck healthCheck =
                new RoverHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
//        environment.jersey().register(new RootResource(mongoBundle.getDB()));
    }
}
