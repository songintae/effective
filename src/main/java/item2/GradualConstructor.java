package item2;

public class GradualConstructor {

    private final int servingSize;  //필수
    private final int servings;     //필수
    private final int calories;     //선택
    private final int fat;          //선택
    private final int sodium;       //선택
    private final int carbohydrate; //선택


    public GradualConstructor(int servingSize, int servings) {
        this(servingSize, servings,0);
    }

    public GradualConstructor(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public GradualConstructor(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0, 0);
    }

    public GradualConstructor(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public GradualConstructor(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
