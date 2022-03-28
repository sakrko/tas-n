Feature: Sample API
  This feature covers few api functionalities for demoqa.com

  @REG
  Scenario Outline: Test APIs
    Given test data is loaded from <testCase>
    When user makes an api call
    Then status code is <statusCode>
    Examples:
      | testCase | statusCode |
      | API01    | 200        |
