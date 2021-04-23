package be.intecbrussel.healthy_goal.model;

import java.util.Random;

public class Conseil {

    private static Random random = new Random();
    private static final String DID_YOU_KNOW = "Saviez-vous ";
    private static final String[] ADVICE_LIST = {
            "qu'une bonne activité physique réduit le risque de contracter plusieurs types de cancer?",
            "que l'activité physique est bénéfique pour la santé des os et la qualité du sommeil?",
            "que l'activité physique améliore le fonctionnement du système immunitaire?",
            "que l'obésité augmente le risque de maladie grave due au COVID-19?",
            "qu'un IMC élevé reste un facteur de risque important pour contracter un COVID-19 sévère?",
            "que l'effet de l'IMC sur le système immunitaire est plus apparent chez les femmes?",
            "que la perte de poids module les paramètres du système immunitaire des patients infectés par le virus du VHC?",
            "que l'excès de poids crée une pression artérielle élevée (hypertension)?",
            "que l'excès de poids crée un cholestérol LDL élevé ou des taux élevés de triglycérides (dyslipidémie)?"
    };

    public String getConseil() {
        int randomIndex = random.nextInt(ADVICE_LIST.length);
        return DID_YOU_KNOW + ADVICE_LIST[randomIndex];
    }
}
