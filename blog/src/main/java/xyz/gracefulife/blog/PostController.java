package xyz.gracefulife.blog;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
  private final ReadPostService readPostService;
  private final WritePostService writePostService;
  private final UpdatePostService updatePostService;
  private final DeletePostService deletePostService;

  @GetMapping
  public Flux<PostResponse> readPosts() {
    return readPostService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<PostResponse> readPost(@PathVariable String id) {
    return readPostService.findById(id);
  }

  @PostMapping
  public Mono<PostResponse> createPost(@RequestBody Post post) {
    return writePostService.write(post);
  }

  @PutMapping
  public Mono<PostResponse> updatePost(@RequestBody Post post) {
    return updatePostService.update(post);
  }

  @DeleteMapping("/{id}")
  public Mono<String> deletePost(@PathVariable String id) {
    return deletePostService.delete(id);
  }
}
