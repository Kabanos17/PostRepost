import org.junit.Assert.*
import org.junit.Before
import kotlin.test.Test

class WallServiceTest {

    private lateinit var service: WallService

    @Before
    fun setup() {
        service = WallService()
    }

    @Test
    fun addPost_IdIsNotZero() {
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 123,
            createdBy = true,
            date = 1643723400,
            text = "Hello, world!",
            friendsOnly = true
        )
        val addedPost = service.add(post)
        assertNotEquals(0, addedPost.id)
    }

    @Test
    fun updateExistingPost_ReturnsTrue() {
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 123,
            createdBy = true,
            date = 1643723400,
            text = "Hello, world!",
            friendsOnly = true
        )
        val addedPost = service.add(post)
        val updatedPost = addedPost.copy(text = "Hello, universe!")
        val result = service.update(updatedPost)
        assertTrue(result)
    }

    @Test
    fun updateNonExistingPost_ReturnsFalse() {
        val post = Post(
            id = 0,
            ownerId = 123,
            fromId = 123,
            createdBy = true,
            date = 1643723400,
            text = "Hello, world!",
            friendsOnly = true
        )
        val result = service.update(post)
        assertFalse(result)
    }
}
