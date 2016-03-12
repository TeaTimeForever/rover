package rover;

import com.google.inject.servlet.ServletModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.meltmedia.dropwizard.mongo.MongoBundle;
import com.mongodb.DB;
import io.dropwizard.Application;
import io.dropwizard.jersey.gzip.GZipDecoder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import rover.health.RoverHealthCheck;
import rover.resources.LoanResource;
import rover.resources.MainResource;
import rover.utils.CountryFrequencyFilter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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

    public void updateDB() throws IOException {
        URL website = new URL("http://geolite.maxmind.com/download/geoip/database/GeoLite2-City.mmdb.gz");
        GZIPInputStream gz = new GZIPInputStream(website.openStream());
        ReadableByteChannel rb = Channels.newChannel(gz);
        FileOutputStream f = new FileOutputStream("GeoLite2-Country.mmdb");
        f.getChannel().transferFrom(rb, 0, Long.MAX_VALUE);
    }

    @Override
    public void initialize(Bootstrap<RoverConfiguration> bootstrap) {
        System.setProperty("java.net.preferIPv4Stack" , "true");

        bootstrap.addBundle(mongoBundle = MongoBundle.<RoverConfiguration>builder()
                .withConfiguration(RoverConfiguration::getMongo)
                        .build());

        bootstrap.addBundle(guiceBundle = GuiceBundle.<RoverConfiguration>newBuilder()
        .addModule(new ServletModule() {
            @Override protected void configureServlets() {
                bind(DB.class).toProvider(() -> mongoBundle.getDB());
                filter("/loans").through(CountryFrequencyFilter.class);
            }
        }).setConfigClass(RoverConfiguration.class)
        .build());

        try {
            updateDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
