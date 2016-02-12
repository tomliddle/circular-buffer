


trait CircularBuffer[T] {

	protected val buffer: IndexedSeq[Option[T]]
	protected val startIdx: Int
	protected val endIdx: Int
	lazy val length = buffer.length

	def insert(t: T): CircularBuffer[T] = CircularBuffer(buffer.updated(startIdx, Some(t)), startIdx, endIdx)

	def take: (Option[T], CircularBuffer[T]) = {
		(buffer(startIdx), CircularBuffer(buffer, (startIdx + 1) % length, endIdx))
	}

}

object CircularBuffer {
	def apply[T](length: Int): CircularBuffer[T] = CircularBuffer(IndexedSeq[Option[T]]().padTo(length, None))

	def apply[T](circularBuffer: IndexedSeq[Option[T]], start: Int = 0, end: Int = 0): CircularBuffer[T] = {
		require(circularBuffer.nonEmpty)

		new CircularBuffer[T] {
			override val buffer = circularBuffer
			override val startIdx = start
			override val endIdx = end
		}
	}
}
