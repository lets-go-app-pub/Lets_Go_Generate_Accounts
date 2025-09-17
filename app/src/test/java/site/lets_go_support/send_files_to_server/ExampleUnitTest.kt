package site.lets_go_support.send_files_to_server

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val hello = "hello"
        val byteArray = hello.toByteArray()
        val final = byteArray.contentToString()
        println("ByteArray: $byteArray")
        println("ByteArray: $final")
        assertEquals(4, 2 + 2)
    }
}
