package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.EventsManager;
import com.creativehub.backend.services.PostsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {
	private final ArtworksManager artworksManager;
	private final EventsManager eventsManager;
	private final PostsManager postsManager;

	/**
	 * Arriva l'ID dell'utente di cui devono essere eliminate le pubblicazioni
	 */
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void receivedMessage(UUID id) {
		log.debug("DELETE ALL BY: " + id.toString());
		try {
			artworksManager.deleteAllArtworksByCreator(id);
			eventsManager.deleteAllEventsByCreator(id);
			postsManager.deleteAllPostsByCreator(id);
		} catch (Exception e) {
			log.error("ERROR", e);
		}
	}
}
