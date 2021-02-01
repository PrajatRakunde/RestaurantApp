//package com.eatza.restaurantsearch.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import com.eatza.restaurantsearch.dto.ReviewDto;
//
//@EnableKafka
//@Configuration
//public class ReviewKafkaConsumer {
//
//	@Value(value = "${kafka.bootstrapAdress}")
//	private String address;
//
//	@Bean
//	public ConsumerFactory<String, ReviewDto> consumerFactory() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, "reviews");
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//		// props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		JsonDeserializer<ReviewDto> deserialiser = new JsonDeserializer<>(ReviewDto.class);
//		deserialiser.addTrustedPackages("*");
//		deserialiser.setRemoveTypeHeaders(false);
//		deserialiser.setUseTypeMapperForKey(true);
//		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserialiser);
//	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, ReviewDto> kafkaListenerContainerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, ReviewDto> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
//		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
//		return kafkaListenerContainerFactory;
//	}
//
//}
