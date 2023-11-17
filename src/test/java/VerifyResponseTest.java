import com.aventstack.extentreports.ExtentTest;
import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class VerifyResponseTest extends BaseTest{

    @Test
    public void getPostContains(){
        ExtentTest test = extentReports.createTest("Get post contains");

        given().log().all().
                when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body(Matchers.containsString("Tomek"));
    }

    @Test
    public void getPostCheckSpecificField(){
        ExtentTest test = extentReports.createTest("Get post check specific field");

        given().log().all().
                when().get("http://localhost:3000/posts/{postId}", 1)
                .then().assertThat().body("title", equalTo("Zaktualizowany tytul"))
        .and().assertThat().body("author", equalTo("Tomek"));
    }

    @Test
    public void getPostObject(){
        ExtentTest test = extentReports.createTest("Get post object");

        Integer id = 1;

        Post newPost = given().log().all().
                when().get("http://localhost:3000/posts/{postId}", id)
                .then().log().all().body("title", equalTo("Zaktualizowany tytul"))
        .and().body("author", equalTo("Tomek")).extract().body().as(Post.class);

        Assert.assertEquals(newPost.getAuthor(), "Tomek");
        Assert.assertEquals(newPost.getTitle(), "Zaktualizowany tytul");
        Assert.assertEquals(newPost.getId(), id);
    }

    @Test
    public void addPostObject(){
        ExtentTest test = extentReports.createTest("Add post object");

        Post newPost = new Post();
        newPost.setAuthor("Autor obiektowy");
        newPost.setTitle("Tytul obiektowy");

        Post createdPost = given().contentType(ContentType.JSON).body(newPost).
                when().post("http://localhost:3000/posts").
                then().log().all().extract().body().as(Post.class);

        Assert.assertEquals(newPost, createdPost);
    }
}
