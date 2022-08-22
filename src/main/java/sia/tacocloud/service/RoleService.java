package sia.tacocloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.entity.User.Role;
import sia.tacocloud.repository.user.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public HashSet<Role> getDefaultRoleSet() {
//        возвращает Set из дефотлных ролей для выдачи пользователю при регистрации
//        на текущий момент только одна дефолтная роль ROLE_USER id=1L
        Role defaultRole = roleRepository.findById(1L).orElse(null); //only one default role
        return new HashSet<Role>(Arrays.asList(defaultRole));
    }

    public HashSet<Role> getAllRoles() {
        //получает все роли, находящиеся в базе
        return new HashSet<>(roleRepository.findAll());
    }

    public void saveRole(Role role) {
        //создание новой роли
        //в случае отсутствия, ROLE_ADMIN и ROLE_USER создаются при запуске приложения в ApplicationRunner bean
        roleRepository.save(role);
    }


}
