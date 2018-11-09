package Systems;

import Components.*;
import ECScore.*;

import java.util.ArrayList;

public class LoginSystem {
    public static boolean login(Integer id,String pwd){
        Filter filter = new Filter(Account.class);
        ArrayList<Entity> entities = EntityManager.loadEntities(filter);
        if (entities != null) {
            for (Entity entity:entities){
                if (id.equals(EntityManager.getComponentData(entity, Account.class).accountId)){
                    if (pwd.equals(EntityManager.getComponentData(entity,Account.class).password)){
                        System.out.println("login success!");
                        return true;
                    }
                    else {
                        System.out.println("wrong password.");
                        return false;
                    }
                }
            }
            System.out.println("account don't exist.");
            return false;
        }
        else {
            System.out.println("no account.");
            return false;
        }
    }
}
