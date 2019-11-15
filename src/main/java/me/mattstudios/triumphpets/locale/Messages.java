package me.mattstudios.triumphpets.locale;

public enum Messages {

    TEST(
            "&7Using &aEnglish &7messages!",
            "Teste"
    );

    private String english;
    private String portuguese;

    Messages(String english, String portuguese) {
        this.english = english;
        this.portuguese = portuguese;
    }

    public String getEnglish() {
        return english;
    }

    public String getPortuguese() {
        return portuguese;
    }

}
