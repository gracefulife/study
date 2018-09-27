import spock.lang.Specification

class HelloSpock extends Specification {
    def "Hello spock !"() {
        setup:
        def stack = new Stack()
        def elem = "push me"

        when:
        stack.push(elem)

        then:
        !stack.empty
        stack.size() == 1
        stack.peek() == elem
    }
}
