package sia.tacocloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.entity.Ingredient;
import sia.tacocloud.entity.Ingredient.Type;
import sia.tacocloud.entity.Taco;
import sia.tacocloud.entity.TacoOrder;
import sia.tacocloud.data.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    @Autowired
    private IngredientRepository ingredientRepo;
@Autowired
    public  DesignTacoController(IngredientRepository ingredientRepo) {
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }
    }
@ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
}
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }
    @GetMapping
    public String showDesignForm(Model model) {

        System.out.println(model.asMap());

        return "design";
    }
    @PostMapping
    public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors,
                              @ModelAttribute("tacoOrder") TacoOrder order) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("design controller Processing taco: {}", taco);
        order.addTaco(taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> Ingredient, Type type) {
        return Ingredient
                .stream()
                .filter(x-> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    }


