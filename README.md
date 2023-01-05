# Read Me First

This sample app does two things:
* Saves the SMS message to the DynamoDB table
* Sends SMS message on the registered phone number

You will need:
* An AWS account
* Java 17 SDK

## How To Run

* Set up environment variables `PHONE_NUMBER`, `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`
* Build the app with `./gradlew build`
* Create DynamoDB table `Greeting` with partition key `id`
* Create AWS Elastic Beanstalk application
* Edit AWS Elastic Beanstalk environment, `PHONE_NUMBER`, `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY` to valid values. Set `SERVER_PORT` environment variable to 5000
* Validate the application is up and running, send SMS via your app's web interface (use AWS Elastic Beanstalk generated URL)

## Useful Link
[Example of web application with AWS DynamoDB and AWS SNS running on AWS Beanstalk](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/usecases/creating_first_project)
