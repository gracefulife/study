package xyz.gracefulife.blog.post;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse implements Serializable {
  private String id;
  private String title;
  private String contents;

  static PostResponse from(Post post) {
    return new PostResponse(post.getId(), post.getTitle(), post.getContents());
  }

  public Post to() {
    return new Post(id, title, contents);
  }
}
