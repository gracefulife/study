package xyz.gracefulife.blog;

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
}

