@part1
Feature: Get current time for a timezone or ipv4 address
  As a user of worldtimeapi
  I want consume the time service by timezone or ipv4 address
  So that I would know the time for that specific location

  Scenario: Get current time for a timezone with region from a list of valid timezones
    Given I get the list of time zones
    When I take the time zone in the 45th position
    Then I see the response code 200
    And the datetime is in the correct format

  @ScenarioOutline
  Scenario Outline: Get time from a specific ip address
    Given I get the world time from "<ip>" address
    Then I see the response code <code>
    And the "<message>"

    Examples:
      | ip        | code | message               |
      | 8.8.8.8.8 | 404  | malformed ip          |
      | .         | 500  | Internal Server Error |

