package com.demo.testcontainers.integration.base

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import javax.transaction.Transactional

@ContextConfiguration(initializers = [BaseITInitializer::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegrationTest {

    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null

    @Transactional
    protected fun cleanDB() {
        val tablesToTruncate = listOf("book").joinToString()
        val sql = """
            TRUNCATE TABLE $tablesToTruncate CASCADE
        """.trimIndent()
        jdbcTemplate?.execute(sql)
    }
}
