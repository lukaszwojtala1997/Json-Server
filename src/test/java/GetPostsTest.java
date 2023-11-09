import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetPostsTest extends BaseTest{

    @Test
    public void getPosts(){
        given().
                spec(requestSpecification).
       when().
                get().
       then().
                spec(responseSpecification);
    }
}
