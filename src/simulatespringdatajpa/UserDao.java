package simulatespringdatajpa;

/**
 * @author sunhang
 * @date 2019/8/2 17:34
 * @email sunhang@weli.cn
 */
public class UserDao extends SpringDataJpa<User> {
    @Override
    public void add(User bean) throws IllegalAccessException {
        super.add(bean);
    }
}