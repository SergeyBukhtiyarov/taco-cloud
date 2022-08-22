package sia.tacocloud.repository.user;


import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.entity.User.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
