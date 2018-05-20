package net.learningpath.logger.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * See Kotlin version of this class
 * @see CassandraTestContextKT
 */
@Configuration
@PropertySources({
    @PropertySource("classpath:cassandra-sentences.properties"),
    @PropertySource("classpath:test-cassandra.properties")
})
@Import({CassandraConfig.class})
public class CassandraTestContext {

    @Value("${start.timeout}")
    private int timeout;

    @Value("${cluster.contact-point}")
    private String contactPoint;

    @Value("${cluster.port}")
    private int port;

    @Value("${log-messages.create-keyspace}")
    private String createKeyspaceSentence;

    @Value("${log-messages.activate-keyspace}")
    private String activateKeyspaceSentence;

    @PostConstruct
    public void setUpEmbeddedCassandra() throws InterruptedException, TTransportException, ConfigurationException, IOException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra(timeout);
        final Cluster cluster = Cluster.builder().addContactPoints(contactPoint).withPort(port).build();
        final Session session = cluster.connect();
        session.execute(createKeyspaceSentence);
        session.execute(activateKeyspaceSentence);
    }

    @PreDestroy
    public void stopEmbeddedCassandra() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

}
