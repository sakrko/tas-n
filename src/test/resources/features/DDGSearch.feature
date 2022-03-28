Feature: DDG Search

  Scenario: DDG Search
    Given user is on ddg page
    When user searches for test
    Then user is able to see test1 in search field

  Scenario Outline: DDG Searches
    Given user is on ddg page
    When user searches for <phrase>
    Then user is able to see <phrase> in search field
    Examples:
      | phrase |
      | duck   |
      | can    |
      | go     |