package net.learningpath.logger.config;

import net.learningpath.logger.database.cassandra.LoggerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource("classpath:cassandra.properties")
@EnableCassandraRepositories(basePackageClasses = {LoggerRepository.class})
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${keyspace-name}")
    private String keyspaceName;

    @Value("${cluster.contact-point}")
    private String contactPoint;

    @Value("${cluster.port}")
    private int port;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoint);
        cluster.setPort(port);
        return cluster;
    }

    @Bean
    @Override
    public CassandraMappingContext cassandraMapping() {
        return new BasicCassandraMappingContext();
    }

}
