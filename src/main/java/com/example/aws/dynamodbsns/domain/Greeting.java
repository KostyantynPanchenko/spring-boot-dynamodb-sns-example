package com.example.aws.dynamodbsns.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Greeting {

  private String id;
  private String body;
  private String name;
  private String title;

}
