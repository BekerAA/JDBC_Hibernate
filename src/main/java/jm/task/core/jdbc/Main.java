package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.service.UserService;
import java.util.List;

public class Main {
    static void main() {
        UserService userServie = new UserServiceImpl();
        userServie.createUsersTable();
        userServie.saveUser("Andrey", "Pushkov", (byte) 23);
        userServie.saveUser("Lara", "Akopin", (byte) 21);
        userServie.saveUser("Alina", "Pushkova", (byte) 18);
        userServie.saveUser("Misha", "Abakumov", (byte) 25);
        List<User> users = userServie.getAllUsers();
        for ( User user: users){
            System.out.println(user.toString());
        }
        userServie.cleanUsersTable();
        userServie.dropUsersTable();
    }
}
