@regression @Login
Feature: Login

  Scenario Outline:  Login to SALESFORCE with "<userType>" role
    Given I am logged in as "<userType>"
    When SalesForce home page appears
    And i logged out of application
    
    Examples: 
      | userType |
      | SysAdmin |
      | AnsellSalesMed |
      | AnsellSalesMed_Ind |
      | AnsellCustomerService |
      |AnsellMarketing|