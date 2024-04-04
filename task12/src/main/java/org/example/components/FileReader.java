package org.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class FileReader {
    // Подставляем пути к файлам ввода и хеша из application.properties
    @Value("${file.reader.inputFilePath}")
    private String inputFilePath;

    @Value("${file.reader.hashFilePath}")
    private String hashFilePath;
    // Логгер для вывода сообщений
    Logger logger = LoggerFactory.getLogger(FileReader.class);
    // Метод, выполняющийся после инициализации бина
    @PostConstruct
    private void saveHash() {
        try {
            // Создание экземпляра MessageDigest с алгоритмом SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Создание FileWriter для записи хеша в файл hashFilePath
            FileWriter fileWriter = new FileWriter(hashFilePath, false);

            try {
                // Обновление MessageDigest содержимым файла ввода
                md.update(Files.readAllBytes(Path.of(inputFilePath)));
                // Запись хеша в файл hashFilePath
                fileWriter.write(Converter.fromByteArrayToHex(md.digest()));
                logger.info("Hash has been written to " + hashFilePath);
            } catch (NoSuchFileException e) {
                // Обработка ситуации, когда файл ввода не найден
                fileWriter.write("null\n");
                // Логирование сообщения о том, что файл ввода не найден
                logger.info("File " + inputFilePath + " doesn't exist");
            } finally {
                // Сброс и закрытие FileWriter
                fileWriter.flush();
                fileWriter.close();
            }

        } catch (NoSuchAlgorithmException | IOException e) {
            // Логирование сообщения об ошибке при NoSuchAlgorithmException или IOException
            logger.error(e.getMessage());
        }
    }

    // Метод, выполняющийся перед уничтожением бина
    @PreDestroy
    private void deleteInputFile() {
        // Проверка наличия файла ввода
        if (Files.exists(Path.of(inputFilePath))) {
            try {
                // Удаление файла ввода
                Files.delete(Path.of(inputFilePath));
                // Логирование успешного удаления файла
                logger.info("File " + inputFilePath + " deleted");
            } catch (IOException e) {
                // Логирование сообщения об ошибке при удалении файла
                logger.error(e.getMessage());
            }
        }
    }
}