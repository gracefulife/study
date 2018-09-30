import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject
import xyz.gracefulife.blog.PostRepository
import xyz.gracefulife.blog.WritePostService

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WritePostServiceSpecs extends Specification {
    @Subject
    WritePostService writePostService

    PostRepository postRepository = Mock()

    def setup() {
        writePostService = new WritePostService(postRepository)
    }

    def '글은 제목과 내용이 모두 입력되어야 합니다.'() {
        // TODO not yet implements
    }

    def '글 제목이 입력되지 않은 경우 IllegalArgumentException 이 발생되어야 합니다.'() {
        // TODO not yet implements
    }

    def '글 내용이 입력되지 않은 경우 IllegalArgumentException 이 발생되어야 합니다.'() {
        // TODO not yet implements
    }
}
