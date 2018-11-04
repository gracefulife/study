package xyz.gracefulife.blog.blog;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import xyz.gracefulife.blog.post.Post;

public interface BlogRepository extends ReactiveCrudRepository<Blog, String> {
}
