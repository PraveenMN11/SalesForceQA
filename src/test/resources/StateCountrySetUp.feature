@StateSetUp
Feature: Country-State SetUp

  Scenario Outline: Contact SetUp with "<userType>" role
    Given I am logged in as "<userType>"
    When I want to Add States in SetUp
    And I will navigate to SetUp
    And I will navigate to "State"
    And i will configure the data for states
    Then the states are added successfully
    And i logged out of application
    
    Examples: 
      | userType |
      | SysAdmin |
   