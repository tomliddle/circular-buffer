import org.scalatest.BeforeAndAfterEach
import org.scalatest.Matchers
import org.scalatest.WordSpec


class CircularBufferTest extends WordSpec with Matchers with BeforeAndAfterEach {

	"Circular Buffer" when {

		"creating" should {

			"create the correct length buffer" in {
				val buffer = CircularBuffer[Int](5)

				buffer.length shouldBe 5
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

			"add a new element" in {
				val buffer = CircularBuffer[Int](5).insert(4)

				buffer.headOption shouldEqual Some(4)
			}

		}

		"retrieving" should {

			"only retrieve an element once" in {
				val buffer = CircularBuffer[Int](5).insert(4).tail

				buffer.headOption shouldEqual None
			}

			"retrieve all five elements" in {
				val buffer = CircularBuffer[Int](5).insert(1).insert(2).insert(3).insert(4).insert(5)

				buffer.headOption shouldEqual Some(1)
				buffer.tail.headOption shouldEqual Some(2)
				buffer.tail.tail.headOption shouldEqual Some(3)
				buffer.tail.tail.tail.headOption shouldEqual Some(4)
				buffer.tail.tail.tail.tail.headOption shouldEqual Some(5)
			}

			"retrieve only 3 added elements" in {
				val buffer = CircularBuffer[Int](5).insert(1).insert(2).insert(3)

				buffer.headOption shouldEqual Some(1)
				buffer.tail.headOption shouldEqual Some(2)
				buffer.tail.tail.headOption shouldEqual Some(3)
				buffer.tail.tail.tail.headOption shouldEqual None
			}

			"add 7 elements in a CB of size 5 and overwrite oldest" in {
				val buffer = CircularBuffer[Int](5).insert(1).insert(2).insert(3).insert(4).insert(5).insert(6).insert(7)

				buffer.headOption shouldEqual Some(3)
				buffer.tail.headOption shouldEqual Some(4)
				buffer.tail.tail.headOption shouldEqual Some(5)
				buffer.tail.tail.tail.headOption shouldEqual Some(6)
				buffer.tail.tail.tail.tail.headOption shouldEqual Some(7)
			}

			"add 7 elements in a CB of size 2 and overwrite oldest" in {
				val buffer = CircularBuffer[Int](2).insert(1).insert(2).insert(3).insert(4).insert(5).insert(6).insert(7)

				buffer.headOption shouldEqual Some(6)
				buffer.tail.headOption shouldEqual Some(7)
			}

		}
	}
}
