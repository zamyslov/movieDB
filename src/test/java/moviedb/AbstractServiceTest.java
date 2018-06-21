package moviedb;

import moviedb.config.JpaConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:data.sql",
        config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration(classes = JpaConfig.class)
@WebAppConfiguration
abstract public class AbstractServiceTest {

    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
//    public <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
//        try {
//            runnable.run();
//            Assert.fail("Expected " + exceptionClass.getName());
//        } catch (Exception e) {
//            Assert.assertThat(getRootCause(e), instanceOf(exceptionClass));
//        }
//    }
}