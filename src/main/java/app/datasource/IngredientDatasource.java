package app.datasource;

import app.recipe.Ingredient;

import java.util.List;

/**
 * Created by carlos on 17/05/18.
 */
public interface IngredientDatasource extends AbstractDAO {

    List<Ingredient> findAll();

    Ingredient findById(long id);

    List<Ingredient> findByIdRecipe(long id);
}
