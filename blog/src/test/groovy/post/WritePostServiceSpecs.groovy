package post

import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject
import xyz.gracefulife.blog.post.Post
import xyz.gracefulife.blog.post.PostRepository
import xyz.gracefulife.blog.post.WritePostService

class WritePostServiceSpecs extends Specification {
    @Subject
    WritePostService writePostService

    PostRepository postRepository = Mock()

    def setup() {
        writePostService = new WritePostService(postRepository)
    }

    def '글은 제목과 내용이 모두 입력되어야 합니다.'() {
        given:
        def post = new Post("", "제목1", "내용1")

        and:
        postRepository.save(post) >> Mono.just(post)

        when:
        def savedPost = writePostService.write(post).block()

        then:
        post.contents == savedPost.contents
        post.title == savedPost.title
    }

    def '글 제목이 입력되지 않은 경우 IllegalArgumentException 이 발생되어야 합니다.'() {
        given:
        def emptyTitlePost = new Post("", "", "내용1")

        when:
        writePostService.write(emptyTitlePost).block()

        then:
        def e = thrown(IllegalArgumentException.class)
        e.message == "Post 의 제목과, 내용은 필수 입력값입니다."
    }

    def '글 내용이 입력되지 않은 경우 IllegalArgumentException 이 발생되어야 합니다.'() {
        given:
        def emptyContentsPost = new Post("", "제목1", "")

        when:
        writePostService.write(emptyContentsPost).block()

        then:
        def e = thrown(IllegalArgumentException.class)
        e.message == "Post 의 제목과, 내용은 필수 입력값입니다."
    }

}
