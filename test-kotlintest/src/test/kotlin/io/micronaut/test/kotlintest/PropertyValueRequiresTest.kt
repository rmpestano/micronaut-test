package io.micronaut.test.kotlintest

import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.specs.AnnotationSpec
import io.micronaut.context.annotation.Property
import io.micronaut.context.annotation.Requires
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest(rebuildContext = true)
@Property(name = "foo.bar", value = "stuff")
open class PropertyValueRequiresTest: AnnotationSpec() {

    @Inject
    open lateinit var myService: MyService

    @Test
    open fun testInitialValue() {
        myService.shouldBeInstanceOf<MyServiceStuff>()
    }

    @Property(name = "foo.bar", value = "changed")
    @Test
    open fun testValueChanged() {
        myService.shouldBeInstanceOf<MyServiceChanged>()
    }

    @Test
    @Ignore
    open fun testValueRestored() {
        myService.shouldBeInstanceOf<MyServiceStuff>()
    }
}

interface MyService

@Singleton
@Requires(property = "foo.bar", value = "stuff")
open class MyServiceStuff : MyService


@Singleton
@Requires(property = "foo.bar", value = "changed")
open class MyServiceChanged : MyService