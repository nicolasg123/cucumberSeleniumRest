# Test Cucumber, Selenium 

This project contains a structure ready to implement automated test cases using Cucumber and Selenium along with the following components:

- JUnit Assert
- Cucumber
- Selenium Webdriver
- Chrome Driver
- java.net.http
- org.json.simple

## Setting up yourself

- You need a personal Gitlab account: https://gitlab.com/users/sign_up
- Make sure you have JDK 11 or newer installed in your environment
- Maven 3.3 or newer is also required
- You should have Chrome or Chromium installed in your system
- Follow this advice in order to install **chromedriver**:
    1. Chromedriver has to match the Chrome/Chromium version in your system. You can download it from: [https://chromedriver.chromium.org/downloads]
    2. Unzip the binary file and place it under `/usr/local/bin`
        - Ultimately, it can be located under any directory that is part of the `$PATH` or the directory where it has been extracted should be added to the `$PATH` environment variable
        - Use of `System.setProperty("webdriver.chrome.driver", "path-to-chromedriver");` is **discouraged** as long as your project may not run in other systems
- Fork this project into your personal Gitlab account
- Clone the project from your persoal Gitlab repository into your local environment
- Once you have cloned the project execute `mvn package` command from project root directory in order for the dependencies to be resolved
- Create a new branch and name it with your given name and surname using snake case

## Test

### Part 1

1. Go to http://worldtimeapi.org/pages/examples
2. Check how `http://worldtimeapi.org/api/timezone[/:area][/:location][/:region][.json|.txt]` endpoint works
    - Define a test scenario where:
        - The user obtains the list of time zones
        - Then takes the time zone in the exact 45th position, remember Java arrays are zero-indexed
        - Then gets the current time for the time zone that was obtained in the previous step
        - Assert the response code
        - Assert some of the elements of the response content using **regular expressions**
    - Write that scenario using Gherkin language in the `worldtimeapi.feature` file
3. Check how `http://worldtimeapi.org/api/ip[/:ipv4][.json|.txt]` endpoint works    
    - Define two **negative** scenarios
    - Write them in `worldtimeapi.feature` feature file using Gherkin language and `Scenario Outline` clause
    - Assert the response code
    - Assert some of the elements of the response content using **regular expressions** that work for both scenarios
4. Include a description for the feature file and for each of the scenarios

### Part 2

1. Implement the test steps in the `StepDefinitions.java` file
2. All the tests should run and show results when executing `mvn test` command
3. Add a mechanism to run only the tests in the part 1 but not the tests of the part 3
4. Add a mechanism to run only the tests in the `Scenario Outline` clause

### Part 3

1. In a separated feature file create a new test that covers the following user story:
```
As a www.google.com user
I want to type a keyword in the search box and click on the "I'm Feeling Lucky" button
So that I would be taken directly to the most relevant result
```
2. Add a description to the test and to the scenario
3. Use "Devsavant" as keyword for the seach
4. Implement the test in a separated `*.java` class.  Use Selenium and chromedriver for the browser automation
5. In the step that performs the keyword search use a dynamic step definition name so that the same step would work with any keyword that is sent from the feature file
6. Implement a screen capture of the browser when the Devsavant website is shown
7. The test should run and show results along with the other tests when executing `mvn test` command
8. Add a mechanism to run only the Devsavant website test

## To finish up

- Modify this `README.md` file in order to add the following information at the top of the file:
    - Your given name, surname and email
    - How to run the Part 1 tests only
    - How to run the Scenario Outline tests only
    - How to run the Devsavant website test only
    - The expected location of the file containing the screen capture of the Devsavant website
    - Any other information you consider relevant from your implementation of this challenge
- Push your work to the repo
- Create a pull request from your personal repo to this project
- Send an email with a link to the project in your personal repo. Make sure it is set to public.

Best of luck!

Running tests

one test only mvn test -Dcucumber.filter.tags="@Part1"

Run the Scenario Outline: mvn test -Dcucumber.filter.tags="@ScenarioOutline"

Run website test only: mvn test -Dcucumber.filter.tags="@Part3"

captured image  website is under target folder
