package net.learningpath.logger.config

import net.learningpath.logger.repository.cassandra.LoggerRepositoryKT
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext
import org.springframework.data.cassandra.mapping.CassandraMappingContext
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

/**
 * See Java version of this class
 * @see CassandraConfig
 */
@Configuration
@PropertySource("classpath:cassandra.properties")
@EnableCassandraRepositories(basePackageClasses = [LoggerRepositoryKT::class])
open class CassandraConfigKT : AbstractCassandraConfiguration() {

    @Value("\${keyspace-name}")
    private val keyspaceName = ""

    @Value("\${cluster.contact-point}")
    private val contactPoint = ""

    @Value("\${cluster.port}")
    private val dbPort = 1

    override fun getKeyspaceName(): String {
        return keyspaceName
    }

    @Bean
    override fun cluster(): CassandraClusterFactoryBean {
        val cluster = CassandraClusterFactoryBean()
        cluster.setContactPoints(contactPoint)
        cluster.setPort(dbPort)
        return cluster
    }

    @Bean
    override fun cassandraMapping(): CassandraMappingContext {
        return BasicCassandraMappingContext()
    }

}