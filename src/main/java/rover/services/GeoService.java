package rover.services;

import com.maxmind.geoip2.DatabaseReader;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by eq on 05/03/16.
 */
public class GeoService {
    private final String DB_NAME = "GeoLite2-Country.mmdb";
    private final String DEFAULT_ISO = "LV";
    private DatabaseReader reader;
    final static Logger logger = Logger.getLogger(GeoService.class);

    @Inject
    public GeoService(){
        File dbFile = new File(DB_NAME);
        try {
            reader = new DatabaseReader.Builder(dbFile).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCountryByIp(String ip){
        try {
            InetAddress address = InetAddress.getByName(ip);
            return reader.country(address).getCountry().getIsoCode();
        } catch (Exception e) {
            logger.warn("GetCountryByIp fail: "+ ip + " . Returned default ISO: " + DEFAULT_ISO);
            return DEFAULT_ISO;
        }
    }
}
