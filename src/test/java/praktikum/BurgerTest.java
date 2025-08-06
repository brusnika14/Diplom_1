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
        assertEquals("Название булочки", burger.bun.getName());
    }

    @Test
    public void addIngredientShouldIncreaseSize() {
        burger.addIngredient(ingredientA);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldContainAddedIngredient() {
        burger.addIngredient(ingredientA);
        assertTrue(burger.ingredients.contains(ingredientA));
    }

    @Test
    public void removeIngredientShouldDecreaseSize() {
        burger.ingredients.add(ingredientA);
        burger.ingredients.add(ingredientB);
        burger.ingredients.add(ingredientC);
        burger.removeIngredient(1);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveCorrectIngredient() {
        burger.ingredients.add(ingredientA);
        burger.ingredients.add(ingredientB);
        burger.ingredients.add(ingredientC);
        burger.removeIngredient(1);
        assertFalse(burger.ingredients.contains(ingredientB));
    }

    @Test
    public void moveIngredientShouldChangePosition() {
        burger.ingredients.add(ingredientA);
        burger.ingredients.add(ingredientB);
        burger.ingredients.add(ingredientC);

        burger.moveIngredient(1, 0);

        assertEquals(ingredientB, burger.ingredients.get(0));
        assertEquals(ingredientA, burger.ingredients.get(1));
    }
    @Test
    public void moveIngredientShouldNotChangeSize() {
        burger.ingredients.add(ingredientA);
        burger.ingredients.add(ingredientB);
        burger.ingredients.add(ingredientC);
        burger.moveIngredient(1, 0);
        assertEquals(3, burger.ingredients.size());
    }
}