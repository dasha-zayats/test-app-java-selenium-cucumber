package ui.utils.helpers;

import net.datafaker.Faker;

import java.util.Random;

public class TestDataGenerator {

    private final Faker faker;
    private final Random random;

    private static final String[] allowedUserPosition = {
            "Lead Test Engineer",
            "Senior Test Engineer",
            "Test Engineer",
            "Junior Engineer",
            "Lead Developer",
            "Senior Developer",
            "Test Developer",
            "Junior Developer",
            "Business Analyst"
    };

    public TestDataGenerator() {
        this.faker = new Faker();
        this.random = new Random();
    }

    public String generateName() {
        return faker.name().firstName();
    }

    public String generateSurname() {
        return faker.name().lastName();
    }

    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    public String generatePosition() {
        int index = random.nextInt(allowedUserPosition.length);
        return allowedUserPosition[index];
    }
}
