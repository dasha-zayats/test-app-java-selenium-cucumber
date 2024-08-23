package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class APITests {

    private WebDriver driver;
    private int idNewUser;
    private final String baseURI = "http://localhost:8080";

    @Test(priority = 1)
    public void getAllUsers() {
        Response response = given()
                .baseUri(baseURI)
                .when()
                .get("/users")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

    @Test(priority = 2)
    public void crateNewUser() {
        Response response = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"darya\",\"surname\": \"zayats\",\"email\": \"darya.zayats@test.com\",\"position\": \"Test Engineer\"}")
                        .when()
                        .post("/users")
                        .then()
                        .assertThat()
                        .statusCode(202)
                        .body("id",notNullValue())
                        .extract()
                        .response();

        idNewUser = response.jsonPath().getInt("id");
    }

    @Test(priority = 3)
    public void getUserById() {
        Response response = given()
                .baseUri(baseURI)
                .pathParam("userId", idNewUser)
                .when()
                .get("/users/{userId}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(idNewUser))
                .body("name", equalTo("darya"))
                .body("surname", equalTo("zayats"))
                .body("email", equalTo("darya.zayats@test.com"))
                .body("position", equalTo("Test Engineer"))
                .extract()
                .response();
    }

    @Test(priority = 4)
    public void updateUser() {
        Response response = given()
                .baseUri(baseURI)
                .pathParam("userId", idNewUser)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Alex\",\"surname\": \"Zayats\",\"email\": \"alex.zayats@test.com\",\"position\": \"Lead Developer\"}")
                .when()
                .patch("/users/{userId}")
                .then()
                .assertThat()
                .statusCode(202)
                .body("id",notNullValue())
                .extract()
                .response();
    }

    @Test(priority = 5)
    public void getUpdatedUserById() {
        Response response = given()
                .baseUri(baseURI)
                .pathParam("userId", idNewUser)
                .when()
                .get("/users/{userId}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(idNewUser))
                .body("name", equalTo("Alex"))
                .body("surname", equalTo("Zayats"))
                .body("email", equalTo("alex.zayats@test.com"))
                .body("position", equalTo("Lead Developer"))
                .extract()
                .response();
    }

    @Test(priority = 6)
    public void deleteUser() {
        Response response = given()
                .baseUri(baseURI)
                .pathParam("userId", idNewUser)
                .when()
                .delete("/users/{userId}")
                .then()
                .assertThat()
                .statusCode(202)
                .body("id", notNullValue())
                .extract()
                .response();
    }
}
