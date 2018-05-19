package app.datasource;

import java.util.List;

import app.recipe.Recipe;


public interface RecipeDatasource extends AbstractDAO{

	List<Recipe> findAll();

    Recipe findById(long id);
}
