package xyz.gracefulife.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class PostController {
  private final ReadPostService readPostService;
  private final WritePostService writePostService;

  @GetMapping("/posts")
  public Flux<PostResponse> posts() {
    return readPostService.findAll();
  }

  @PostMapping("/posts")
  public Mono<PostResponse> savePost(@RequestBody Post post) {
    return writePostService.write(post);
  }
}
