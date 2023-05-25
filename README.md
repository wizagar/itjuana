# README

This project is the solution for the 3 exercises given by iTijuana
1. Anagram solution
2. Balanced parentheses
3. Hash function (lambda version)

# Import this project using IntelliJ:

- Launch IntelliJ IDEA and select "File" from the top menu.
- Click on "Open" or "Open Project".
- Navigate to the directory where you have downloaded the itijuana zip file.



# 3. Hash function (lambda version)

Please refer to lambda_hash_diagram.png for a graphic explanation.

LambdaHash.java is a serverless AWS Lambda function that generates a SHA-256 hash for a given string if the string meets certain criteria. The function is designed to be deployed and invoked through AWS API Gateway as a RESTful endpoint.

To use the LambdaHash class, send an HTTP POST request to the API Gateway endpoint with the input string in the request body. The Lambda function will generate a SHA-256 hash for the input string and return the result in the response.

Example Request:

https://ygu2jt25lh.execute-api.us-east-2.amazonaws.com/default/hash?input=MySecretPassword123!
