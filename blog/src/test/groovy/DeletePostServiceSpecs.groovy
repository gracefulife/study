import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject
import xyz.gracefulife.blog.DeletePostService
import xyz.gracefulife.blog.PostRepository

class DeletePostServiceSpecs extends Specification {
    @Subject
    DeletePostService deletePostService

    PostRepository postRepository = Mock()

    def setup() {
        deletePostService = new DeletePostService(postRepository)
    }

    def '존재하는 글을 삭제시 정상적으로 삭제되어야 합니다.'() {
        setup:
        postRepository.deleteById("1") >> Mono.just("1")

        when:
        Mono<String> mono = deletePostService.delete("1")

        then:
        '1' == mono.block()
    }

    def '존재하지 않는 글을 삭제시 IllegalArgumentException 이 발생되어야 합니다.'() {
        setup:
        postRepository.deleteById("1") >> Mono.error(new IllegalArgumentException())

        when:
        deletePostService.delete("1").block()

        then:
        thrown(IllegalArgumentException.class)
    }
}
