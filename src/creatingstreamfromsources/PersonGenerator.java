package creatingstreamfromsources;

import java.util.*;

/**
 * @author sunhang
 * @date 2019/7/29 11:45
 * @email sunhang@weli.cn
 */
public class PersonGenerator {
    public static List<Person> generatePersonList(int size) {
        List<Person> result = new ArrayList<>();
        String[] firstNames = {"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "James", "John", "Robert", "Michael", "William"};
        String[] lastNames = {"Smith", "Jones", "Taylor", "Williams", "Brown", "Davies", "Evans", "Wilson", "Thomas", "Roberts"};
        Random randomGenerator = new Random();
        for (int i = 0; i < size; i++) {
            Person person = new Person();
            person.setId(i);
            person.setFirstName(firstNames[randomGenerator.nextInt(10)]);
            person.setLastName(lastNames[randomGenerator.nextInt(10)]);
            person.setSalary(randomGenerator.nextInt(100000));
            person.setCoeficient(randomGenerator.nextDouble() * 10);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -randomGenerator.nextInt(30));
            Date birthDate = calendar.getTime();
            person.setBirthday(birthDate);
            result.add(person);
        }
        return result;
    }
}
