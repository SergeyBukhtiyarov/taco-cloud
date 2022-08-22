package sia.tacocloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sia.tacocloud.entity.User.User;
import sia.tacocloud.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WebUserService implements UserDetailsService {//interface UserDetailService для работы spring security


    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private RoleService roleService; //для получения дефотлной роли при регистрации
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder; //шифрование пароля

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User webUser = UserRepository.findByUsername(username);
        if (webUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return webUser;
    }

    public User findUserById(Long webUserId) {
        Optional<User> userFromDB = UserRepository.findById(webUserId);
        return userFromDB.orElse(new User());
    }

    public User findUserByUsername(String username) {
        User userFromDB = UserRepository.findByUsername(username);
        return userFromDB;
    }


    public boolean saveUser(User User) {
        User userFromDB = UserRepository.findByUsername(User.getUsername());
        if (userFromDB != null) {
            return false;
        }
        //устанавливает дефолтную роль и шифрует пароль
        if (User.getRoles() == null) {
//            System.out.println("test default role check");
            User.setRoles(roleService.getDefaultRoleSet());
        }
        User.setPassword(bCryptPasswordEncoder.encode(User.getPassword()));
        //сохраняем в БД
        UserRepository.save(User);
        return true;
    }

    public boolean deleteUser(Long webUserId) {
        if (UserRepository.findById(webUserId).isPresent()) {
            UserRepository.deleteById(webUserId);
            return true;
        } else return false;
    }

    public List<User> findAllUsers() {
        return (List<User>) UserRepository.findAll();
    }

}
