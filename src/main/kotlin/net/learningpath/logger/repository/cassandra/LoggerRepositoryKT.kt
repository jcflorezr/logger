package net.learningpath.logger.repository.cassandra

import net.learningpath.logger.model.entity.LogMessagesKT
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

/**
 * See Java version of this class
 * @see LoggerRepository
 */
@Repository
interface LoggerRepositoryKT : CassandraRepository<LogMessagesKT>