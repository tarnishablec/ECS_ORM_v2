package ORMcore;

public interface DBTypeConvertor {

    String databaseType2JavaType(String columType);

    String javaType2DatabaseType(String javaType);
}
