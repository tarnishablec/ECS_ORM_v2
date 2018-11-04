package ECScore;

import java.util.concurrent.ConcurrentHashMap;

public class Entity {
    int entityId;
    ConcurrentHashMap<Class,IComponentData> componentDataHashMap;

    public ConcurrentHashMap<Class, IComponentData> getComponentDataHashMap() {
        return componentDataHashMap;
    }

    {
        componentDataHashMap=new ConcurrentHashMap<>(10);
    }

    Entity(){}
}
