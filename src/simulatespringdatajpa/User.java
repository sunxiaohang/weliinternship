package simulatespringdatajpa;

/**
 * @author sunhang
 * @date 2019/8/2 17:22
 * @email sunhang@weli.cn
 */
@Table("t_user")
public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
