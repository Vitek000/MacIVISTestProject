package springtrenings.springinaction.knight;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vitek000 on 23.08.2016.
 */
@Configuration
public class KnightConfig {
    @Bean
    public Knight knight() {
        return new BraveKnight(quest());
    }

    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }
}

