package com.example.aws.dynamodbsns.service;

import com.example.aws.dynamodbsns.domain.Greeting;
import com.example.aws.dynamodbsns.domain.GreetingItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Service
@Slf4j
@RequiredArgsConstructor
public class DynamoDbEnhancedService {

  private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

  public void injectDynamoItem(final Greeting item) {
    try {
      DynamoDbTable<GreetingItem> mappedTable = dynamoDbEnhancedClient.table("Greeting",
          TableSchema.fromBean(GreetingItem.class));
      final var greetingItem = new GreetingItem();
      greetingItem.setName(item.getName());
      greetingItem.setMessage(item.getBody());
      greetingItem.setTitle(item.getTitle());
      greetingItem.setId(item.getId());

      final var enReq = PutItemEnhancedRequest.builder(GreetingItem.class)
          .item(greetingItem)
          .build();

      log.info("Saving record {}.", item);
      mappedTable.putItem(enReq);
      log.info("Record {} saved.", item);
    } catch (final DynamoDbException ddbExc) {
      log.error(ddbExc.getMessage());
      System.exit(1);
    }
  }
}
