Feature: BookStore API Create User Functionality
@wip
  Scenario: Create a user
    When user send a POST request to create user end point
    And User captures status code, userID, username and books information
    Then verifies status code username and userID is NOT null