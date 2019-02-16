package sanek.elastic.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

@Configuration
@EnableElasticsearchRepositories()
public class ElasticConfig {

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;
//
//    @Value("${elasticsearch.clustername}")
//    private String EsClusterName;

    @Bean
    public Client client1() throws Exception {

//        Settings esSettings = Settings.builder()
//                .put("cluster.name", EsClusterName)
//                .build();
        
        TransportClient Client = new PreBuiltTransportClient(Settings.EMPTY)
            .addTransportAddress(new TransportAddress(InetAddress.getByName(EsHost), EsPort))
            //.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))
            ;

        return Client;
    }

    @Bean
    public ElasticsearchOperations esTemplate() throws Exception {
        return new ElasticsearchTemplate(client1());
    }

    //Embedded Elasticsearch Server
    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }*/

}