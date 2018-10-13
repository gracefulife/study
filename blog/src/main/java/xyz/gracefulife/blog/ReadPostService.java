package xyz.gracefulife.blog;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ReadPostService {
  private final PostRepository postRepository;

  public Flux<PostResponse> findAll() {
    return postRepository.findAll().map(PostResponse::from);
  }

  public Mono<PostResponse> findById(Mono<String> id) {
    return postRepository.findById(id)
        .map(PostResponse::from)
        .single();
  }

  public Mono<PostResponse> findById(String id) {
    return findById(Mono.just(id));
  }
}
