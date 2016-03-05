package rover.services;

import com.mongodb.DB;

import javax.inject.Inject;

/**
 * Created by eq on 05/03/16.
 */
public class Service {

    private final DB db;

    @Inject
    public Service(DB db){
        this.db = db;
    }

    public String sayHello(){
        return "hi, dude";
    }

}
