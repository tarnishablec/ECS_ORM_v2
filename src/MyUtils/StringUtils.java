package MyUtils;

class StringUtils {

    static String firstChar2UpCase(String str){
        return str.toUpperCase().substring(0,1)+str.substring(1);
    }
}
