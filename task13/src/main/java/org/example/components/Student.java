package org.example.components;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@Log4j2
public class Student {
    // Инъекция значений из файла конфигурации в поля класса
    @Value("${student.name}")
    String name;
    @Value("${student.last_name}")
    String last_name;
    @Value("${student.group}")
    String group;

    // Метод, выполняющийся после инициализации бина
    @PostConstruct
    private void init() {
        // Вывод информации о студенте с использованием логгера
        log.info(this);
    }
}

