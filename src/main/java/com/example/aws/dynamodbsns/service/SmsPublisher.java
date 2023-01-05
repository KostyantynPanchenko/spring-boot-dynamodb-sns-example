package com.example.aws.dynamodbsns.service;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
@Slf4j
public class SmsPublisher implements Consumer<String> {

  private final SnsClient snsClient;
  private final String phoneNumber;

  SmsPublisher(final SnsClient snsClient, @Value("${PHONE_NUMBER}") final String phoneNumber) {
    this.snsClient = snsClient;
    this.phoneNumber = phoneNumber;
  }

  @Override
  public void accept(final String id) {
    String message = "A new item with ID value " + id + " was added to the DynamoDB table";

    try {
      final var request = PublishRequest.builder()
          .message(message)
          .phoneNumber(phoneNumber)
          .build();

      log.info("Sending message with id {} sent.", id);
      snsClient.publish(request);
      log.info("Message with id {} sent.", id);
    } catch (final SnsException snsExc) {
      log.error(snsExc.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }
}
