package rover.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

/**
 * Created by eq on 04/03/16.
 */
public class Customer {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PERSONAL_ID= "personalId";

    @MongoId
    @MongoObjectId
    private String id;

    @Length(max=64)
    private String name;

    private String personalId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setName(String firstName, String lastName) {
        name = firstName + " " + lastName;
    }

    @JsonProperty
    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
}
