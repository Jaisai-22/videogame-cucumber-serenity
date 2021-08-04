package com.videogame.stepinfo;

import com.videogame.constant.EndPoints;
import com.videogame.model.VideoGamePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class VideoGameSteps {

    @Step("Creating New Video Games with id:{0},name:{1}, releaseDate:{2}, reviewScore:{3}, category:{4},rating:{5}")
    public ValidatableResponse createNewVideoGames(int id, String name, String releaseDate, int reviewScore, String category,
                                                   String rating) {

        VideoGamePojo videoGamePojo= new VideoGamePojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(videoGamePojo).accept("application/json")
                .when()
                .post(EndPoints.CREAT_NEW_VIDEOGAME)
                .then();

    }

    @Step("Getting video game with information with id")
    public ValidatableResponse getVideoGameByID(int videogameid ) {
        return SerenityRest.rest()
                .given().accept("application/json")
                .pathParam("id", videogameid).accept("application/json")
                .log().all()
                .when()
                .get(EndPoints.GET_SINGLE_VIDEOGAME_BY_ID)
                .then();
    }
    @Step("updating Video Games with id:{0} name:{1}, releaseDate:{2}, reviewScore:{3}, category:{4},rating:{5}")
    public ValidatableResponse updateVideoGamesById(int id, String name, String releaseDate, int reviewScore, String category,
                                                    String rating) {

        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id).accept("application/json")
                .body(videoGamePojo).accept("application/json")
                .when()
                .put(EndPoints.UPDATE_VIDEOGAME_BY_ID)
                .then();
    }

    @Step("Deleting vediogame information with product Id")
    public ValidatableResponse deleteVideoGameById(int videogameid) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", videogameid).accept("application/json")
                .log().all()
                .when()
                .delete(EndPoints.DELETE_VIDEOGAME_BY_ID)
                .then();
    }


    @Step("Getting all video games information")
    public ValidatableResponse getAllVideoGames() {
        return SerenityRest.rest()
                .given().accept("application/json")
                .when()
                .get(EndPoints.GET_ALL_VIDEOGAMES)
                .then();
    }

}
