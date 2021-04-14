package be.intecbrussel.healthy_goal.model;

import java.util.Random;

public class Advice {

    static Random random = new Random();
    private static final String DID_YOU_KNOW = "Did you know that ";
    private static final String[] ADVICE_LIST = {
            "there is strong evidence that higher levels of physical activity are linked to lower risk of several types of cancer ?",
            "some evidence that exercise is beneficial for bone health and sleep quality ?",
            "physical activity improves immune system function ?",
            "obesity increases your risk of severe illness from COVID-19 ?",
            "BMI remains a strong independent risk factor for severe COVID-19 ?",
            "the effect of BMI on the immune system is more apparent for women ?",
            "weight loss modulates immune system parameters of patients with virus HCV ?",
            "excess weight creates high blood pressure (Hypertension) ?",
            "excess weight creates high LDL cholesterol or high levels of triglycerides (Dyslipidemia) ?"
    };

    public String getAdvice() {
        int randomIndex = random.nextInt(ADVICE_LIST.length);
        return DID_YOU_KNOW + ADVICE_LIST[randomIndex];
    }
}
