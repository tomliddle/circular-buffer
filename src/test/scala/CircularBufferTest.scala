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
	}
}
