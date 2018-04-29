package augustovictor.com.github.dependencyinjectiondemo.config;

import augustovictor.com.github.dependencyinjectiondemo.datasources.FakeDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:datasource.properties")
public class PropertyConfig {

    @Autowired
    Environment env;

    @Value("${augustovictor.DB}")
    String db;

    @Value("${augustovictor.PASSWORD}")
    String password;

    @Value("${augustovictor.USERNAME}")
    String username;

    @Value("${augustovictor.HOST}")
    String host;

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource dataSource = new FakeDataSource();
        dataSource.setDb(db);
        dataSource.setHost(host);
        dataSource.setUsername(username);
        // dataSource.setUsername(env.getProperty("CUSTOM_VARIABLE"));
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public static PropertyConfig properties() {
        return new PropertyConfig();
    }
}
