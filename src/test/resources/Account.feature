@Account
Feature: Account SetUp

  Scenario Outline: Account SetUp with "<userType>" role for End User Record Type
    Given I am logged in as "<userType>"
    When I want to create an Account
    And I select the "<RecordType>"
    | RecordType |
    | End User   |
    | Standard   |
    | Distributor|
    |Grouping Entity|
    |Global Accounts|
    And i click to Next Button
    And I enter the Ansell Account Management Information
    And I enter the Address and Contact information
    And I enter the Healthcare specific fields information
    And i click on save
    Then an Account with end user record type is created successfully

    Examples: 
      | userType |
  #    | SysAdmin |
      | AnsellSalesMed |
   #   | AnsellSalesMed_Ind |
  #    | AnsellCustomerService |
  #    |AnsellMarketing|