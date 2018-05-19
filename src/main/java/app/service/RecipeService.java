package app.service;

import javax.ws.rs.*;

import app.recipe.Recipe;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


@Path("recipe")
@Component
@Consumes("application/json")
@Produces("application/json")
public interface RecipeService {
	
	@GET
	@Path("/all")
	public RecipesResponse getRecipes();


	@GET
	@Path("/{id}/")
	public RecipesResponse getRecipe(@PathParam("id") long id);

	@POST
	@Path("/")
	public RecipesResponse saveRecipe(@RequestBody Recipe recipe);

	@PUT
	@Path("/{id}/")
	public RecipesResponse updateRecipe(@PathParam("id") long id, @RequestBody Recipe recipe);
}
