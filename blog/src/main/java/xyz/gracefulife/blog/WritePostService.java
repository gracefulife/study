package xyz.gracefulife.blog;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WritePostService {
  private final PostRepository postRepository;

  public Mono<PostResponse> write(Post post) {
    return Mono.just(post)
        .flatMap(p -> {
          if (StringUtils.isEmpty(p.getTitle()) || StringUtils.isEmpty(p.getContents())) {
            return Mono.error(() -> new IllegalArgumentException("Post 의 제목과, 내용은 필수 입력값입니다."));
          }

          return postRepository.save(p);
        })
        .map(PostResponse::from);
  }
}
