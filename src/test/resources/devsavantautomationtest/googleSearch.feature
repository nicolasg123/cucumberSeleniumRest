@Part3
Feature: test search google "I'm Feeling Lucky" string and click search button
  As a www.google.com user
  I want to search box and click on the "I'm Feeling Lucky" button
  So that I would be taken directly to the most relevant result

  Scenario: User is taken directly to the most relevant result
    Given I im in the google search page
    When I search for "Devsavant" with "I'm Feeling Lucky" button
    Then I am taken directly to the most relevant result
