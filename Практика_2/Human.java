package Практика_2;
import java.time.LocalDate;

public class Human {
    private int age;
    private String firstName;
    private String lastName;
    LocalDate birthDate;
    private int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return  age + " " + firstName + " " + lastName + " " + birthDate + " " + weight;
    }
}
