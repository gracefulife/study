package xyz.gracefulife.blog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
  @Id private String id;
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

