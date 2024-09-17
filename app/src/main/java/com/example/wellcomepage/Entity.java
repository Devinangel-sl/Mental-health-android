package com.example.wellcomepage;

public class Entity {
    private String property1;
    private String property2;
    private String description;

    public Entity(String property1, String property2, String description) {
        this.property1 = property1;
        this.property2 = property2;
        this.description = description;
    }

    public String getProperty1() {
        return property1;
    }

    public String getProperty2() {
        return property2;
    }

    public String getDescription() {
        return description;
    }
}
