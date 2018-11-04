package xyz.gracefulife.blog.blog;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WriteBlogService {
  private final BlogRepository blogRepository;

  public Mono<BlogResponse> write(Blog blog) {
    return Mono.just(blog)
        .flatMap(blogRepository::save)
        .map(BlogResponse::from);
  }
}
