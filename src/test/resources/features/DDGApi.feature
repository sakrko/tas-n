Feature: DDG API

  Test feature for API requests

  @REG
  Scenario: One Search api
    Given the DDG API is queried with test
    Then the response status code is 200

  Scenario Outline: More than one search api
    Given the DDG API is queried with <phrase>
    Then the response status code is <code>
    Examples: test
      | phrase | code |
      | duck   | 200  |
      | can    | 200  |
      | go     | 200  |