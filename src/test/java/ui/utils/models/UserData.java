package ui.utils.models;

import ui.utils.testDataGenerator.UserDataGenerator;

public class UserData {

    UserDataGenerator userDataGenerator = new UserDataGenerator();

    private final String name = userDataGenerator.generateName();
    private final String surname = userDataGenerator.generateSurname();
    private final String email = userDataGenerator.generateEmail();
    private final String position = userDataGenerator.generatePosition();

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
