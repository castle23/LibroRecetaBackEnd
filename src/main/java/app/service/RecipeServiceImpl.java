package app.service;

import app.recipe.Ingredient;
import app.recipe.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.datasource.RecipeDatasource;

public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	@Autowired
	private RecipeDatasource datasource;
	
	@Override
	public RecipesResponse getRecipes() {
		
		RecipesResponse response = new RecipesResponse();
		response.setCode("200");
		response.setDescription("ok");
		
		try{
			response.getRecipes().addAll(datasource.findAll());
		} catch (Exception e) {
			logger.error("Error al obtener las recetas de la base de datos.", e);
			response.setCode("500");
			response.setDescription("Ha ocurrido un error al consultar las recetas");
		}
		
		return response;
	}

	@Override
	public RecipesResponse getRecipe(long id) {

		RecipesResponse response = new RecipesResponse();
		response.setCode(AbstractResponse.CODE_OK);
		response.setDescription(AbstractResponse.ESTATUS_OK);

		try{
			response.setData(datasource.findById(id));
		} catch (Exception e) {
			logger.error("Error al obtener la receta "+id+" de la base de datos.", e);
			response.setCode(AbstractResponse.CODE_ERROR_INTERNO);
			response.setDescription("Ha ocurrido un error al consultar la receta " + id);
		}

		return response;
	}

	@Override
	public RecipesResponse saveRecipe(Recipe recipe) {

		RecipesResponse response = new RecipesResponse();
		response.setCode(AbstractResponse.CODE_OK);
		response.setDescription(AbstractResponse.ESTATUS_OK);

		try{
            recipe.setId(null);
			datasource.persist(recipe);
            response.setData(recipe);
		} catch (Exception e) {
			logger.error("Error al crear la receta", e);
			response.setCode(AbstractResponse.CODE_ERROR_INTERNO);
			response.setDescription("Ha ocurrido un error intentar crear la receta ");
		}

		return response;
	}


	@Override
	public RecipesResponse updateRecipe(long id, Recipe recipe) {

		RecipesResponse response = new RecipesResponse();
		response.setCode(AbstractResponse.CODE_OK);
		response.setDescription(AbstractResponse.ESTATUS_OK);

		try{
			Recipe recipeOld = datasource.findById(id);
			if (recipeOld != null){
				recipe.setId(id);
				for (Ingredient ingredient : recipe.getIngredients()) {
					ingredient.setRecipe(new Recipe(id));
				}
				datasource.persist(recipe);
			}else{
				response.setDescription("Error no se ha encontrado la receta " + id);
			}
		} catch (Exception e) {
			logger.error("Error al actualizar la receta " + id, e);
			response.setCode(AbstractResponse.CODE_ERROR_INTERNO);
			response.setDescription("Ha ocurrido un error intentar actualizar la receta " + id);
		}

		return response;
	}


	

}
