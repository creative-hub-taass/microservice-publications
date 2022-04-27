package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.ConsumerService;
import com.creativehub.backend.services.EventsManager;
import com.creativehub.backend.services.PostsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
	private final ArtworksManager artworksManager;
	private final EventsManager eventsManager;
	private final PostsManager postsManager;

	// Arriva l'id dell'utente a cui verranno eliminate le proprie opere nei vari repository
	@Override
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void receivedMessage(UUID id) {
		try {
			artworksManager.deleteAllArtworksByCreator(id);
			eventsManager.deleteAllEventsByCreator(id);
			postsManager.deleteAllPostsByCreator(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
