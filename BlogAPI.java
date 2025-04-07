import java.util.ArrayList;
import java.util.List;

public class BlogAPI {
    private List<Post> posts = new ArrayList<>();

    public void createPost(String title, String content, String author) {
        Post post = new Post(title, content, author);
        posts.add(post);
        System.out.println("Post created with ID: " + post.getId());
    }

    public void getAllPosts() {
        if (posts.isEmpty()) {
            System.out.println("No blog posts available.");
            return;
        }
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    public void getPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                System.out.println(post);
                return;
            }
        }
        System.out.println("Post not found.");
    }

    public void updatePost(int id, String newTitle, String newContent) {
        for (Post post : posts) {
            if (post.getId() == id) {
                post.update(newTitle, newContent);
                System.out.println("Post updated.");
                return;
            }
        }
        System.out.println("Post not found.");
    }

    public void deletePost(int id) {
        posts.removeIf(post -> post.getId() == id);
        System.out.println("Post deleted (if it existed).");
    }
}
