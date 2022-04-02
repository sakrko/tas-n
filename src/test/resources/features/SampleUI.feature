@REG
Feature: Sample UI
  This feature covers few functionalities in demoqa.com page

  Background:
    Given the demoqa home page is displayed

  Scenario Outline: Search books in book store application
    Given the "bookStoreApplication" page is displayed
    When the user searches for <book>
    Then the result list has the <book>
    Examples: Books name
      | book              |
      | Git Pocket Guide  |
      | You Don't Know JS |

  Scenario: Search book which is not available
    Given the "bookStoreApplication" page is displayed
    When the user searches for The Great Gatsby
    Then no such book is found in result list

  Scenario Outline: Submit form
    Given the "elements" page is displayed
    When the user fills the form with <form> data
    And submits form
    Then submitted data is shown
    Examples:
      | form |
      | TC01 |
      | TC02 |