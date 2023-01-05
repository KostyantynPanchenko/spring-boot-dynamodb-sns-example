package com.example.aws.dynamodbsns.domain;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Getter
@Setter
public class GreetingItem {

  private String id;
  private String message;
  private String name;
  private String title;

  @DynamoDbPartitionKey
  public void setId(final String id) {
    this.id = id;
  }

}
