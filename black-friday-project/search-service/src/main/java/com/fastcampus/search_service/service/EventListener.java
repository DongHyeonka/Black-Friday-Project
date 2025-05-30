package com.fastcampus.search_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

import blackfriday.protobuf.EdaMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SearchService searchService;

    @KafkaListener(topics = "product_tags_added")
    public void consumeTagAdded(byte[] message) throws InvalidProtocolBufferException {
        var object = EdaMessage.ProductTags.parseFrom(message);
        logger.info("[product_tags_added] consumed: {}", object);

        searchService.addTagCache(object.getProductId(), object.getTagsList());
    }

    @KafkaListener(topics = "product_tags_removed")
    public void consumeTagRemoved(byte[] message) throws InvalidProtocolBufferException {
        var object = EdaMessage.ProductTags.parseFrom(message);
        logger.info("[product_tags_removed] consumed: {}", object);

        searchService.removeTagCache(object.getProductId(), object.getTagsList());
    }
}
