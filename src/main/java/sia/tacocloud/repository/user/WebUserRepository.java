package sia.tacocloud.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloud.entity.User.User;

public interface WebUserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

}
