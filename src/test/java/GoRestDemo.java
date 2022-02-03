import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GoRestDemo {


    private static final String USERS_ENDPOINT = "/users";
    private static final String AUTHORIZATION_TOKEN = "ce497b923a87020b84bb23622c624b18f1f9218aca7108febc78745b5592d852";

    @BeforeMethod
    public void setUp() {
        baseURI = "https://gorest.co.in/public/v2/";
    }


    /**
     * creating a new user passing the json file as a payload to request
     * and passing "Authorization" header as Bearer token
     */
    @Test
    public void CreateUserswithToken() {
        String responseBpody = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + AUTHORIZATION_TOKEN)
                .body(new File("src/test/resources/JSONS/newGoRestUser.json"))
                .when().log().all()
                .post(USERS_ENDPOINT)
                .then().log().all()
                .extract().body().asString();
        System.out.println(responseBpody);
    }

    /**
     * using JsonPAth to get especific information, in this case to get the list of id's
     */
    @Test
    public void getListIds() {
        List<Object> listIds = given().log().all()
                .when().log().all()
                .get(USERS_ENDPOINT)
                .then().log().all()
                .statusCode(200)
                .extract().jsonPath().getList("id");

        listIds.forEach(id -> {
            System.out.println("id: " + id.toString());
        });
    }


    /**
     * validate JSON schema
     */
    @Test
    public void testJSONSchema() {
        System.out.println(
                given()
                        .when()
                        .get(USERS_ENDPOINT)
                        .then().log().all()
                            .body(JsonSchemaValidator.
                                matchesJsonSchema(new File("src/test/resources/JSONS/shema.json")))
                            .extract().body().asString()
        );
    }
}
