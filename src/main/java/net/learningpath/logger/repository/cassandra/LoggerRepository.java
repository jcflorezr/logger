package net.learningpath.logger.repository.cassandra;

import net.learningpath.logger.model.entity.LogMessages;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * See Kotlin version of this class
 * @see LoggerRepositoryKT
 */
@Repository
public interface LoggerRepository extends CassandraRepository<LogMessages> {

}
