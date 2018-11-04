import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject
import xyz.gracefulife.blog.post.Post
import xyz.gracefulife.blog.post.PostRepository
import xyz.gracefulife.blog.post.PostResponse
import xyz.gracefulife.blog.post.ReadPostService
import xyz.gracefulife.blog.post.UpdatePostService

class UpdatePostServiceSpecs extends Specification {
    @Subject
    UpdatePostService updatePostService

    ReadPostService readPostService = Mock()
    PostRepository postRepository = Mock()

    def setup() {
        updatePostService = new UpdatePostService(readPostService, postRepository)
    }

    def '존재하지 않는 ID 를 주고 글을 업데이트 하려 할 경우 IllegalArgumentException 이 발생해야 합니다.'() {
        given:
        def post = new Post("2", "제목1", "내용1")
        def givenExistId = "1"
        def givenNotExistId = "2"

        and:
        def mockPost = new PostResponse("1", "제목1", "내용1")

        readPostService.findById(givenExistId) >> Mono.just(mockPost)
        readPostService.findById(givenNotExistId) >> { throw new IllegalArgumentException() }
        postRepository.save(mockPost.to()) >> Mono.just(mockPost.to())

        when:
        updatePostService.update(post).block()

        then:
        thrown(IllegalArgumentException.class)
    }

    def '존재하는 ID 의 글을 주고 업데이트 하는 경우 필드의 정보가 새로운 정보로 업데이트 되어야 합니다'() {
        given:
        def post = new Post("1", "제목1", "내용1")

        and:
        def mockPost = new PostResponse("1", "제목1", "내용11")
        readPostService.findById("1") >> Mono.just(mockPost)


        def expectedPost = mockPost.to()
        expectedPost.updateFields(post)
        postRepository.save(expectedPost) >> Mono.just(expectedPost)

        when:
        def result = updatePostService.update(post).block()

        then:
        result.id == "1"
        result.title == "제목1"
        result.contents == "내용1"
    }

    def '존재하는 ID 의 글을 주고 업데이트 하는 경우 빈 필드를 제외하고 새로운 정보로 업데이트 되어야 합니다'() {
        given:
        def post = new Post("1", "", "내용1")

        and:
        def mockPost = new PostResponse("1", "제목1", "내용11")
        readPostService.findById("1") >> Mono.just(mockPost)


        def expectedPost = mockPost.to()
        expectedPost.updateFields(post)
        postRepository.save(expectedPost) >> Mono.just(expectedPost)

        when:
        def result = updatePostService.update(post).block()

        then:
        result.id == "1"
        result.title == "제목1"
        result.contents == "내용1"
    }
}
