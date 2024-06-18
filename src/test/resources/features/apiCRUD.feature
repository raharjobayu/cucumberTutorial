Feature: API CRUD Testing
  Scenario: Create a new user with valid data
    Given I set POST user service api endpoint
    When I send POST HTTP request to create a user with "6967499", "Deveshwar Abbott", "deveshwar_abbott12345@johnston.test", "female", "active"
    Then I receive valid HTTP response code 200
    And the response should contain the created user details

  Scenario: Create a new user with existing email
    Given I set POST user service api endpoint
    When I send POST HTTP request to create a user with "6967499", "Deveshwar Abbotts", "deveshwar_abbot@johnston.test", "female", "active"
    Then I receive valid HTTP response code 422

  Scenario: Create a new user without authorization
    Given I set POST user service api endpoint
    When I send POST HTTP request without authorization to create a user with "6967499", "Deveshwar Abbott", "deveshwar_abbott12345@johnston.test", "female", "active"
    Then I receive valid HTTP response code 401

  Scenario: Get user details
    Given I set GET user service api endpoint
    When I send GET HTTP request to get the created user with 6940095
    Then I receive valid HTTP response code 200
    And the response should contain the user details

  Scenario: Get user details with not existing user id
    Given I set GET user service api endpoint
    When I send GET HTTP request to get the created user with 99999
    Then I receive valid HTTP response code 404

  Scenario: Update user details
    Given I set PUT user service api endpoint
    When I send PUT HTTP request to update the user with 6940095
    Then I receive valid HTTP response code 200
    And the response should contain the updated user details

  Scenario: Update user details with not existing user id
    Given I set PUT user service api endpoint
    When I send PUT HTTP request to update the user with 999999
    Then I receive valid HTTP response code 404

  Scenario: Delete user
    Given I set DELETE user service api endpoint
    When I send DELETE HTTP request to delete the user with 6940096
    Then I receive valid HTTP response code 200

  Scenario: Delete user with not existing user id
    Given I set DELETE user service api endpoint
    When I send DELETE HTTP request to delete the user with 999999
    Then I receive valid HTTP response code 404