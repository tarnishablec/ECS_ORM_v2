package ECScore;

import ORMcore.*;
import MyUtils.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class EntityManager {

    private static class EntityManagerInstance{
        private static final EntityManager instance = new EntityManager();
    }

    public static EntityManager getOrCreateManager(){
        return EntityManagerInstance.instance;
    }



    private static Entity generateEntity(IComponentData... componentDatas){
        Entity entity = new Entity();
        for (IComponentData componentData : componentDatas) {
            entity.componentDataHashMap.put(componentData.getClass(), componentData);
        }
        return entity;
    }

    private static Entity generateEntity(Class...classes){
        Entity entity = new Entity();
        for (Class c:classes){
            if (!c.isAssignableFrom(IComponentData.class)){
                throw new NullPointerException();
            }

            try {
                entity.componentDataHashMap.put(c,(IComponentData) c.getConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    public static void init(){
        Map<String, DBTable> map= DBMapper.getTables();
        for (var t:map.values()){
            JavaFileUtils.createJavaFile(t,new MysqlTypeConvertor());
        }
    }

    public static void addComponent(Entity entity, IComponentData...componentDatas){
        for (IComponentData componentData:componentDatas){
            entity.componentDataHashMap.put(componentData.getClass(),componentData);
        }
    }

    public static  <T extends IComponentData> T getComponentData(Entity entity,Class<T> tClass){
        IComponentData icd;
        icd=entity.componentDataHashMap.getOrDefault(tClass,null);
        return (T)icd;
    }

    public static ArrayList<Entity> loadEntities(Filter filter){
        ArrayList<Entity> entities = new ArrayList<>();
        ResultSet rs = null;
        String sql= SqlGenerator.generateSelectSql(filter.getComponentDataClasses());
        Connection connection = DBManager.createConn();
        try {
            rs= connection.prepareStatement(sql).executeQuery();
            if (rs!=null){
                while (rs.next()){
                    IComponentData[] iComponents = new IComponentData[filter.getComponentDataClasses().length];
                    for (int i = 0; i < filter.getComponentDataClasses().length; i++) {
                        IComponentData iComponent = (IComponentData) filter.getComponentDataClasses()[i].getConstructor().newInstance();
                        Field[] fields = filter.getComponentDataClasses()[i].getFields();
                        for (Field f:fields){
                            f.set(iComponent,rs.getObject(f.getName()));
                        }
                        iComponents[i]=iComponent;
                    }
                    Entity entity= EntityManager.generateEntity(iComponents);
                    entities.add(entity);
                }
            }
            else {
                return null;
            }
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return entities;
    }
}
