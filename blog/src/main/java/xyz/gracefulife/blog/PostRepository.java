package xyz.gracefulife.blog;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository {

  Mono<Post> save(Post post);

  Flux<Post> findAll();

  Mono<Post> findById(String id);
}
