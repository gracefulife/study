package xyz.gracefulife.blog;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WritePostService {
  private final PostRepository postRepository;

  public Mono<Post> write(Post post) {
    return postRepository.save(post);
  }
}
