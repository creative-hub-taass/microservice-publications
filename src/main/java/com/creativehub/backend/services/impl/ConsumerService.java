package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.PublicationsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {
	private final PublicationsManager publicationsManager;

	/**
	 * Arriva l'ID dell'utente di cui devono essere eliminate le pubblicazioni
	 */
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void receivedMessage(UUID id) {
		System.out.println("E' arrivato il messaggio " + id);
		publicationsManager.deleteAllPublicationsByCreator(id);
	}
}
