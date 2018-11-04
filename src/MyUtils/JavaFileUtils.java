package MyUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ORMcore.*;

public class JavaFileUtils {

    public static void main(String[] args) {

    }

    private static JavaFieldGetSet createField(DBColumn column, DBTypeConvertor convertor) {
        JavaFieldGetSet jfgs = new JavaFieldGetSet();

        String javaFieldType = convertor.databaseType2JavaType(column.getDataType());

        jfgs.setFieldInfo("\tpublic "+javaFieldType+" "+column.getColumnName()+";\n");

        return jfgs;
    }

    private static String createJavaSrc(DBTable table, DBTypeConvertor convertor){

        Map<String,DBColumn> columns= table.getColumns();
        List<JavaFieldGetSet> javaFields = new ArrayList<>();

        for (DBColumn c:columns.values()){
            javaFields.add(createField(c,convertor));
        }
        StringBuilder src = new StringBuilder();

        src.append("package ").append(DBManager.getConfiguration().getComponentPath()).append(";\n\n");

        src.append("import ECScore.*;\n");
//        src.append("import java.util.*;\n");

        src.append("public class ").append(StringUtils.firstChar2UpCase(table.getTableName())).append(" extends IComponentData{\n\n");

        for (JavaFieldGetSet f:javaFields){
            src.append(f.getFieldInfo());
        }
        src.append("\n\n");

        src.append("}\n");
        return src.toString();
    }

    public static void createJavaFile(DBTable table, DBTypeConvertor convertor){
        String src = createJavaSrc(table,convertor);

        String srcPath = DBManager.getConfiguration().getSrcPath()+"\\";

        String packagePath = DBManager.getConfiguration().getComponentPath();

        File f = new File(srcPath+packagePath);

//        System.out.println(f.getAbsolutePath());

        if (!f.exists()){
            f.mkdirs();
        }

        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter(f.getAbsolutePath()+"\\"+StringUtils.firstChar2UpCase(table.getTableName())+".java"));
            bw.write(src);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
