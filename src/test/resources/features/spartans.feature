Feature: Testing Spartan Web Application
  @spartan
  Scenario: SpartanFLOW
    Given User create a new Spartan
    And User gets the Spartan and verify it is created
    Then user delete the Spartan
    And user verify it is deleted

    Scenario: viewing any random Spartan data info
      When User clicks on any random Spartan and view data
      Then verify spartan data should be same with API
