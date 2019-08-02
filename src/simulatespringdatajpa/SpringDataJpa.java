package simulatespringdatajpa;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * @author sunhang
 * @date 2019/8/2 17:11
 * @email sunhang@weli.cn
 */
public class SpringDataJpa<T> {
    private Class<T> beanClass;
    public SpringDataJpa() {
        /**
         * 在main方法中只创建了DataBaseConnection对象
         * 因此此处this关键字指向的引用是DataBaseConnection
         * 父类持有子类的引用（吃惊）
         * */
        /**
         * 由子类引用获取泛型父类引用
         * */
        beanClass = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public void add(T bean) throws IllegalAccessException {
        Field[] declaredFields = beanClass.getDeclaredFields();
        //拼接表名
        for (int i = 0; i < declaredFields.length; i++) {
            //append query sql
            // 通过table注解获取表名使数据库表名和预设的值一致
            String tableName = beanClass.getAnnotation(Table.class).value();
        }
        //获取bean的字段值
        ArrayList<Object> parameterList = new ArrayList<>();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            Object o = declaredFields[i].get(bean);
            parameterList.add(o);
        }
        Object[] params = parameterList.toArray(new Object[parameterList.size()]);
//        jdbcTemplate.update(sql,params);
    }
}
