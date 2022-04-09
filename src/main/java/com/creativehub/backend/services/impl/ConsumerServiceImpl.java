package com.creativehub.backend.services.impl;

import com.creativehub.backend.models.Creation;
import com.creativehub.backend.repositories.*;
import com.creativehub.backend.services.ConsumerService;
import com.creativehub.backend.services.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ArtworkRepository artworkRepository;
    private final EventRepository eventRepository;
    private final PostRepository postRepository;
    private final ArtworkCreationRepository artworkCreationRepository;
    private final EventCreationRepository eventCreationRepository;
    private final PostCreationRepository postCreationRepository;


    //arriva l'id dell'utente a cui verranno eliminate le proprie opere nei
    //vari repository
    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(UUID id) {
        //TODO
    }
}
