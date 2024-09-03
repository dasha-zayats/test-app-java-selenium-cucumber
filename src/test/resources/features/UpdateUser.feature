Feature: Update user page and its features work correctly

  Background: Main page is opened
    Given the user is on the main page
    And new user is added to the "Users" table

  Scenario: Update user - page
    When the user clicks the "update user" button against the new user created
    Then the "update user" page is opened
    And the title is displayed on the "update user" page
    And the "Test Application" link is displayed on the "update user" page
    And the "Add new user" text is displayed on the "update user" page
    And the "Name" text field is displayed on the "update user" page
    And the "Surname" text field is displayed on the "update user" page
    And the "Email" text field is displayed on the "update user" page
    And the "Position" dropdown is displayed on the "update user" page
    And the "Update user" button is present and active on the "update user" page
    And the "Cancel" button is present and active on the "update user" page
    And the copyright statement is displayed on the "update user" page

  Scenario Outline: Update user <fieldName> details
    When the user clicks the "update user" button against the new user created
    Then the "update user" page is opened
    When the user changes "<fieldName>"
    And the user clicks the "Update user" button on the "update user" page
    Then the "main" page is opened
    And the user for the "Update user" scenario is in the "Users" table
    Examples:
      | fieldName |
      | Name      |
      | Surname   |
      | Email     |
      | Position  |

  Scenario: Update user details - cancel update
    When the user clicks the "update user" button against the new user created
    Then the "update user" page is opened
    When the user clicks the "Cancel" button on the "update user" page
    Then the "main" page is opened

  Scenario: Navigate from the "Update user" page to the main page
    When the user clicks the "Test Application" link
    Then the "main" page is opened