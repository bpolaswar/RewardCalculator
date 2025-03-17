### **Problem statement:**

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.  


#### **Solution:**

This application calculate reward points for customer

Created GET REST endpoint,

`/reward/calculate-reward-point/{custId}`

Where custId is customerId for which we need to calculate rewards points.
this endpoint will take customerId as pathVariable and determine current month, then
fetch all transactions of last three month(including current month) for this customer, 
and then calculate reward points as per below formula:

#### **Implementation:**

Used H2 in-memory database for now, 
created dummy data with multiple transactions with multiple customers, Whenever application starts sql record with insert query is executed and dataset being ready
and call endpoint with customer id and get response

#### **Curl:**

`curl --location 'http://localhost:8080/reward/calculate-reward-point/CUST102'`

#### Sample Response

```json
{
  "CUST102": {
    "monthlyPoint": {
      "March": 1350,
      "February": 1201,
      "January": 5101
    },
    "totalPoint": 7652
  }
}
```

#### **E.g.**
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction. 

a $120 purchase = 2x$20 + 1x$50 = 90 points

