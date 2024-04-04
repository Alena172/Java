package org.example.components;

public class Converter {
    public static String fromByteArrayToHex(byte[] bytes) {
        // Создание StringBuilder для построения шестнадцатеричной строки
        StringBuilder sb = new StringBuilder();
        // Итерация по каждому байту в массиве
        for (byte b : bytes) {
            // Преобразование байта в шестнадцатеричное представление
            String hex = Integer.toHexString(b & 0xFF);
            // Если представление состоит из одного символа, добавляем в начало '0'
            if (hex.length() == 1) {
                sb.append('0');
            }
            // Добавление шестнадцатеричного представления байта к StringBuilder
            sb.append(hex);
        }
        return sb.toString();
    }
}

