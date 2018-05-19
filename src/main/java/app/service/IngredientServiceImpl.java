package app.service;

import app.datasource.IngredientDatasource;
import app.recipe.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by carlos on 17/05/18.
 */
public class IngredientServiceImpl implements IngredientService {

    private static final Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);

    @Autowired
    private IngredientDatasource datasource;

    @Override
    public AbstractResponse getIngredientsbyRecipe(long id) {

        AbstractResponse response = new AbstractResponse();
        response.setCode(AbstractResponse.CODE_OK);
        response.setDescription(AbstractResponse.ESTATUS_OK);

        try{
            List<Ingredient> ingredientList = datasource.findByIdRecipe(id);
            if(ingredientList != null && !ingredientList.isEmpty()){
                response.setData(datasource.findByIdRecipe(id));
            }else{
                response.setDescription("No se encontraron ingredientes para la receta");
            }

        } catch (Exception e) {
            logger.error("Error al obtener la receta "+id+" de la base de datos.", e);
            response.setCode(AbstractResponse.CODE_ERROR_INTERNO);
            response.setDescription("Ha ocurrido un error al consultar la receta " + id);
        }

        return response;
    }
}
