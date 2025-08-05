package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    public Burger burger;

    @Mock private Bun bun;
    @Mock private Ingredient ingredientA;
    @Mock private Ingredient ingredientB;
    @Mock private Ingredient ingredientC;

    @Before
    public void makeBurger() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("Название булочки");
        assertEquals("Установлена не верная булочка", "Название булочки", burger.bun.getName());
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientA);
        assertEquals("В бургер было добавлено " + burger.ingredients.size() +
                        " ингредиентов, ожидалось добавление 1 ингредиента",
                1, burger.ingredients.size());
        assertTrue("Ингредиент не добавился в бургер", burger.ingredients.contains(ingredientA));
    }

    @Test
    public void removeIngredientTest() {
        burger.ingredients.add(ingredientA);
        burger.ingredients.add(ingredientB);
        burger.ingredients.add(ingredientC);
        burger.removeIngredient(1);
        assertEquals("Из бургера ингредиент не был удален", 2, burger.ingredients.size());
        assertTrue("Из бургера был удален не тот ингредиент",
                burger.ingredients.contains(ingredientA) &&
                        burger.ingredients.contains(ingredientC) &&
                        !burger.ingredients.contains(ingredientB));
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.add(ingredientA);
        burger.ingredients.add(ingredientB);
        burger.ingredients.add(ingredientC);
        burger.moveIngredient(1, 0);
        assertEquals("В бергере ошибочное число ингредиентов", 3, burger.ingredients.size());
        assertTrue("В бергере находится не тот ингредиент",
                burger.ingredients.contains(ingredientA) &&
                        burger.ingredients.contains(ingredientB) &&
                        burger.ingredients.contains(ingredientC));
        assertEquals("Ингредиент не был перемещен на новое место", burger.ingredients.get(0), ingredientB);
        assertEquals("Ингредиент не был перемещен с предыдущего места", burger.ingredients.get(1), ingredientA);
    }
}