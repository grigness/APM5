package Domain;

public class MealPlanner {

    String name;
    int cookingTime;
    String ingredients;


    public MealPlanner(String name, int cookingTime, String ingredients) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String contents) {
        this.ingredients = contents;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return
                "Recipe ='" + name + '\'' +
                        " | " + cookingTime +
                        " | " + ingredients + '\'';
    }

}
