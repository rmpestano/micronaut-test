package io.micronaut.test.kotlintest

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.context.annotation.Property
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import javax.inject.Inject

@MicronautTest
class TestPropertiesProviderTest: StringSpec(), TestPropertyProvider {

    override fun getProperties(): MutableMap<String, String> {
        return mutableMapOf("foo.bar" to "3")
    }

    @set:Inject
    @setparam:Property(name = "foo.bar")
    var property: Int = 0

    init {
        "test the property was injected"() {
            property shouldBe 3
        }
    }
}
