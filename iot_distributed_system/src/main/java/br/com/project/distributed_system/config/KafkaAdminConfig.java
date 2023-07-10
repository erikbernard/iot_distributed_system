package br.com.project.distributed_system.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {
    private String bootstrapServers="127.0.0.1:9092";
    private String topicStructuredData = "STRUCTURED-AND-ANALYZED-SENSOR-DATA";

    @Bean
    public KafkaAdmin kafkaAdmin(){
        var configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public KafkaAdmin.NewTopics newTopics(){
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(topicStructuredData).partitions(2).build()
        );
    }
}
