import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthTest {

    @Test
    public void basicAuth(){
        given().auth().basic("postman","password").
        when().get("https://postman-echo.com/basic-auth").then().log().all();
    }
}
