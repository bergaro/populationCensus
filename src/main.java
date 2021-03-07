import java.util.*;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {
        List<Person> london = new ArrayList<>(getListOfPeople());
        System.out.println("Общее число населения Лондона = " + london.size() + " чел.");
        System.out.println("Из них:");
        System.out.println("Кол-во несовершеннолетних людей = " + countingMinors(london) + " чел.");
        System.out.println("Кол-во призывников = " + surnamesOfConscripts(london).size() + " чел.");
        System.out.println("Кол-во едениц рабочего населения = " + workingPopulation(london).size() + " чел.");
    }
    /**
     * Возвращает отсортированный по фамилии список потенциально работоспособных людей
     * с высшим образованием в выборке
     * (т.е. людей с высшим образованием от 18 до 60 лет для женщин
     * и до 65 лет для мужчин).
     * @param town Collection
     * @return List
     */
    public static List<Person> workingPopulation(Collection<Person> town) {
        return town.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.MAN) ? person.getAge() <= 65 : person.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
    /**
     * Возвращает список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
     * @param town Collection
     * @return List
     */
    public static List<String> surnamesOfConscripts(Collection<Person> town) {
        return town.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
    }
    /**
     * Возвращает количество несовершеннолетних (т.е. людей младше 18 лет).
     * @param town Collection
     * @return long
     */
    public static long countingMinors(Collection<Person> town) {
        return town.stream()
                    .filter(person -> person.getAge() < 18)
                    .count();
    }
    /**
     * Создаёт 10_000_000 объектов типа Person, коллекцией.
     * @return Collection
     */
    public static Collection<Person> getListOfPeople() {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}
