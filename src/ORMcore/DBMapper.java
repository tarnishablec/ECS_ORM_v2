package ORMcore;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBMapper {
    private static Map<String,DBTable> tables = new HashMap<>();

    public static Map<String, DBTable> getTables() {
        return tables;
    }

    static {
        try {
            Connection conn = DBManager.createConn();
            assert conn != null;
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet tableSet = dmd.getTables(DBManager.getConfiguration().getDatabaseName(), "%", "%", new String[]{"TABLE"});
            System.out.println(DBManager.getConfiguration().getDatabaseName());
            System.out.println(DBManager.getConfiguration().getComponentPath());
            while (tableSet.next()) {
                String tableName = (String) tableSet.getObject("TABLE_NAME");
                DBTable ti = new DBTable(tableName, new HashMap<>(),new DBColumn());
                tables.put(tableName, ti);
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

