package com.videogame.cucumber.stepdefs;

import com.videogame.stepinfo.VideoGameSteps;
import com.videogame.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.CoreMatchers.equalTo;

public class VideoGameTest {

    static int id = 1 + TestUtils.getRandomValueInt();
    static String name = ("Spider-Man");
    static String releaseDate = ("2021-07-15T19:08:27.609Z");
    static int reviewScore = (78);
    static String category = ("FunGame");
    static String rating = ("String");

    @Steps
    VideoGameSteps videoGameSteps;



    @When("^I create a new videogame by providing the information name \"([^\"]*)\" releaseDate \"([^\"]*)\" rating \"([^\"]*)\"$")
    public void iCreateANewVideogameByProvidingTheInformationNameReleaseDateRating(String name, String releaseDate, String rating) {
        videoGameSteps.createNewVideoGames(id, name, releaseDate, reviewScore, category, rating).log().all().statusCode(200).extract().response()
                .body().jsonPath();

    }
   @Then("^I verify the videogame is created$")
    public void iVerifyTheProductIsCreated() {
        videoGameSteps.getVideoGameByID(id).log().all().statusCode(200);
    }

    @When("^I send GET request to videogames endpoint with Id \"([^\"]*)\",I should received the videogame information")
    public void iSendGETRequestToVideogameEndpointWithIdIShouldReceivedTheVideogameInformation(String videogameid )  {
        videoGameSteps.getVideoGameByID(id).log().all().statusCode(200);

    }
    @When("^I update a created videogame by providing the new name\"([^\"]*)\" category and rating$")
    public void iUpdateACreatedVideogameByProvidingTheNewNameCategoryAndRating(String name)  {
        id = id;
        name = name + "_new";
        releaseDate = releaseDate;
        reviewScore = reviewScore + 1;
        category = category + "_new";
        rating = rating + "_new";

        videoGameSteps.updateVideoGamesById(id, name, releaseDate, reviewScore, category, rating).statusCode(200).log().all();
    }

    @Then("^I verify the videogame is updated")
    public void iVerifyTheVideogameIsUpdated() {
        videoGameSteps.getVideoGameByID(id).body("id", equalTo(id));
    }


    @When("^I delete a created videogame ,they must get back a valid status code is (\\d+)$")
    public void iDeleteACreatedVideogameTheyMustGetBackAValidStatusCodeIs(int videogameid){
        videoGameSteps.deleteVideoGameById(id).log().all().statusCode(200);
    }
    @When("^User sends a GET requets to videogames endpoint, they must get back a valid status code 200$")
    public void userSendsAGETRequetsToVideogamesEndpointTheyMustGetBackAValidStatusCode() {
        videoGameSteps.getAllVideoGames().log().all().statusCode(200);
    }

}




