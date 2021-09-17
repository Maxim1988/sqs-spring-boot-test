package com.example.sqsspringboottest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SQSController {

        private static final Logger LOG = LoggerFactory.getLogger(SQSController.class);

        @Autowired
        private QueueMessagingTemplate queueMessagingTemplate;

        @Value("${cloud.aws.end-point.uri}")
        private String sqsEndPoint;

        @GetMapping("/send/{message}")
        public void sendMessage(@PathVariable String message) {
            queueMessagingTemplate.send(sqsEndPoint,
                    MessageBuilder.withPayload(message).build());
        }

        @SqsListener(value = "test-queue", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
        public void loggingSQSMessage(String message) {
                LOG.info("Message from SQS: {}", message);
        }
}
