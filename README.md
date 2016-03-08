# REST api
#### Loans collection
```
GET /loans - all loans
GET /loans?private_id={:private id} - loans by users private id
GET /loans?status=true - approved loans
```

#### Particular loan
```
GET /loans/{:id} - particular loan
```
#### Insert new loan; update customers info if neccessary (identifiction by personalId)

```
POST /loans
Content-Type: application/json

{
 "loanAmount": 9,
 "term": 42 ,
 "name": "Kirk",
 "surname": "Ent",
 "personalId": "111"
}
```

#### Never Have I Ever [drinking game]
- dropwizard [Jersey + Jackson + Jetty]
- mongo [just a little bit, only for expirience]
- jongo
- guice
- REST
- jukito [mockito + guice + junit]
- writing tests seriously
