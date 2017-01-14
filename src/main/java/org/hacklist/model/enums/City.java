package org.hacklist.model.enums;

/**
 * @author Neil Alishev
 */
public enum City {
    Moscow("Москва", "Moscow", "Мск"),
    SaintPetersburg("Санкт-Петербург", "Saint-Petersburg", "Saint Petersburg", "Спб", "Питер", "SaintP"),
    Kazan("Казань", "Kazan"),
    Ekaterinburg("Екатеринбург", "Ekaterinburg"),
    Novosibirsk("Новосибирск", "Novosibirsk");

    private String name;
    private String[] synonyms;

    City(String name, String... synonyms) {
        this.name = name;
        this.synonyms = synonyms;
    }

    public String getName() {
        return name;
    }

    public String[] getSynonyms() {
        return synonyms;
    }
}
