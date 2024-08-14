package com.example.borrowdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookReturnProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.name}")
    private String queueName;

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(queueName, message);
    }
}
