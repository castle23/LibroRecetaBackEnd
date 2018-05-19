package app.recipe;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "Ingredient.SumByIdRecipe",query = "select id, name, sum(amount) as amount, recipe_id from ingredient where recipe_id = :recipeId group by name", resultClass = Ingredient.class)
})
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	private double amount;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	public Ingredient() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
