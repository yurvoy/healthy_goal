package be.intecbrussel.healthy_goal.model;

import java.util.Random;

public class Advies {

    static Random random = new Random();
    private static String didYouKnow = "Did you know that ";
    private static String[] advies = {
            "there is strong evidence that higher levels of physical activity are linked to lower risk of several types of cancer.",
            "some evidence that exercise is beneficial for bone health and sleep quality.",
            "physical activity improves immune system function"
    };

    public static String getAdvies() {
        int randomIndex = random.nextInt(advies.length);
        return didYouKnow + advies[randomIndex];
    }
}
