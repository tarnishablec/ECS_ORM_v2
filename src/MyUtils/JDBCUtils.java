package MyUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

class JDBCUtils {
    public static void handleStatements(PreparedStatement statement, Object...objs){
        if (objs!=null){
            for (int i = 0; i < objs.length; i++) {
                try {
                    statement.setObject(1+i,objs[i]);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
