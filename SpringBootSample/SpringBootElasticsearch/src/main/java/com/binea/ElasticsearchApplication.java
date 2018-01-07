package com.binea;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class ElasticsearchApplication {

	public static void main(String[] args) {
		Settings settings = Settings.settingsBuilder()
				.put("cluster.name", "elasticsearch_binea").build();

		Client client = TransportClient.builder().settings(settings).build()
				.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
		SpringApplication.run(ElasticsearchApplication.class, args);
	}
}
