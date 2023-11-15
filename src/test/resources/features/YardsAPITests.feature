Feature: Validating Yard APIs

  @TC-101 @smoke @regression
  Scenario: Validating Create Yard API call
    Given user create yard with post api call
      | yardName | Mindtek 9 - Club |
      | city     | Chicago          |
    When user gets created yard with get api call
    Then user validates yard details matches with created yard

  @TC-102 @smoke @regression
  Scenario Outline: Validating Create Yard API call with invalid body
    Given user create yard with post api call "<scenario>"
    Then user validates status code <statusCode>
    And user validates "<errorMessage>" error message
    Examples:
      | scenario                     | statusCode | errorMessage                                                     |
      | without yard name            | 400        | This field is required.                                          |
      | name with special characters | 400        | Enter the correct data (leters, digits and '- & \| . ()' symbols |
#      | with closed status           | 400        | \"closed\" is not a valid choice.                                |

  @TC-103 @smoke @regression
  Scenario Outline: Validating Get Yards query parameters
    Given user gets yards with get yard api call with limit <limit>
    Then user validates response includes <limit> yards
    Examples:
      | limit |
      | 3     |
      | 5     |
      | 100   |
      | 0     |

  @TC-104 @smoke @regression
  Scenario: Validating Get Yards api call without authorization token
    Given user gets yards with get yard api call without authorization token
    Then user validates status code 401
    And user validates "Authentication credentials were not provided." authorization error message

  @TC-105 @smoke @regression
  Scenario: Validating updated yard api call
    Given user create yard with post api call
      | yardName | New Mindtek 9 |
      | city     | Schaumburg    |
    When user updates yard name with patch api call
      | yardName | Old Mindtek 9 |
    Then user validates status code 200
    And user validates yard name is updated

  @TC-105 @smoke @regression
  Scenario: Validating updated yard api call
    Given user create yard with post api call
      | yardName | New Mindtek 9 |
      | city     | Schaumburg    |
#    When user updates yard name with patch api call with no name
    Then user validates status code 400
#    And user validates "This field may not be blank." update error message


