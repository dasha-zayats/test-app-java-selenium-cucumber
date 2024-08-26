package ui.utils.helpers;

public class UserData {

    TestDataGenerator testDataGenerator = new TestDataGenerator();

    private final String name = testDataGenerator.generateName();
    private final String surname = testDataGenerator.generateSurname();
    private final String email = testDataGenerator.generateEmail();
    private final String position = testDataGenerator.generatePosition();

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getEmail(){
        return email;
    }

    public String getPosition(){
        return position;
    }
}
