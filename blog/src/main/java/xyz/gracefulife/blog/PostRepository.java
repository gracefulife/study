package xyz.gracefulife.blog;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository extends ReactiveCrudRepository<Post, String> {

  Flux<Post> findAll();

  Mono<Post> findById(String id);

  Mono<String> delete(String id);
}
