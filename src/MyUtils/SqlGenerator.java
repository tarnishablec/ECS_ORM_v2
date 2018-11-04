package MyUtils;


public class SqlGenerator {
    public static String generateSelectSql(Class...classes){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from "+classes[0].getSimpleName());
        for (int i = 1; i < classes.length; i++) {
            sb.append(" join "+classes[i].getSimpleName()+" on "+classes[i-1].getSimpleName()+".superid="+classes[i].getSimpleName()+".superid");
        }
        sb.append(";");
        return sb.toString();
    }

}

