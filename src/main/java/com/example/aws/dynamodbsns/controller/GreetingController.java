package com.example.aws.dynamodbsns.controller;

import com.example.aws.dynamodbsns.domain.Greeting;
import com.example.aws.dynamodbsns.service.DynamoDbEnhancedService;
import com.example.aws.dynamodbsns.service.SmsPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
class GreetingController {

  private final DynamoDbEnhancedService dde;
  private final SmsPublisher smsPublisher;

  @GetMapping("/")
  public String greetingForm(final Model model) {
    model.addAttribute("greeting", new Greeting());
    return "greeting";
  }

  @PostMapping("/greeting")
  public String greetingSubmit(@ModelAttribute final Greeting greeting) {
    dde.injectDynamoItem(greeting);
    smsPublisher.accept(greeting.getId());
    return "result";
  }
}
