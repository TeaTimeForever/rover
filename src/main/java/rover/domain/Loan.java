package rover.domain;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.math.BigDecimal;

/**
 * Created by eq on 04/03/16.
 */
public class Loan {

    private static final String ID = "_id";
    private static final String STATUS = "status";
    private static final String AMOUNT = "amount";
    private static final String CURRENCY = "currency";
    private static final String REQUESTED_FROM= "requestedFrom";

    @MongoId
    @MongoObjectId
    private String id;

    private Boolean status;
    private String requestedFrom;
    private BigDecimal amount;
    private String currency;
    private Long term;

    @MongoObjectId
    private String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(String requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
