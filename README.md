# **Purchase Transaction API Documentation**

This is Spring Boot application with H2 Database. Just clone the code and let the Maven manager the dependencies and then start the application.
This API allows you to create, retrieve, update, delete, and convert purchase transaction values to different currencies based on exchange rates. Below is an overview of the available endpoints and how to use them. 

---

## **Table of Contents**

1. [Endpoints](#endpoints)
   - [Create Purchase Transaction](#create-purchase-transaction)
   - [Get Purchase by ID](#get-purchase-by-id)
   - [Get All Purchases](#get-all-purchases)
   - [Update Purchase Transaction](#update-purchase-transaction)
   - [Delete Purchase Transaction](#delete-purchase-transaction)
   - [Convert Purchase Transaction](#convert-purchase-transaction)
   
2. [Data Schemas](#data-schemas)
   - [Purchase Transaction](#purchase-transaction)
   - [Purchase Transaction Conversion](#purchase-transaction-conversion)
   - [Currency Exchange Rate](#currency-exchange-rate)

---

## **Endpoints**

### **1. Create Purchase Transaction**
**POST** `/api/purchases`

Creates a new purchase transaction. This requires the transaction to have the state `ACCEPT`.

#### Request:
- **Body**:
  ```json
  {
    "state": "ACCEPT",
    "description": "Laptop purchase",
    "transactionDate": "2024-11-05T15:30:00",
    "amountUSD": 1000.00
  }

#### Response:
- **Body**:
  ```json
  {
  "transactionId": "uuid",
  "state": "ACCEPT",
  "description": "Laptop purchase",
  "transactionDate": "2024-11-05T15:30:00",
  "amountUSD": 1000.00
  }

### **2. Get Purchase by ID**
**GET** `/api/purchases/{transactionId}`

Retrieve a specific purchase transaction by its `transactionId`.

#### Request:
- **Path Parameter**:
  - `transactionId` (UUID): The unique identifier of the purchase transaction.

#### Response:
- **200 OK**:
  ```json
  {
    "transactionId": "uuid",
    "state": "ACCEPT",
    "description": "Laptop purchase",
    "transactionDate": "2024-11-05T15:30:00",
    "amountUSD": 1000.00
  }

### **3. Get All Purchase Transactions**
**GET** `/api/purchases`

Retrieves a list of all purchase transactions.

#### Request:
- **No request body**.

#### Responses:
- **200 OK**: If the transactions are retrieved successfully.
  - **Body** (JSON Array):
    ```json
    [
      {
        "transactionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
        "state": "ACCEPT",
        "description": "Laptop purchase",
        "transactionDate": "2024-11-05T15:30:00",
        "amountUSD": 1000.00
      },
      {
        "transactionId": "c72d3e89-d670-45a5-b459-1b43e29d24f7",
        "state": "ACCEPT",
        "description": "Smartphone purchase",
        "transactionDate": "2024-11-06T10:15:00",
        "amountUSD": 500.00
      }
    ]
    ```

- **404 Not Found**: If no purchase transactions exist.
  - **Body**:
    ```json
    {
      "error": "No transactions found."
    }
    ```
### **4. Update Purchase Transaction**
**PUT** `/api/purchases/{transactionId}`

Updates an existing purchase transaction by its transaction ID.

#### Path Parameters:
- **transactionId** (UUID): The ID of the purchase transaction to update.

#### Request Body:
- **purchaseTransactionDetails** (JSON Object): The details to update for the purchase transaction.
  ```json
  {
    "state": "ACCEPT",
    "description": "Updated laptop purchase",
    "transactionDate": "2024-11-06T15:00:00",
    "amountUSD": 1200.00
  }

#### Response Body:
- **200 OK**: If the transaction is updated successfully..
  ```json
  {
  "transactionId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "state": "ACCEPT",
  "description": "Updated laptop purchase",
  "transactionDate": "2024-11-06T15:00:00",
  "amountUSD": 1200.00
  }

### **5. Delete Purchase Transaction**
**DELETE** `/api/purchases/{transactionId}`

Deletes a purchase transaction by its transaction ID.

#### Path Parameters:
- **transactionId** (UUID): The ID of the purchase transaction to delete.

#### Responses:
- **204 No Content**: If the transaction is deleted successfully.
  - **Body**: Empty response body.

- **404 Not Found**: If the specified transaction ID does not exist.
  - **Body**:
    ```json
    {
      "error": "Transaction not found."
    }
    ```
### **6. Convert Purchase Transaction to Target Currency**
**GET** `/api/transactions/{transactionId}/convert?targetCurrency=XXXXXXX`

Converts the purchase transaction amount from USD to a specified target currency using the latest exchange rate available within the last 6 months.

#### Path Parameters:
- **transactionId** (UUID): The ID of the purchase transaction to convert.
  
#### Query Parameters:
- **targetCurrency** (String): The currency code to which the transaction amount should be converted (e.g., "Canada-Dollar", "Brazil-Real").

#### Responses:
- **200 OK**: If the currency conversion is successful.
  - **Body**:
    ```json
    {
      "transactionId": "uuid",
      "description": "Transaction description",
      "transactionDate": "2024-11-06",
      "originalAmountUSD": 100.00,
      "exchangeRate": 1.252,
      "convertedAmount": 125.20
    }
    ```
  
- **400 Bad Request**: If no exchange rate is available within the last 6 months for the specified target currency.
  - **Body**:
    ```json
    {
      "error": "Currency conversion rate not available within the last 6 months for the specified currency."
    }
    ```

- **404 Not Found**: If the specified transaction ID does not exist.
  - **Body**:
    ```json
    {
      "error": "Transaction not found."
    }
    ```

### **Data Schemas**

#### **PurchaseTransaction**
Represents a purchase transaction in the system.

- **transactionId** (UUID): The unique identifier of the transaction.
- **description** (String): The description of the purchase.
- **transactionDate** (Date): The date when the transaction occurred.
- **amountUSD** (BigDecimal): The amount of the purchase in USD.
- **state** (TransactionState): The state of the transaction (ACCEPT or NOT_ACCEPT).

##### Example:
```json
{
  "transactionId": "uuid",
  "description": "Laptop Purchase",
  "transactionDate": "2024-11-06",
  "amountUSD": 1000.00,
  "state": "ACCEPT"
}
```

#### **PurchaseTransactionConversionDto**
Represents the response for a converted purchase transaction.

- **transactionId** (UUID): The unique identifier of the transaction.
- **description** (String): The description of the purchase.
- **transactionDate** (Date): The date when the transaction occurred.
- **originalAmountUSD** (BigDecimal): The original purchase amount in USD.
- **exchangeRate** (BigDecimal): The exchange rate used for conversion.
- **convertedAmount** (BigDecimal): The amount in the target currency after conversion.

##### Example:
```json
{
  "transactionId": "uuid",
  "description": "Laptop Purchase",
  "transactionDate": "2024-11-06",
  "originalAmountUSD": 1000.00,
  "exchangeRate": 1.252,
  "convertedAmount": 1252.00
}
 ```

#### **CurrencyExchangeRate**
Represents the exchange rate data returned from the Treasury API.

- **countryCurrencyDesc** (String): A description of the country and its currency (e.g., "Canada-Dollar", "Mexico-Peso").
- **exchangeRate** (BigDecimal): The exchange rate for the currency relative to USD.
- **recordDate** (Date): The date when the exchange rate was recorded.

##### Example:
```json
{
  "countryCurrencyDesc": "Canada-Dollar",
  "exchangeRate": 1.252,
  "recordDate": "2022-03-31"
}
```


  
