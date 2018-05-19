package app.datasource;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.recipe.Recipe;

@Repository
@Transactional
public class RecipeDatasourceImpl extends AbstractDAOImpl implements RecipeDatasource {
    
	@Autowired
	private SessionFactory sessionFactory;

	@Override
    public List<Recipe> findAll() {
		return findAll(Recipe.class);
    }

	@Override
    public Recipe findById(long id) {
		return load(Recipe.class, id);
    }


}
