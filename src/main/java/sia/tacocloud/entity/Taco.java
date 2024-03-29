package sia.tacocloud.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();
    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;


    @Size(min=1, message = "You must choose at least 1 ingredient")
    @ManyToMany
    @Autowired
    private List <Ingredient> ingredients;

}




