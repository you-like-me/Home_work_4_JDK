// Создать класс справочник сотрудников, который содержит внутри
// коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
// Табельный номер
// Номер телефона
// Имя
// Стаж
// Добавить метод, который ищет сотрудника по стажу (может быть список)
// Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
// Добавить метод, который ищет сотрудника по табельному номеру
// Добавить метод добавление нового сотрудника в справочник сотрудников

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        var persons = new ArrayList<Person>(
                List.of(
                        new Person(0, "46456456", "Алекс", 10),
                        new Person(1, "9575789", "Алекс", 12),
                        new Person(2, "575756", "Василий", 8),
                        new Person(3, "475675647", "Михаил", 9),
                        new Person(4, "56457567657", "Анжелика", 11),
                        new Person(5, "45757567", "Александра", 15)));

        System.out.println("Сотрудник со стажем 8:" + findByExperience(persons, 8));
        Person pp = findByExperience(persons, 20);
        if (pp == null) {
            System.out.println("нет такого");

        } else {
            System.out.println("Сотрудник со стажем 20:" + pp);
        }

        add(persons, "1456456", "Вася", 5);
        add(persons, "45756", "Маша", 10);
        add(persons, "56767657", "Коля", 20);

        persons.forEach(System.out::println);

        var phonesByName = findPhonesByName(persons, "Алеj");
        System.out.println("Сотрудники по имени Алекс : " +phonesByName);

        var idsByName = findSomething(persons, p -> p.getName() == "Анжелика", p -> p.getPersonId());
        System.out.println("ID'шники Анжелики: " + idsByName);
    }

    static Person findByExperience(List<Person> list, int experience) {
        assert list != null : "LIST";
        var opt = list.stream().filter(p -> p.getExperience() == experience).findAny();
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    static List<String> findPhonesByName(List<Person> list, String name) {
        return list.stream().filter(p -> p.getName().equalsIgnoreCase(name))
                .map(p -> p.getPhone()).toList();
    }

    static <R> List<R> findSomething(List<Person> list, Predicate<Person> filter, Function<Person, R> mapper) {
        return list.stream().filter(filter).map(mapper).toList();
    }

    static void add(ArrayList<Person> list, String phone, String name, int experience) {
        int lastId = list.stream().mapToInt(Person::getPersonId).max().getAsInt();
        list.add(new Person(lastId + 1, phone, name, experience));
    }
}

class Person {
    int personId;
    String phone;
    String name;
    int experience;

    public Person(int personId, String phone, String name, int experience) {
        this.personId = personId;
        this.phone = phone;
        this.name = name;
        this.experience = experience;
    }

    public int getPersonId() {
        return personId;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return String.format("Табельный номер: %d, Имя: %s, Телефон: %s, Стаж: %d", personId, name, phone, experience);
    }

}