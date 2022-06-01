package devsavantautomationtest;


import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import io.cucumber.java.en.And;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class StepsTest {

    private final String apiBaseUrl = "http://worldtimeapi.org/api";
    private JSONArray timeZonesList;
    private HttpClient client = HttpClient.newHttpClient();
    private HttpResponse<String> response;
    private JSONParser JSONParser = new JSONParser();

    @Given("I get the list of time zones")
    public void getListOfTimeZones() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiBaseUrl + "/timezone"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            timeZonesList = (JSONArray) JSONParser.parse(response.body());
        } catch (IOException | InterruptedException | ParseException e) {
            throw new Exception("error parsing response" + e.getMessage());
        }
    }

    @When("I take the timezone in the {int}th/nd/rd/st position")
    public void getTheTimeZoneInThePosition(int position) throws Exception {
        var areaLocationRegion = timeZonesList.get(position - 1);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiBaseUrl + "/timezone/" + areaLocationRegion))
                .GET()
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch ( IOException |  InterruptedException  e) {
            throw new Exception("error parsing response" + e.getMessage());
        }
    }

    @Then("I see the response code {int}")
    public void assertTheResponseCode(int expectedCode) {
        assertEquals(expectedCode, response.statusCode());
    }

    @And("the datetime is in the correct format")
    public void isDatetimeIsInTheCorrectFormat() throws Exception {
        var dateTimeRegex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?(([+-]\\d{2}:\\d{2})|Z)?$";
        try {
            JSONObject responseBody = (JSONObject) JSONParser.parse(response.body());
            assertAll("the datetime fields",
                    () -> assertTrue(responseBody.get("datetime").toString().matches(dateTimeRegex)),
                    () -> assertTrue(responseBody.get("utc_datetime").toString().matches(dateTimeRegex)));
        } catch (final ParseException e) {
            throw new Exception("error parsing response" + e.getMessage());
        }

    }

    @Given("I get the world time from {string} address")
    public void getTheWorldTimeFromIpAddress(final String ip) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiBaseUrl + "/ip/" + ip))
                .GET()
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException  e) {
            throw new Exception("error parsing response" + e.getMessage());
        }
    }

    @And("the {string}")
    public void assertTheMessage(final String message) {
        assertTrue(response.body().matches(".*" +message+ ".*"));
    }
}

