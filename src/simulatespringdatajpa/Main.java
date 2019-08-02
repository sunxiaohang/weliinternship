package simulatespringdatajpa;

/**
 * @author sunhang
 * @date 2019/8/2 17:13
 * @email sunhang@weli.cn
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        UserDao userDao = new UserDao();
        User user = new User("mockName",20);
        userDao.add(user);
    }
}
