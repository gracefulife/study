package xyz.gracefulife.blog;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeletePostService {
  private final PostRepository postRepository;

  public Mono<String> delete(String id) {
    return postRepository.delete(id).onErrorMap(IllegalArgumentException::new); // FIXME 모든 에러를 wrap 하지 않아야 함
  }
}
