package xyz.gracefulife.blog;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeletePostService {
  private final PostRepository postRepository;

  public Mono<String> delete(String id) {
    return postRepository.deleteById(id).thenReturn(id);
  }
}
