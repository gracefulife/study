package xyz.gracefulife.blog.blog;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogResponse implements Serializable {
  private String id;
  private String name;

  static BlogResponse from(Blog blog) {
    return new BlogResponse(blog.getId(), blog.getName());
  }

  public Blog to() {
    return new Blog(id, name);
  }
}
