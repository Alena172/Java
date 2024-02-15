package Практика_1;

import java.util.Arrays;
import java.util.function.Function;

public class MinValue implements Function<int[], String> {
    public String apply(int[] numbers) {
        //Сортируем массив
        Arrays.sort(numbers);
        StringBuilder result = new StringBuilder();
        //Создаем массив для выявления дубликатов
        boolean[] used = new boolean[10];
        //Проходим по массиву
        for (int number : numbers) {
            //Если цифра ранее не встречалась
            if (!used[number]) {
                //Добавляем ее в строку
                result.append(number);
                //Устанавливаем флаг
                used[number] = true;
            }
        }
        //Возвращаем полученную запись
        return result.toString();
    }
    public static void main(String[] args) {
        int[] input = {1, 5, 3, 1, 2, 4, 2};
        MinValue minValueFunction = new MinValue();
        System.out.println(minValueFunction.apply(input));
    }
}
