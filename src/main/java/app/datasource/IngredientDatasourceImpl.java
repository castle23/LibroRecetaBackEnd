package app.datasource;

import app.recipe.Ingredient;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by carlos on 17/05/18.
 */
@Repository
@Transactional
public class IngredientDatasourceImpl extends AbstractDAOImpl implements IngredientDatasource{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ingredient> findAll() {
        return findAll(Ingredient.class);
    }

    @Override
    public Ingredient findById(long id) {
        return load(Ingredient.class, id);
    }

    @Override
    public List<Ingredient> findByIdRecipe(long id) {
        Map<String,Object> params = new HashMap<>();
        params.put("recipeId",id);
        return findListNamedQuery("Ingredient.SumByIdRecipe",params);
    }

}
