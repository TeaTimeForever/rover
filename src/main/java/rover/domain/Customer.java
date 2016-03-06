package rover.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.jongo.marshall.jackson.oid.MongoId;

/**
 * Created by eq on 04/03/16.
 */
public class Customer {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PERSONAL_ID= "personalId";

    @MongoId
    private ObjectId id;

    @Length(max=64)
    private String name;

    private String personalId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
}
