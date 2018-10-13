package xyz.gracefulife.blog;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveCrudRepository<Post, String> {
  Flux<Post> findAll();
}
