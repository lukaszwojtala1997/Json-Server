import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class UpdatePostTest extends BaseTest{

    @Test
    public void replacePost(){
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "tytul po aktualizacji");
        newPost.put("author", "Daria");

        given().spec(requestSpecification).pathParam("postId", 3).
                body(newPost).
                when().put("{postId}").
                then().spec(responseSpecification).extract().body().as(Post.class);
    }
    @Test
    public void replacePostObject(){
        Post newPost = new Post();
        newPost.setAuthor("Tomek");
        newPost.setTitle("Tytul Tomka");

        given().spec(requestSpecification).pathParam("postId", 5).
                body(newPost).
                when().put("{postId}").
                then().spec(responseSpecification).extract().body().as(Post.class);
    }

    @Test
    public void replacePostObjectWithoutTitle(){
        Post newPost = new Post();
        newPost.setAuthor("Tomek");

        given().spec(requestSpecification).pathParam("postId", 6).
                body(newPost).
                when().put("{postId}").
                then().spec(responseSpecification).extract().body().as(Post.class);
    }
    @Test
    public void updatePostObject(){
        Post newPost = new Post();
        newPost.setAuthor("Tomek");

        given().spec(requestSpecification).pathParam("postId", 7).
                body(newPost).
                when().put("{postId}").
                then().spec(responseSpecification).extract().body().as(Post.class);
    }
}
