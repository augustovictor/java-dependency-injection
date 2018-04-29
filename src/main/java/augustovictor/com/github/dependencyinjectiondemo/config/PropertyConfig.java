package augustovictor.com.github.dependencyinjectiondemo.config;

import augustovictor.com.github.dependencyinjectiondemo.datasources.FakeDataSource;
import augustovictor.com.github.dependencyinjectiondemo.datasources.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${augustovictor.DB}")
    String db;

    @Value("${augustovictor.PASSWORD}")
    String password;

    @Value("${augustovictor.USERNAME}")
    String username;

    @Value("${augustovictor.HOST}")
    String host;

    @Value("${augustovictor.jms.username}")
    String jmsUsername;

    @Value("${augustovictor.jms.password}")
    String jmsPassword;

    @Value("${augustovictor.jms.url}")
    String jmsUrl;

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker jmsBroker = new FakeJmsBroker();

        jmsBroker.setUsername(jmsUsername);
        jmsBroker.setPassword(jmsPassword);
        jmsBroker.setUrl(jmsUrl);

        return jmsBroker;
    }

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource dataSource = new FakeDataSource();
        dataSource.setDb(db);
        dataSource.setHost(host);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
