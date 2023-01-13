
Feature: User Verification
  @bookIt
  Scenario: verify information about logged user
    Given User logged Bookit api using "sbirddbj@fc2.com" and "asenorval"
    When User get the current user information from api
    Then verify status code should be 200