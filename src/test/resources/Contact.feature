@Contact
Feature: Contact SetUp

  Scenario Outline: Contact SetUp with "<userType>" role
    Given I am logged in as "<userType>"
    When I want to create a contact
    And I enter the contact information    
    And I enter the address information
    And I enter the additional information
    And I enter the description information
    And i click to save a record
    Then an contact is created successfully
    
   Examples: 
      | userType |
      | SysAdmin |
    #  | AnsellSalesMed |
    #  | AnsellSalesMed_Ind |
     # | AnsellCustomerService |
    #  |AnsellMarketing|