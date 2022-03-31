@REG
Feature: Sample API
  This feature covers few api functionalities for reqres.in


  Scenario Outline: Test APIs
    Given test data is loaded from <testCase>
    When user makes an api call
    Then status code is <statusCode>
    And validate schema
    And validate response body
    Examples:
      | testCase | statusCode |
      | API01    | 200        |
      | API02    | 201        |
      | API03    | 200        |
      | API04    | 200        |



