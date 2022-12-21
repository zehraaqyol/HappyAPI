Feature: Get All Books Info

  Scenario: Verify the number of books
    When User sends GET request to receive all books info
    Then Verifies that we have 8 books in response