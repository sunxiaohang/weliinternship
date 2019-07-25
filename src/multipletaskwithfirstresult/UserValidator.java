package multipletaskwithfirstresult;

import java.util.Random;

/**
 * @author Administrator
 */
public class UserValidator {
    private final String name;

    public UserValidator(String name) {
        this.name = name;
    }
    public boolean validate(String name,String password){
        Random random = new Random();
        int duration = random.nextInt(10);
        System.out.println("validate duration: "+duration);
        return random.nextBoolean();
    }

    public String getName() {
        return name;
    }
}
