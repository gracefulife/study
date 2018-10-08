package xyz.gracefulife.blog;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdatePostService {
  private final ReadPostService readPostService;
  private final PostRepository postRepository;

  public Mono<PostResponse> update(Post post) {
    return readPostService.findById(post.getId())
        .map(PostResponse::to)
        .flatMap(p -> {
          p.updateFields(post);
          return postRepository.save(p);
        })
        .map(PostResponse::from);
  }
}
