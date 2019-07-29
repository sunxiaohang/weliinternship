package multiplecondition;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class FileMock {
    private String[] content;
    private int index;
    public FileMock(int size,int length){
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder builder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int randomCharacter = (int) (Math.random()*255);
                builder.append((char) randomCharacter);
            }
            content[i] = builder.toString();
        }
        index=0;
    }
    public boolean hasMoreLines(){
        return index<content.length;
    }
    public String getLine(){
        if(this.hasMoreLines()){
            return content[index++];
        }
        return null;
    }
}
