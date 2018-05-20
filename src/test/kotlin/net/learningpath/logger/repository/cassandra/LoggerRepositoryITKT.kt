package net.learningpath.logger.repository.cassandra

import net.learningpath.logger.config.CassandraTestContextKT
import net.learningpath.logger.model.entity.LogMessagesKT
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cassandra.core.cql.CqlIdentifier
import org.springframework.data.cassandra.core.CassandraAdminOperations
import org.springframework.data.cassandra.repository.support.BasicMapId
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.HashMap

/**
 * See Java version of this class
 * @see LoggerRepositoryIT
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [CassandraTestContextKT::class])
class LoggerRepositoryITKT {

    @Value("\${log-messages.table-name}")
    private val logMessageTableName: String? = null

    @Autowired
    private val adminTemplate: CassandraAdminOperations? = null

    @Autowired
    private val loggerRepository: LoggerRepositoryKT? = null

    @Before
    fun createTable() {
        adminTemplate!!.createTable(true, CqlIdentifier.cqlId(logMessageTableName!!), LogMessagesKT::class.java, HashMap())
    }

    @Test
    fun whenSavingLogMessage_thenAvailableOnRetrieval() {
        val log = LogMessagesKT(2018, 3, 16, 16, 13, 34, 234, "any sender", "info", "log stored successfully!!!")
        val savedLog = loggerRepository!!.save(log)

        val mapId = BasicMapId
                .id("year", savedLog.year)
                .with("month", savedLog.month)
                .with("day", savedLog.day)
                .with("hour", savedLog.hour)
                .with("minutes", savedLog.minutes)
                .with("seconds", savedLog.seconds)
                .with("milliseconds", savedLog.milliseconds)
                .with("sender", savedLog.sender)
                .with("messageType", savedLog.messageType)

        val retrievedLog = loggerRepository.findOne(mapId)

        assertThat(retrievedLog, equalTo(savedLog))

    }

    @After
    fun dropTable() {
        adminTemplate!!.dropTable(CqlIdentifier.cqlId(logMessageTableName!!))
    }

}