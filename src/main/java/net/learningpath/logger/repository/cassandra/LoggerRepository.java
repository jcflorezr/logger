package net.learningpath.logger.repository.cassandra;

import net.learningpath.logger.model.entity.LogMessages;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends CassandraRepository<LogMessages> {

}
