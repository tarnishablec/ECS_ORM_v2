package ECScore;

import ORMcore.DBManager;

import java.sql.Connection;

public class DatabaseFilter {

    private static int getSuperId(Class...classes){
        Connection conn = DBManager.createConn();
        for (Class c:classes){

        }
        return 0;
    }
}
