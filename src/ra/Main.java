package ra;

import ra.bussiness.entity.User;
import ra.bussiness.impl.UserImp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> list= new UserImp().getAll();
        if (list==null){
            list = new ArrayList<>();
        }
        User addmin = new User(2,"admin123","123456",1,true);
        list.add(addmin);
        new UserImp().writeToFile(list);
    }

}
