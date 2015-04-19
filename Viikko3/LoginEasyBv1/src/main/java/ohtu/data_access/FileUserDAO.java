package ohtu.data_access;

import ohtu.domain.User;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileUserDAO implements UserDao {

    private Scanner reader;
    private String filename;
    private List<User> users;

    public FileUserDAO(String kalanimi) {
        this.filename = kalanimi;
        users = new ArrayList<User>();
    }

    @Override
    public List<User> listAll() {
        try {
            reader = new Scanner(new File(filename));
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        while (reader.hasNextLine()) {
            if (reader.hasNext()) {
                String name = reader.next();
                String password = reader.next();
                User user = new User(name, password);
                users.add(user);
            }
        }
        reader.close();
        return users;
    }

    @Override
    public User findByName(String name) {
        try {
            reader = new Scanner(new File(filename));
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        while (reader.hasNextLine()) {
            if (reader.hasNext()) {
                String username = reader.next();
                String password = reader.next();
                if (name.equals(username)) {
                    reader.close();
                    return new User(name, password);
                }
            } else {
                reader.close();
                return null;
            }
        }
        reader.close();
        return null;
    }

    @Override
    public void add(User user) {
        FileWriter writer;
        try {
            writer = new FileWriter(filename);
            writer.append(user.getUsername() + " " + user.getPassword() + "\n");
            writer.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
