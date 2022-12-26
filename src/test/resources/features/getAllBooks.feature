Feature: Get All Books Info

  Scenario: Verify the number of books
    When User sends GET request to receive all books info
    Then Verifies that we have 8 books in response

  @book
  Scenario Outline: Verify response body info of the first book

    When User sends GET request to receive first book info
    Then Verify that we have correct info about fisrt book which we selected "<key>" "<value>"
    Examples:
      | key          | value                                                                     |
      | isbn         | 9781449337711                                                             |
      | title        | Designing Evolvable Web APIs with ASP.NET                                 |
      | subTitle     | Harnessing the Power of the Web                                           |
      | author       | Glenn Block et al.                                                        |
      | publish_date | 2020-06-04T09:12:43.000Z                                                  |
      | publisher    | O'Reilly Media                                                            |
      | pages        | 238                                                                       |
      | description  | Design and build Web APIs for a broad range of clients—including browsers and mobile devices—that can adapt to change over time. This practical, hands-on guide takes you through the theory and tools you need to build evolvable HTTP services with Microsoft|
      | website      | http://chimera.labs.oreilly.com/books/1234000001708/index.html            |

    @zippo
    Scenario: Verify zippopotam.us web site api info
      When User sends GET request to receive info by endpoint
      Then verify response body is correct

