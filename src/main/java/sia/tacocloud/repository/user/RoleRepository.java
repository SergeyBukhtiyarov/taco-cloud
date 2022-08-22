package sia.tacocloud.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloud.entity.User.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
