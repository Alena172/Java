package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KnightApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        String knightName = "strongKnight";
        Knight knight = (Knight) context.getBean(knightName);
        knight.fight();
        knightName = "weakKnight";
        knight = (Knight) context.getBean(knightName);
        knight.fight();
        knightName = "kingOfKnights";
        knight = (Knight) context.getBean(knightName);
        knight.fight();
    }
}
