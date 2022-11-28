# Payments

Payment System is POC of a distributed peer to peer system that enables Customers to pay other customers for various services or products they use.




# Prerequisites

- Java 8
- Confluet Platform for Kafka
> Confluet Platform can be downladed from [here](https://docs.confluent.io/5.5.0/quickstart/ce-quickstart.html)


# Getting Started
- Clone Project
```
git clone https://github.com/mshaib/payments.git
```
- Start Confluent Platform (as explained in upove link)
- Run application using maven Or from Intellij

# DB

The project uses H2 DB that initiates with project and gets deleted once the project is down.
Tables and Data gets initialized using conf/data.sql file

# Create Payments
- Using Postman or any other application send a request to
  ```POST 'http://localhost:<port>/v1/payments' ```


- Example request
```
curl --location --request POST 'http://localhost:8080/v1/payments' \
--header 'Content-Type: application/json' \
--data-raw '{
    "payeeId":"2aac9090-4466-4e88-b64b-57a395c34db4",
    "amount": 65,
    "currency": "USD",
    "paymentMethodId": "76be15ed-896b-4c7c-9c02-3112b486c2cc",
    "userId" : "ffa71ba4-f7cb-442f-bb27-34edea2deb6c"
}'
```


# 
