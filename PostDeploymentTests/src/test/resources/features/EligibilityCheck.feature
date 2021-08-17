Feature: Validating Credit card eligibility check endpoint of a bank


Scenario Outline: Validate credit card eligibility check for eligible users
  Given I am an user with name "<username>"
  When I perform an eligibility check for a credit card
  Then I should get a status code indicating "success"
  Then I should get "<cardsList>" as my available list of credit cards
  Examples:
  |username|cardsList|
  |Boris|C1|
  |Theresa|C2|
  |Angela|C1,C2|

Scenario: Validate credit card eligibility check for random users
  Given I am a random user
  When I perform an eligibility check for a credit card
  Then I should get a status code indicating "success"
  Then I should not be eligible for any credit cards


Scenario Outline: Validate credit card eligibility check for invalid input
  Given I am a random user
  When I perform an eligibility check for a credit card without one "<mandatoryField>"
  Then I should get a status code indicating "failure"
  Examples:
  |mandatoryField|
  |address|
  |name|
  |email|


  