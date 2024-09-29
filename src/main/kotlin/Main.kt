data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Boolean,
    val date: Int,
    val text: String,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val comments: Comment = Comment(),
    val likes: Like = Like()
)

data class Comment(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true
)

data class Like(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

class WallService {
    private val posts: MutableList<Post> = mutableListOf()
    private var postIdCounter: Int = 0

    fun add(post: Post): Post {
        val newPost = post.copy(id = ++postIdCounter)
        posts.add(newPost)
        return newPost
    }

    fun update(post: Post): Boolean {
        val existingPost = posts.find { it.id == post.id }
        if (existingPost != null) {
            val updatedPost = post.copy(id = existingPost.id)
            val index = posts.indexOf(existingPost)
            posts.set(index, updatedPost)
            return true
        }
        return false
    }

    fun getPosts(): List<Post> {
        return posts
    }
}

fun main() {
    val wallService = WallService()

    val post1 = Post(
        id = 0,
        ownerId = 123,
        fromId = 123,
        createdBy = true,
        date = 1643723400,
        text = "Hello, world!",
        friendsOnly = true
    )

    val post2 = Post(
        id = 0,
        ownerId = 456,
        fromId = 456,
        createdBy = false,
        date = 1643723410,
        text = "Kotlin is awesome!",
        replyOwnerId = 123,
        replyPostId = 1
    )

    val addedPost1 = wallService.add(post1)
    val addedPost2 = wallService.add(post2)

    println("Added post 1:")
    println("  ID: ${addedPost1.id}")
    println("  Owner ID: ${addedPost1.ownerId}")
    println("  From ID: ${addedPost1.fromId}")
    println("  Created by: ${addedPost1.createdBy}")
    println("  Date: ${addedPost1.date}")
    println("  Text: ${addedPost1.text}")
    println("  Friends only: ${addedPost1.friendsOnly}")
    println()

    println("Added post 2:")
    println("  ID: ${addedPost2.id}")
    println("  Owner ID: ${addedPost2.ownerId}")
    println("  From ID: ${addedPost2.fromId}")
    println("  Created by: ${addedPost2.createdBy}")
    println("  Date: ${addedPost2.date}")
    println("  Text: ${addedPost2.text}")
    println("  Friends only: ${addedPost2.friendsOnly}")
    println()

    val updatedPost = addedPost1.copy(text = "Hello, universe!")
    val isUpdated = wallService.update(updatedPost)

    println("Updated post:")
    println("  ID: ${updatedPost.id}")
    println("  Owner ID: ${updatedPost.ownerId}")
    println("  From ID: ${updatedPost.fromId}")
    println("  Created by: ${updatedPost.createdBy}")
    println("  Date: ${updatedPost.date}")
    println("  Text: ${updatedPost.text}")
    println("  Friends only: ${updatedPost.friendsOnly}")
    println("  Is updated: $isUpdated")
    println()

    val posts = wallService.getPosts()

    posts.forEach { post ->
        println("Post ${post.id}:")
        println("  Owner ID: ${post.ownerId}")
        println("  From ID: ${post.fromId}")
        println("  Created by: ${post.createdBy}")
        println("  Date: ${post.date}")
        println("  Text: ${post.text}")
        println("  Friends only: ${post.friendsOnly}")
        println()
    }
}
