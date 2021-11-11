package scenarioSatu;

import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiGet {

    public static Integer userId;
    public static Integer id;
    public static String title;
    public static String body;

    RequestSpecification requestSpecification;

    @BeforeMethod
    public void beforeMethod() {
        requestSpecification = given().
                baseUri("https://jsonplaceholder.cypress.io").
                log().all();
    }

    @Test
    public void validateStatusCode() {
        Response response = given(requestSpecification).get("/posts").then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(200));
    }

    @Test(dependsOnMethods = "validateStatusCode")
    public void validateJsonData() {
        //using pojo classes
        //to make content-type as json , parser.json
        Data dt = given(requestSpecification).expect().defaultParser(Parser.JSON).
                when().
                get("/posts").as(Data.class);

        userId = dt.getUserId();
        System.out.println(userId);
    }
}
