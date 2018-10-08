package xyz.gracefulife.blog;

import org.springframework.util.StringUtils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
  private String id;
  private String title;
  private String contents;

  public void updateFields(Post post) {
    if (!id.equals(post.getId())) {
      throw new IllegalArgumentException("다른 ID 를 가진 POST 의 요청입니다.");
    }
    this.title = StringUtils.isEmpty(post.getTitle()) ? this.title : post.getTitle();
    this.contents = StringUtils.isEmpty(post.getContents()) ? this.contents : post.getContents();
  }
}

