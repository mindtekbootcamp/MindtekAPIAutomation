Feature: Validating Booking API calls

  @TC-110 @smoke @regression
  Scenario: Validating create booking API call
    Given user create booking with post api call with data
      | firstname       | John       |
      | lastname        | Doe        |
      | totalprice      | 111        |
      | depositpaid     | false      |
      | checkin         | 2018-12-12 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    When user gets created booking with get api call
    Then user validates created data matches with get response

