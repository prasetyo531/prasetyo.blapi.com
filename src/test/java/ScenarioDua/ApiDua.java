package ScenarioDua;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiDua {

    @BeforeMethod
    public void beforeMethod () {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://jsonplaceholder.cypress.io");
        requestSpecBuilder.setContentType(ContentType.JSON);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePost () throws IOException {
        String payload = "{\n" +
                "  \"title\": \"delectus aut autem\",\n" +
                "  \"body\": \"motorcycle\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = with().body(payload).post("/posts");
        response.getBody().prettyPrint();

        String title = response.path("title");
        String body = response.path("body");
        Integer userId = response.path("userId");
        Integer id = response.path("id");

        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialization into the `Employee` class
        Data data = objectMapper.readValue(payload, Data.class);

        System.out.println("\tis id equals?: " + title.equals(data.getTitle()));
        System.out.println("\tis id equals?: " + body.equals(data.getBody()));
        System.out.println("\tis id equals?: " + userId.equals(data.getUserId()));
    }
}
