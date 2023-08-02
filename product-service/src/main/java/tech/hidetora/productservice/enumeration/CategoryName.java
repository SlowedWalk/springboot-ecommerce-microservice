package tech.hidetora.productservice.enumeration;

public enum CategoryName {
    ELECTRONICS("Electronics appliances"),
    FASHION("Fashion accessories"),
    HEALTH("Health and beauty"),
    HOME("Home and living"),
    SPORTS("Sports and outdoors"),
    TOYS("Toys and games"),
    OTHER("Other");

    private String name;

    CategoryName(String name) {
        this.name = name;
    }
}
