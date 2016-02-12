

trait CircularBuffer[T] {

	protected val buffer: IndexedSeq[Option[T]]
	protected val readIdx: Int
	protected val writeIdx: Int
	lazy val length = buffer.length

	def insert(t: T): CircularBuffer[T] = {
		// Add one on to the next write index
		val nWriteIdx = (writeIdx + 1) % length
		// If our readIdx == writeIdx we need to advance the readIdx as the last value will be overwritten
		val nReadIdx = if (buffer(writeIdx).isDefined && readIdx == writeIdx) nWriteIdx else readIdx
		CircularBuffer(buffer.updated(writeIdx, Some(t)), nReadIdx, nWriteIdx)
	}

	def headOption: Option[T] = buffer(readIdx)

	def tail: CircularBuffer[T] = CircularBuffer(buffer, (readIdx + 1) % length, writeIdx)

}

object CircularBuffer {
	def apply[T](length: Int): CircularBuffer[T] = CircularBuffer(IndexedSeq[Option[T]]().padTo(length, None))

	protected def apply[T](circularBuffer: IndexedSeq[Option[T]], start: Int = 0, end: Int = 0): CircularBuffer[T] = {
		require(circularBuffer.nonEmpty)

		new CircularBuffer[T] {
			override val buffer = circularBuffer
			override val readIdx = start
			override val writeIdx = end
		}
	}
}
