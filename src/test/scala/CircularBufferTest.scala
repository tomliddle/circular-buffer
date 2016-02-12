import org.scalatest.BeforeAndAfterEach
import org.scalatest.Matchers
import org.scalatest.WordSpec


class CircularBufferTest extends WordSpec with Matchers with BeforeAndAfterEach {

	"Circular Buffer" when {

		"creating" should {

			"create the correct length buffer" in {
				val buffer = CircularBuffer[Int](5)

				buffer.length shouldBe(5)
			}

			"throw a InvalidArgumentException when trying to create a 0 length buffer" in {
				try {
					val buffer = CircularBuffer[Int](0)
					fail("Should have thrown an exception")
				}
				catch {
					case iae: IllegalArgumentException => // Expected behaviour
				}
			}
		}

		"adding" should {

			"add a new element and retrieve it" in {
				val buffer = CircularBuffer[Int](5).insert(4)

				buffer.headOption shouldEqual Some(4)
			}

			"only retrieve an element once" in {
				val buffer = CircularBuffer[Int](5).insert(4).tail

				buffer.headOption shouldEqual None
			}
			
		}
	}
}
