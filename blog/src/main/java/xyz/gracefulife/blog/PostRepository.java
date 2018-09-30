package xyz.gracefulife.blog;

import reactor.core.publisher.Mono;

public interface PostRepository {

  Mono<Post> save(Post post);
}
