package augustovictor.com.github.dependencyinjectiondemo;

import augustovictor.com.github.dependencyinjectiondemo.controllers.MyController;
import augustovictor.com.github.dependencyinjectiondemo.datasources.FakeDataSource;
import augustovictor.com.github.dependencyinjectiondemo.datasources.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionDemoApplication {

    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(DependencyInjectionDemoApplication.class, args);
        FakeDataSource dataSource = act.getBean(FakeDataSource.class);
        System.out.println(dataSource.getUsername());

        FakeJmsBroker jmsBroker = act.getBean(FakeJmsBroker.class);
        System.out.println(jmsBroker.getUsername());

        // Ask the content for the bean
        // We have to cast it since it is not strongly typed
        MyController controller = (MyController) act.getBean("myController");
        System.out.println(controller.hello());
    }
}
