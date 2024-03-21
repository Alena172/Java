package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "strongKnight")
    public Knight createStrongKnight() {
        return new StrongKnight();
    }

    @Bean(name = "weakKnight")
    public Knight createWeakKnight() {
        return new WeakKnight();
    }

    @Bean(name = "kingOfKnights")
    public Knight createKingOfKnights() {
        return new KingOfKnights();
    }
}
