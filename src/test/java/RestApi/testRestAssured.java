package RestApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class testRestAssured extends BaseRestAssured {
    @Test
    void checkFourDigitIntegers() {
        List<Integer> idList = given()
                .get("https://gorest.co.in/public/v1/users")
                .jsonPath().getList("data.id");
        given().get("https://gorest.co.in/public/v1/users").then().statusCode(200).statusLine("HTTP/1.1 200 OK");

        System.out.println("idList elements = " +idList);
        System.out.println("idList Size = " +idList.size());

        for (int i = 0; i < idList.size(); i++) {
            int S = countDigit(idList.get(i));
            /****************        VERIFY 4 DIGITS BELOW            ***********/
            Assert.assertEquals(S, 4, "not 4 digits");   // Verify 4 Digits
            System.out.println(idList.get(i) + "= data_id[" + i + "] is " + S + " digits.");
        }
    }


    @Test
    void postRequest(){
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification postRequest = RestAssured.given();

        postRequest
                .queryParam("email",email1)
                .queryParam("name",name)
                .queryParam("gender",gender)
                .queryParam("status",status);

        postRequest.header("Accept","application/json");
        postRequest.header("Authorization","Bearer " + accessToken)
                .header("Accept","application/json");

        Response response = postRequest.post("/public/v1/users");

        int statusCode = response.getStatusCode();
        System.out.println("Response status Code is " + statusCode);

        response.prettyPrint();
        response.then().statusCode(201).assertThat()
                .body("data.email", equalTo(email1))
                .body("data.name",equalTo(name))
                .body("data.gender",equalTo(gender))
                .body("data.status",equalTo(status));

        Response response1 = postRequest.post("/public/v1/users");
        response1.prettyPrint();
        response1.then().statusCode(422).assertThat();
    }
}
