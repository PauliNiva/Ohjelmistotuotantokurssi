
package ohtu.data_access;

import ohtu.domain.User;
import java.util.List;

public interface UserDao {
    List<User> listAll();
    User findByName(String name);
    void add(User user);
}
