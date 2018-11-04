package Test;

import Components.*;
import ECScore.Entity;
import ECScore.EntityManager;
import ECScore.Filter;

import java.util.ArrayList;

public class Test1 {
    public static void main(String[] args) {
//        EntityManager.init();

        Filter filter = new Filter(Account.class,Appoint.class);
        ArrayList<Entity> entities= EntityManager.loadEntities(filter);
        System.out.println(entities.size());
        for (Entity e:entities){
            System.out.println(EntityManager.getComponentData(e,Account.class).id);
        }
    }
}
