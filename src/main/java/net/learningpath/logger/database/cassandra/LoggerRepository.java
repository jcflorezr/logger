package net.learningpath.logger.database.cassandra;

import net.learningpath.logger.entity.LogMessages;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends CassandraRepository<LogMessages> {

}
