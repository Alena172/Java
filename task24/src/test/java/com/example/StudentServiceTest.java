package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class StudentServiceTest {

    @Test
    void getAllStudentsTest() {
        // Создаем экземпляр сервиса или мок сервиса
        StudentService studentService = mock(StudentService.class);

        // Вызываем метод, который мы хотим протестировать
        List<Map<String, Object>> students = studentService.getAllStudents();

        // Проверяем ожидаемый результат
        // Здесь можно добавить дополнительные проверки, например, проверку на размер списка
    }

    @Test
    void filterStudentsByCriteriaTest() {
        // Создаем экземпляр сервиса или мок сервиса
        StudentService studentService = mock(StudentService.class);

        // Создаем параметры для фильтрации
        Map<String, String> params = Map.of("key1", "value1", "key2", "value2");

        // Вызываем метод, который мы хотим протестировать
        List<Map<String, Object>> filteredStudents = studentService.filterStudentsByCriteria(params);

        // Проверяем ожидаемый результат
        // Здесь можно добавить дополнительные проверки, например, проверку на размер списка
    }

    @Test
    void deleteStudentTest() {
        // Создаем экземпляр сервиса или мок сервиса
        StudentService studentService = mock(StudentService.class);

        // Вызываем метод, который мы хотим протестировать
        studentService.deleteStudent(1L);

    }
}
