package creatingstreamfromsources;

import java.util.Date;

/**
 * @author sunhang
 * @date 2019/7/29 11:39
 * @email sunhang@weli.cn
 */
public class Person implements Comparable<Person>{
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private int salary;
    private double coeficient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    @Override
    public int compareTo(Person o) {
        int compareLastNames = this.getLastName().compareTo(o.getLastName());
        if (compareLastNames !=0){
            return compareLastNames;
        }else {
            this.getFirstName().compareTo(o.getFirstName());
        }
        return 0;
    }
}
