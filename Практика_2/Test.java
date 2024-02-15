package Практика_2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args){
        //Создаем список из объектов класса Human
        List<Human> input = new ArrayList<>();
        input.add(new Human(25, "John", "Doe", LocalDate.of(1996, 5, 15), 70));
        input.add(new Human(21, "Kurt", "Smith", LocalDate.of(2003, 9, 28), 65));
        input.add(new Human(40, "Bob", "Johnson", LocalDate.of(1981, 3, 10), 80));
        input.add(new Human(23, "Emily", "Brown", LocalDate.of(2001, 12, 5), 55));;
        input.add(new Human(55, "Tom", "Williams", LocalDate.of(1969, 7, 20), 90));
        //Создаем поток для фильтрации его по дате
        Stream<Human> filt = input.stream();
        LocalDate date1 = LocalDate.of(2000, 6, 24);
        System.out.println("Фильтр:");
        //Используем операцию фильтр
        filt.filter(human -> (human.birthDate.isAfter(date1)))
                .forEach(System.out::println);
        System.out.println("\nСортировка по имени:");
        Stream<Human> sortn = input.stream();
        //Используем операцию сортировки потока
        sortn.sorted(Comparator.comparing(Human::getFirstName))
                .forEach(System.out::println);
        System.out.println("\nСортировка по фамилии:");
        Stream<Human> sortl = input.stream();
        //Используем операцию сортировки потока
        sortl.sorted(Comparator.comparing(Human::getLastName))
                .forEach(System.out::println);
        Stream<Human> sumage = input.stream();
        //Используем операцию суммирования
        int suma = sumage.map(Human::getAge)
                .reduce(0, (a, b) -> a + b);
        System.out.println("\nСумма возрастов: " + suma);
    }
}
