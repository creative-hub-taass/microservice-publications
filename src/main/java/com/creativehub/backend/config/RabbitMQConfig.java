package com.creativehub.backend.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	@Value("${spring.rabbitmq.queue}")
	private String queue;
	@Value("${fanout.name}")
	private String fanoutName;

  @Value("${exchange.name}")
  private String directName;

  @Value("${spring.rabbitmq.queueDirect}")
  private String queueDirect;

  @Value("${routing.key}")
  private String routingKey;

	@Value("${spring.rabbitmq.username}")
	private String username;
	@Value("${spring.rabbitmq.password}")
	private String password;
	@Value("${spring.rabbitmq.host}")
	private String host;

	@Bean
	Queue queue() {
		return new Queue(queue, true);
	}

  @Bean
  Queue queueDirect() {return new Queue(queueDirect, true);}

  @Bean
	FanoutExchange myExchange() {
	return new FanoutExchange(fanoutName);}

	@Bean
	Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(myExchange());
	}

  @Bean
  DirectExchange myExchangeDirect() {
    return new DirectExchange(directName);
  }

  @Bean
  Binding bindingDirect() {
    return BindingBuilder
      .bind(queueDirect())
      .to(myExchangeDirect())
      .with(routingKey);
  }

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);
		return cachingConnectionFactory;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
