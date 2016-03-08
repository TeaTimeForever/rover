package testHelpers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by eq on 08/03/16.
 */
public class RoverTestConfiguration {

    private String mongoHost;
    private int mongoPort;
    private String databaseName;

    public String getMongoHost() {
        return mongoHost;
    }

    public void setMongoHost(String mongoHost) {
        this.mongoHost = mongoHost;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public static RoverTestConfiguration loadFromYml(){
        Yaml yaml = new Yaml();
        try {
            FileInputStream in = new FileInputStream("./test_config.yml");
            return yaml.loadAs(in, RoverTestConfiguration.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
