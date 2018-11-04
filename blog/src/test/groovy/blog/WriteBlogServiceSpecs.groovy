package blog

import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Subject
import xyz.gracefulife.blog.blog.Blog
import xyz.gracefulife.blog.blog.BlogRepository
import xyz.gracefulife.blog.blog.WriteBlogService

class WriteBlogServiceSpecs extends Specification {
    @Subject
    WriteBlogService writeBlogService

    BlogRepository blogRepository = Mock()

    def setup() {
        writeBlogService = new WriteBlogService(blogRepository)
    }

    def '블로그는 제목이 입력되어야 합니다.'() {
        given:
        def blog = new Blog("", "Gracefulife 의 블로그")

        and:
        blogRepository.save(blog) >> Mono.just(blog)

        when:
        def savedBlog = writeBlogService.write(blog).block()

        then:
        blog.name == savedBlog.name
    }
}
