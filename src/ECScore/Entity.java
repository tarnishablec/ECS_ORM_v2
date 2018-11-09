package ECScore;

import java.util.concurrent.ConcurrentHashMap;

public class Entity extends PreparedEntity{
    public Integer superid;
    ConcurrentHashMap<Class,IComponentData> componentDataHashMap;

    ConcurrentHashMap<Class, IComponentData> getComponentDataHashMap() {
        return componentDataHashMap;
    }

    {
        componentDataHashMap=new ConcurrentHashMap<>(10);
    }
}
