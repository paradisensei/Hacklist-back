package org.hacklist.model.enums;

/**
 * @author Neil Alishev
 */
public enum City {
    Moscow("Москва", 1, "Moscow", "Мск"),
    SaintPetersburg("Санкт-Петербург", 2, "Saint-Petersburg", "Saint Petersburg", "Спб", "Питер", "SaintP"),
    Kazan("Казань", 3, "Kazan"),
    Ekaterinburg("Екатеринбург", 4, "Ekaterinburg"),
    Novosibirsk("Новосибирск", 5, "Novosibirsk");

    private String name;
    private int priority;
    private String[] synonyms;

    City(String name, int priority, String... synonyms) {
        this.name = name;
        this.priority = priority;
        this.synonyms = synonyms;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public String[] getSynonyms() {
        return synonyms;
    }
}
