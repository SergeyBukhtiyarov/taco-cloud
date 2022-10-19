package sia.tacocloud;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import sia.tacocloud.entity.Ingredient;
import sia.tacocloud.entity.User.Role;
import sia.tacocloud.entity.User.User;
import sia.tacocloud.repository.IngredientRepository;
import sia.tacocloud.service.RoleService;
import sia.tacocloud.service.WebUserService;

@SpringBootApplication   (exclude = {SecurityAutoConfiguration.class})
public class TacoCloudApplication {



    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);

    }


    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.WRAP));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            repo.save(new Ingredient("SRCA", "Sour Cream", Ingredient.Type.SAUCE));
        };

    }
    @Bean
    public ApplicationRunner dataLoader(RoleService roleService, WebUserService webUserService) {

        return args -> {
//      preload 2 basic roles to data base
            Role userRole = new Role(1L, "ROLE_USER");
            Role adminRole = new Role(2L, "ROLE_ADMIN");
            roleService.saveRole(userRole);
            roleService.saveRole(adminRole);
//      create root user and grant him all roles
            User adminUser = new User();
            adminUser.setUsername("adminTest");
            adminUser.setPassword("123");   //TODO change to encoded password or to env vars
            adminUser.setPasswordConfirm("123");
            adminUser.setEmail("admin@email.ru");
            adminUser.setRoles(roleService.getAllRoles());
            webUserService.saveUser(adminUser);
        };
    }
}
