package rover;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.meltmedia.dropwizard.mongo.MongoBundle;
import com.mongodb.DB;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import rover.health.RoverHealthCheck;
import rover.resources.LoanResource;
import rover.resources.MainResource;

/**
 * Created by eq on 05/03/16.
 */
public class RoverApplication extends Application<RoverConfiguration>{

    private MongoBundle<RoverConfiguration> mongoBundle;
    private GuiceBundle<RoverConfiguration> guiceBundle;

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

        bootstrap.addBundle(guiceBundle = GuiceBundle.<RoverConfiguration>newBuilder()
        .addModule(new AbstractModule() {
            @Override protected void configure() {
                bind(DB.class).toProvider(() -> mongoBundle.getDB());

            }

            @Provides
            @Named("defaultName")
            public String getDefaultName(RoverConfiguration config){
                return config.getDefaultName();
            }

            @Provides
            @Named("template")
            public String getTemplate(RoverConfiguration config){
                return config.getTemplate();
            }
        })
        .setConfigClass(RoverConfiguration.class)
        .build());
    }

    @Override
    public void run(RoverConfiguration configuration, Environment environment) {
        final RoverHealthCheck healthCheck =
                new RoverHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(MainResource.class);
        environment.jersey().register(LoanResource.class);
    }
}
