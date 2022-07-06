package sia.tacocloud;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import sia.tacocloud.Ingredient.Type;
@Component
public class IngredientsByIdConverter implements Converter<String, Ingredient> {
    private IngredientRepository ingredientRepo;
@Autowired
    public IngredientsByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo=ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
