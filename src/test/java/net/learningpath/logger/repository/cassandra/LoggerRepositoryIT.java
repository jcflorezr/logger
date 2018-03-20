package net.learningpath.logger.repository.cassandra;

import net.learningpath.logger.config.CassandraTestContext;
import net.learningpath.logger.model.entity.LogMessages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CassandraTestContext.class})
public class LoggerRepositoryIT {

    @Value("${log-messages.table-name}")
    private String logMessageTableName;

    @Autowired
    private CassandraAdminOperations adminTemplate;

    @Autowired
    private LoggerRepository loggerRepository;

    @Before
    public void createTable() {
        adminTemplate.createTable(true, CqlIdentifier.cqlId(logMessageTableName), LogMessages.class, new HashMap<>());
    }

    @Test
    public void whenSavingLogMessage_thenAvailableOnRetrieval() {
        LogMessages log = new LogMessages(2018, 03, 16, 16, 13, 34, 234, "any sender", "info", "log stored successfully!!!");
        LogMessages savedLog = loggerRepository.save(log);

        MapId mapId = BasicMapId
                .id("year", savedLog.getYear())
                .with("month", savedLog.getMonth())
                .with("day", savedLog.getDay())
                .with("hour", savedLog.getHour())
                .with("minutes", savedLog.getMinutes())
                .with("seconds", savedLog.getSeconds())
                .with("milliseconds", savedLog.getMilliseconds())
                .with("sender", savedLog.getSender())
                .with("messageType", savedLog.getMessageType());

        LogMessages retrievedLog = loggerRepository.findOne(mapId);
        String savedLogMessage = savedLog.getMessage();
        String retrievedLogMessage = retrievedLog.getMessage();

        assertThat(savedLogMessage, is(equalTo(retrievedLogMessage)));

    }

    @After
    public void dropTable() {
        adminTemplate.dropTable(CqlIdentifier.cqlId(logMessageTableName));
    }

}