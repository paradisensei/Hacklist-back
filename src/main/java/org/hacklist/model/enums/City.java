package org.hacklist.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Neil Alishev
 */
public enum City {

    MOSCOW(1, "Москва", "Moscow", "Мск"),

    SPB(2, "Санкт-Петербург", "Saint-Petersburg", "Saint Petersburg", "Спб", "Питер", "SaintP"),

    KZN(3, "Казань", "Kazan", "Кзн"),

    EKB(4, "Екатеринбург", "Ekaterinburg", "Екб"),

    NOVOSIB(5, "Новосибирск", "Novosibirsk");

    private int priority;
    private String name;
    private String[] synonyms;

    City(int priority, String name, String... synonyms) {
        this.priority = priority;
        this.name = name;
        this.synonyms = synonyms;
    }

    public int getPriority() {
        return priority;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

}
