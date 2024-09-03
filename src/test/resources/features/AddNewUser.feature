 Feature: Add new user page and its features work correctly

   Background: The "Add new user" page is opened
     Given the user is on the main page
     And the user navigates to the "add new user" page

   Scenario: Add new user - page
     Then the title is displayed on the "add new user" page
     And the "Test Application" link is displayed on the "add new user" page
     And the "Add new user" text is displayed on the "add new user" page
     And the "Name" text field is displayed on the "add new user" page
     And the "Surname" text field is displayed on the "add new user" page
     And the "Email" text field is displayed on the "add new user" page
     And the "Position" dropdown is displayed on the "add new user" page
     And the "Create user" button is present and active on the "add new user" page
     And the copyright statement is displayed on the "add new user" page

   Scenario Outline: Add new user - <fieldName> details missed
     When the user fills in the user data missing <fieldName>
     And the user clicks the "Create user" button on the "add new user" page
     Then the "Fill in all the details" error appears on the "add new user" page
     Examples:
       | fieldName |
       | Name      |
       | Email     |

   Scenario: Add new user
     When the user fills in the user data
     And the user clicks the "Create user" button on the "add new user" page
     Then the "main" page is opened
     And the user for the "Add new user" scenario is in the "Users" table

   Scenario: Navigate from the "Add new user" page to the main page
     When the user clicks the "Test Application" link
     Then the "main" page is opened