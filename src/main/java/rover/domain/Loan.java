package rover.domain;

import org.bson.types.ObjectId;

import java.math.BigDecimal;

/**
 * Created by eq on 04/03/16.
 */
public class Loan {
    private ObjectId id;
    private Boolean approved;
    private String countryRequestor;
    private BigDecimal amount;
    private String currency;
    private Long term;
    private Long customerId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getCountryRequestor() {
        return countryRequestor;
    }

    public void setCountryRequestor(String countryRequestor) {
        this.countryRequestor = countryRequestor;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
