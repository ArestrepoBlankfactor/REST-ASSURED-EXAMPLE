import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.Util;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class TestRestAssuredDemo extends Util {
    private static final String EMAIL_OR_USERNAME_MISSING = "Missing email or username";
    private static final String USERS_ENDPOINT = "/users";
    private static final String REGISTER_ENDPOINT = "/register";
    public static Logger LOGGER = LoggerFactory.getLogger(TestRestAssuredDemo.class);


    @BeforeClass
    public void setUp() {
        baseURI = "https://reqres.in/api/";
    }


    /**
     * test get method and validate one item
     */
    @Test
    public void testGuestusers() {
        given()
                .when()
                    .get(USERS_ENDPOINT)
                .then()
                    .statusCode(200)
                    .body("data[1].first_name", equalTo("Janet")).log().body()
                    .extract().body().asString();
    }

    /**
     * test POST method sending the payload as a map
     */
    @Test
    public void testPostNewUser() {
        Map<String, Object> mapNewUser = new HashMap<String, Object>();
        mapNewUser.put("name", "alejandro");
        mapNewUser.put("job", "developer");

        String postBodyResponse = given()
                .log().all().body(mapNewUser.toString())
                .when()
                .post(USERS_ENDPOINT)
                .then().log().all()
                .statusCode(201)
                .extract().body().asString();

        LOGGER.info(postBodyResponse);
    }

    /**
     * test POST method sending the payload as a map
     */
    @Test
    public void testPutUser() {
        final String ID_USER_UPDATE = "/2";
        Map<String, String> updateUser = new HashMap<String, String>();
        updateUser.put("name", "morpehus");
        updateUser.put("job", "zion resident");
        String putBodyResponse = given().log().all()
                .body(updateUser.toString())
                .when()
                .put(USERS_ENDPOINT + ID_USER_UPDATE)
                .then().log().all()
                .statusCode(200)
                .extract().body().asString();

        LOGGER.info(putBodyResponse);
    }

    /**
     * test Get with queryParameter in the url
     */
    @Test
    public void testGetWithQueryParameter() {
        Map<String, String> queryParameter = new HashMap<String, String>();
        queryParameter.put("delay", "10");

        String postBodyResponse = given().log().all()
                .queryParams(queryParameter)
                .when()
                .get(USERS_ENDPOINT)
                .then().log().body()
                .extract().body().asString();

        LOGGER.info(postBodyResponse);
    }

    /**
     * test post method, successfully login
     * validate that response contains token and the ID i
     */
    @Test
    public void testPostRegister() {
        Map<String, String> emailAndPasswrod = new HashMap<String, String>();
        emailAndPasswrod.put("email", "eve.holt@reqres.in");
        emailAndPasswrod.put("password", "pistol");
        Gson gson = new Gson();
        String json = gson.toJson(emailAndPasswrod);

        String postBodyResponse = given().log().all()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(REGISTER_ENDPOINT)
                .then().log().all()
                .assertThat()
                .body("id", is(4))
                .and()
                .body(containsString("token"))
                .extract().body().asString();

        LOGGER.info(postBodyResponse);
    }

    /**
     * validate status code is 400(bad request) when send the request without correct body format
     */
    @Test
    public void testBadRequest() {
        String PosBadRequet = given()
                .when()
                .body("")
                .post(REGISTER_ENDPOINT)
                .then().statusCode(400)
                .body("error", equalTo(EMAIL_OR_USERNAME_MISSING))
                .extract().response().asString();

        LOGGER.info(PosBadRequet);
    }

    /**
     * delete method
     */
    @Test
    public void testDelete() {
        String deleteResponse = given().
                when()
                .delete(USERS_ENDPOINT + "/2")
                .then()
                .statusCode(204).extract().body().asString();
        LOGGER.info(deleteResponse);

    }

    /**
     * validate status code 404 , Resource not found
     */
    @Test
    public void testResourceNotFound() {
        String PosBadRequet = given()
                .when()
                .get("unknow/23")
                .then().statusCode(404)
                .extract().response().asString();

        LOGGER.info(PosBadRequet);
    }
}
