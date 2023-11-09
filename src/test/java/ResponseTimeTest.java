import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ResponseTimeTest{

    @Test
    public void getPost() {

        long time = when().get("http://localhost:3000/posts/{postId}", 1).
                timeIn(TimeUnit.MILLISECONDS);
        System.out.println(time);

        when().get("http://localhost:3000/posts/{postId}", 1).
        then().time(Matchers.lessThan(3000L), TimeUnit.MILLISECONDS);
    }
}
