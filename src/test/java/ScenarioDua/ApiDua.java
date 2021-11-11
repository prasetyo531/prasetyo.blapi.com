package ScenarioDua;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiDua {

    public static String title;
    public static String body;
    public static String userId;

    @Test
    public void checkTipeData() throws MalformedURLException {
        RestAssured.baseURI = "https://jsonplaceholder.cypress.io";
        RequestSpecification httpRequest = RestAssured.given();

        JsonObject bodyraw = new JsonObject();
        bodyraw.addProperty("title", "recommendation");
        bodyraw.addProperty("body", "test123");
        bodyraw.addProperty("userId", "12");
        httpRequest.body(bodyraw.toString());
        Response response = httpRequest.post("/posts");

        ResponseBody body = response.getBody();
        System.out.println("response is" + ((ResponseBody) body).prettyPrint());

//        Assert.assertEquals(response.getStatusCode(),201);
//
//        Data dt = given().expect().defaultParser(Parser.JSON).
//                when().
//                get("/post").as(Data.class);
//
//        //title = dt.getTitle();
//        System.out.println("asdad :"+dt.getTitle());
    }
}
