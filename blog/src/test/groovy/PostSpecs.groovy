import spock.lang.Specification
import xyz.gracefulife.blog.Post

class PostSpecs extends Specification {
    def '동일 ID 를 가진 다른 POST 로 업데이트 하는 경우 빈 필드를 제외하고 새로운 정보로 채워야 합니다.'() {
        given:
        def originPost = new Post("1", "타이틀1", "내용1")
        def newPost = new Post("1", "", "내용222")

        when:
        originPost.updateFields(newPost)

        then:
        originPost.id == "1"
        originPost.title == "타이틀1"
        originPost.contents == "내용222"
    }

    def '다른 ID 를 가진 다른 POST 로 업데이트 시도를 하는 경우 IllegalArgumentException 을 발생시켜야 합니다'() {
        given:
        def originPost = new Post("1", "타이틀1", "내용1")
        def newPost = new Post("2", "", "내용222")

        when:
        originPost.updateFields(newPost)

        then:
        thrown(IllegalArgumentException.class)
    }
}
