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

  @TC-111 @regression
  Scenario: Validating update booking API call
    Given user create booking with post api call with data
      | firstname       | John       |
      | lastname        | Doe        |
      | totalprice      | 111        |
      | depositpaid     | false      |
      | checkin         | 2018-12-12 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    When user updates created booking with put api call with data
      | firstname | Srinivasan |
      | checkin   | 2018-12-15 |
    When user gets created booking with get api call
    Then user validates updated data matches with get response


    @TC-112 @regression
      Scenario: Validating delete booking API call
      Given user create booking with post api call with data
        | firstname       | John       |
        | lastname        | Doe        |
        | totalprice      | 111        |
        | depositpaid     | false      |
        | checkin         | 2018-12-12 |
        | checkout        | 2019-01-01 |
        | additionalneeds | Breakfast  |
      When user deletes booking with delete api call
      And user gets created booking with get api call
      Then user validates 404 status code





