package ORMcore;

import java.util.Map;

public class DBTable {
    private String tableName;
    private Map<String, DBColumn> columns;
    private DBColumn onlyPriKey;

    DBTable(String tableName, Map<String, DBColumn> columns, DBColumn onlyPriKey) {
        this.tableName = tableName;
        this.columns = columns;
        this.onlyPriKey = onlyPriKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, DBColumn> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, DBColumn> columns) {
        this.columns = columns;
    }

    public DBColumn getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(DBColumn onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }
}
