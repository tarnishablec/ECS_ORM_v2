package ECScore;

public class ComponentGroup implements IEquatable<ComponentGroup>{
    private Class[] componentDataClasses;

    public Class[] getComponentDataClasses() {
        return componentDataClasses;
    }

    public void setComponentDataClasses(Class...componentDataClasses) {
        this.componentDataClasses = componentDataClasses;
    }

    public ComponentGroup(Class...componentDataClasses) {
        this.componentDataClasses = componentDataClasses;
    }

    @Override
    public boolean equalsTo(ComponentGroup other) {
        int hashSum=0;
        for (Class c:componentDataClasses){
            hashSum +=c.hashCode();
        }
        for (Class oc:other.componentDataClasses){
            hashSum -=oc.hashCode();
        }
        return hashSum == 0;
    }

}
