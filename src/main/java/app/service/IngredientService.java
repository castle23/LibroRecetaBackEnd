package app.service;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;

/**
 * Created by carlos on 17/05/18.
 */
@Path("ingredient")
@Component
@Consumes("application/json")
@Produces("application/json")
public interface IngredientService {

    @GET
    @Path("/{idRecipe}/")
    public AbstractResponse getIngredientsbyRecipe(@PathParam("idRecipe") long id);
}
