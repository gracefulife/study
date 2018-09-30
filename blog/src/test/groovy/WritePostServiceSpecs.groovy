import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WritePostServiceSpecs extends Specification {

    def setup() {
        // TODO not yet implements
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
