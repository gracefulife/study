import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject
import xyz.gracefulife.blog.Post
import xyz.gracefulife.blog.PostRepository
import xyz.gracefulife.blog.PostResponse
import xyz.gracefulife.blog.ReadPostService

import static org.hamcrest.Matchers.containsInAnyOrder
import static reactor.core.publisher.Mono.empty

class ReadPostServiceSpecs extends Specification {
    @Subject
    ReadPostService readPostService

    PostRepository postRepository = Mock()

    def setup() {
        readPostService = new ReadPostService(postRepository)
    }

    def '글 목록은 정상적으로 조회되어야 합니다.'() {
        setup:
        postRepository.findAll() >> Flux.just(new Post("1", "제목1", "내용1"), new Post("2", "제목2", "내용2"))

        when:
        def posts = readPostService.findAll().collectList().block()

        then:
        posts.size() == 2
        containsInAnyOrder(new PostResponse("1", "제목1", "내용1"), new PostResponse("2", "제목2", "내용2"))
    }

    def '존재하는 ID 를 주고 단일 글을 조회 시 잘 동작하여야 합니다.'() {
        given:
        def existPostId = "1A"
        def givenExistIdMono = Mono.just(existPostId)

        and:
        postRepository.findById(givenExistIdMono) >> Mono.just(new Post("1A", "제목1", "내용1"))

        when:
        def foundPost = readPostService.findById(givenExistIdMono).single().block()

        then:
        foundPost.id == "1A"
        foundPost.title == "제목1"
        foundPost.contents == "내용1"
    }

    def '존재하지 않는 ID 를 주고 단일 글을 조회 시 NoSuchElementException 이 발생해야 합니다.'() {
        given:
        def notExistPostId = "not exist"
        def givenNotExistIdMono = Mono.just(notExistPostId)

        and:
        postRepository.findById(givenNotExistIdMono) >> empty()

        when:
        readPostService.findById(givenNotExistIdMono).block()

        then:
        thrown(NoSuchElementException.class)
    }

}
