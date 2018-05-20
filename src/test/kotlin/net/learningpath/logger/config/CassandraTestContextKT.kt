package net.learningpath.logger.config

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * See Java version of this class
 * @see CassandraTestContext
 */
@Configuration
@PropertySources(
    PropertySource("classpath:cassandra-sentences.properties"),
    PropertySource("classpath:test-cassandra.properties")
)
@Import(CassandraConfigKT::class)
open class CassandraTestContextKT {

    @Value("\${start.timeout}")
    private var timeout = 0L

    @Value("\${cluster.contact-point}")
    private var contactPoint = ""

    @Value("\${cluster.port}")
    private var dbPort = 0

    @Value("\${log-messages.create-keyspace}")
    private var createKeyspaceSentence = ""

    @Value("\${log-messages.activate-keyspace}")
    private var activateKeyspaceSentence = ""

    @PostConstruct
    fun setUpEmbeddedCassandra() {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra(timeout)
        val cluster: Cluster = Cluster.builder().addContactPoints(contactPoint).withPort(dbPort).build()
        val session: Session = cluster.connect()
        session.execute(createKeyspaceSentence)
        session.execute(activateKeyspaceSentence)
    }

    @PreDestroy
    fun stopEmbeddedCassandra() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra()
    }

}