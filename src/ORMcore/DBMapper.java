package ORMcore;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBMapper {
    static Map<String,DBTable> tables = new HashMap<>();

    public static Map<String, DBTable> getTables() {
        return tables;
    }

    static {
        try {
            Connection conn = DBManager.createConn();
            assert conn != null;
            DatabaseMetaData dmd = conn.getMetaData();

            ResultSet tableSet = dmd.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tableSet.next()) {
                String tableName = (String) tableSet.getObject("TABLE_NAME");
                DBTable ti = new DBTable(tableName, new HashMap<>(),new DBColumn());
                if (ti.getTableName().equals("account")||ti.getTableName().equals("appoint")||ti.getTableName().equals("doctag")){                                      //有问题！！！！！
                    tables.put(tableName, ti);                                                                                          //有问题！！！！！
                }                                                                                                                       //有问题！！！！！

                ResultSet columns = dmd.getColumns(null, "%", tableName, "%");
                while (columns.next()) {
                    DBColumn ci = new DBColumn(columns.getString("COLUMN_NAME"), columns.getString("TYPE_NAME"));
                    ti.getColumns().put(columns.getString("COLUMN_NAME"), ci);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
