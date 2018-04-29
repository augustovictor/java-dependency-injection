package augustovictor.com.github.dependencyinjectiondemo.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("de")
public class GermanGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Oi em german";
    }
}
