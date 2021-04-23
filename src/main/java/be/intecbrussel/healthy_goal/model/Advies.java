package be.intecbrussel.healthy_goal.model;

import java.util.Random;

public class Advies {

    static Random random = new Random();
    private static final String DID_YOU_KNOW = "Wist je dat ";
    private static final String[] ADVICE_LIST = {
            "er zijn sterke aanwijzingen dat hogere niveaus van fysieke activiteit verband houden met een lager risico op verschillende soorten kanker?",
            "enig bewijs dat lichaamsbeweging gunstig is voor de gezondheid van de botten en slaapkwaliteit?",
            "lichamelijke activiteit verbetert de werking van het immuunsysteem?",
            "zwaarlijvigheid verhoogt uw risico op ernstige ziekte door COVID-19?",
            "BMI blijft een sterke onafhankelijke risicofactor voor ernstige COVID-19?",
            "is het effect van BMI op het immuunsysteem duidelijker voor vrouwen?",
            "gewichtsverlies moduleert immuunsysteemparameters van patiënten met virus HCV?",
            "overgewicht veroorzaakt hoge bloeddruk (hypertensie)?",
            "overgewicht zorgt voor een hoog LDL-cholesterol of hoge triglycerideniveaus (dyslipidemie)?"
    };

    public String getAdvice() {
        int randomIndex = random.nextInt(ADVICE_LIST.length);
        return DID_YOU_KNOW + ADVICE_LIST[randomIndex];
    }
}
