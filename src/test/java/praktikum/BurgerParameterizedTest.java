package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final Burger burger;

    @Mock private Bun bun;
    @Mock private Ingredient ingredient1;
    @Mock private Ingredient ingredient2;
    @Mock private Ingredient ingredient3;

    private final TestData testData;

    public BurgerParameterizedTest(TestData testData) {
        this.testData = testData;
        MockitoAnnotations.initMocks(this);
        this.burger = new Burger();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new TestData(
                        "Бургер с одной булочкой и без ингредиентов",
                        "white bun", 100f,
                        null, null, 0f, false,
                        null, null, 0f, false,
                        null, null, 0f, false,
                        200f,  // 100 * 2
                        String.format("(==== white bun ====)%n(==== white bun ====)%n%nPrice: 200,000000%n")
                )},
                {new TestData(
                        "Бургер с булочкой и одним ингредиентом",
                        "black bun", 150f,
                        IngredientType.SAUCE, "hot sauce", 50f, true,
                        null, null, 0f, false,
                        null, null, 0f, false,
                        350f,  // 150 * 2 + 50
                        String.format("(==== black bun ====)%n= sauce hot sauce =%n(==== black bun ====)%n%nPrice: 350,000000%n")
                )},
                {new TestData(
                        "Бургер с булочкой и тремя ингредиентами",
                        "red bun", 80f,
                        IngredientType.FILLING, "cutlet", 200f, true,
                        IngredientType.SAUCE, "mustard", 30f, true,
                        IngredientType.FILLING, "cheese", 150f, true,
                        540f,  // 80 * 2 + 200 + 30 + 150
                        String.format("(==== red bun ====)%n= filling cutlet =%n= sauce mustard =%n= filling cheese =%n(==== red bun ====)%n%nPrice: 540,000000%n")
                )}
        });
    }

    @Before
    public void setUp() {
        // Настройка моков для булочки
        when(bun.getName()).thenReturn(testData.bunName);
        when(bun.getPrice()).thenReturn(testData.bunPrice);

        // Настройка моков для ингредиентов
        if (testData.addIngredient1) {
            when(ingredient1.getType()).thenReturn(testData.ingredient1Type);
            when(ingredient1.getName()).thenReturn(testData.ingredient1Name);
            when(ingredient1.getPrice()).thenReturn(testData.ingredient1Price);
        }
        if (testData.addIngredient2) {
            when(ingredient2.getType()).thenReturn(testData.ingredient2Type);
            when(ingredient2.getName()).thenReturn(testData.ingredient2Name);
            when(ingredient2.getPrice()).thenReturn(testData.ingredient2Price);
        }
        if (testData.addIngredient3) {
            when(ingredient3.getType()).thenReturn(testData.ingredient3Type);
            when(ingredient3.getName()).thenReturn(testData.ingredient3Name);
            when(ingredient3.getPrice()).thenReturn(testData.ingredient3Price);
        }

        // Сборка бургера
        burger.setBuns(bun);
        if (testData.addIngredient1) burger.addIngredient(ingredient1);
        if (testData.addIngredient2) burger.addIngredient(ingredient2);
        if (testData.addIngredient3) burger.addIngredient(ingredient3);
    }

    @Test
    public void testGetPrice() {
        assertEquals(testData.description + ": Неверная цена бургера",
                testData.expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        String receipt = burger.getReceipt();
        String normalizedExpected = testData.expectedReceipt.replace("\r\n", "\n").replace("\r", "\n");
        String normalizedActual = receipt.replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(testData.description + ": Чек не соответствует ожидаемому",
                normalizedExpected, normalizedActual);
    }

    private static class TestData {
        final String description;
        final String bunName;
        final float bunPrice;

        final IngredientType ingredient1Type;
        final String ingredient1Name;
        final float ingredient1Price;
        final boolean addIngredient1;

        final IngredientType ingredient2Type;
        final String ingredient2Name;
        final float ingredient2Price;
        final boolean addIngredient2;

        final IngredientType ingredient3Type;
        final String ingredient3Name;
        final float ingredient3Price;
        final boolean addIngredient3;

        final float expectedPrice;
        final String expectedReceipt;

        public TestData(String description,
                        String bunName, float bunPrice,
                        IngredientType ingredient1Type, String ingredient1Name, float ingredient1Price, boolean addIngredient1,
                        IngredientType ingredient2Type, String ingredient2Name, float ingredient2Price, boolean addIngredient2,
                        IngredientType ingredient3Type, String ingredient3Name, float ingredient3Price, boolean addIngredient3,
                        float expectedPrice, String expectedReceipt) {
            this.description = description;
            this.bunName = bunName;
            this.bunPrice = bunPrice;
            this.ingredient1Type = ingredient1Type;
            this.ingredient1Name = ingredient1Name;
            this.ingredient1Price = ingredient1Price;
            this.addIngredient1 = addIngredient1;
            this.ingredient2Type = ingredient2Type;
            this.ingredient2Name = ingredient2Name;
            this.ingredient2Price = ingredient2Price;
            this.addIngredient2 = addIngredient2;
            this.ingredient3Type = ingredient3Type;
            this.ingredient3Name = ingredient3Name;
            this.ingredient3Price = ingredient3Price;
            this.addIngredient3 = addIngredient3;
            this.expectedPrice = expectedPrice;
            this.expectedReceipt = expectedReceipt;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}