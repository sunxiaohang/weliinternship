package jointaskresults;

import java.util.Random;

/**
 * @author sunhang
 * @date 2019/7/29 10:26
 * @email sunhang@weli.cn
 */
public class DocumentMock {
    private String[] words = {"the","hello","goodbye","packet","java","thread","pool","random","class","main"};
    public String[][] generateDocument(int numLine,int numWords,String word){
        int counter = 0;
        String[][] document = new String[numLine][numWords];
        Random random = new Random();
        for (int i = 0; i < numLine; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if(document[i][j].equals(word)){
                    counter++;
                }
            }
        }
        return document;
    }
}
