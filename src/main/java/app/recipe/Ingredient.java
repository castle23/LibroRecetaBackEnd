package app.recipe;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Ingredient.SumByIdRecipe",query = "select sum(amount) as amount, name from Ingredient where recipe.id = :recipeId group by name")
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
