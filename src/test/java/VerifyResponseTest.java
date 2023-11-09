import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class VerifyResponseTest {

    @Test
    public void getPostContains(){

        given().log().all().
                when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body(Matchers.containsString("Tomek"));
    }

    @Test
    public void getPostCheckSpecificField(){

        given().log().all().
                when().get("http://localhost:3000/posts/{postId}", 1)
                .then().assertThat().body("title", equalTo("Zaktualizowany tytul"))
        .and().assertThat().body("author", equalTo("Tomek"));
    }

    @Test
    public void getPostObject(){

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

        Post newPost = new Post();
        newPost.setAuthor("Autor obiektowy");
        newPost.setTitle("Tytul obiektowy");

        Post createdPost = given().contentType(ContentType.JSON).body(newPost).
                when().post("http://localhost:3000/posts").
                then().log().all().extract().body().as(Post.class);

        Assert.assertEquals(newPost, createdPost);
    }
}
