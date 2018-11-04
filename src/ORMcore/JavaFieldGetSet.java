package ORMcore;

public class JavaFieldGetSet {
    private String fieldInfo;


    public JavaFieldGetSet() {
    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }



    public String toString(){
        System.out.println(fieldInfo);
        return super.toString();
    }
}
