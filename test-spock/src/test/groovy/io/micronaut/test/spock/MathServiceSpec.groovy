package io.micronaut.test.spock

import io.micronaut.test.annotation.MicronautTest
import spock.lang.*
import javax.inject.Inject

@MicronautTest // <1>
class MathServiceSpec extends Specification {

    @Inject
    MathService mathService // <2>

    @Unroll
    void "should compute #num times 4"() { // <3>
        when:
        def result = mathService.compute(num)

        then:
        result == expected

        where:
        num | expected
        2   | 8
        3   | 12
    }
}
