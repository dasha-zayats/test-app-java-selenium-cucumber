Feature: Check interface of the main page

  Background: Main page is opened
    Given the user is on the main page

  Scenario: Check the content on the main page
    Then the title is displayed on the "main" page
    And the "Test Application" link is displayed on the "main" page
    And the "List of test users" text is displayed on the "main" page
    And the "Add new users" button is present and active on the "main" page
    And the header of the "Users" table is displayed on the "main" page
    And the copyright statement is displayed on the "main" page

  Scenario: Navigation to the "Add new user" page
    Then the "Add new users" button is present and active on the "main" page
    When the user clicks the "Add new user" button on the "main" page
    Then the "add new user" page is opened