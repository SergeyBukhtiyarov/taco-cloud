package sia.tacocloud.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Set;

@Data
@Entity(name = "role")
@NoArgsConstructor
public class Role implements GrantedAuthority { //interface GrantedAuthority для работы spring security

    @Id
    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> Users;

    @Override //возвращает имя роли для SpringSecurity
    public String getAuthority() {
        return getName();
    }

    @Override //для более красивого отображения на admin-page
    public String toString() {
        return name;
    }
}
