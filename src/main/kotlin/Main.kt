sealed class Attachment(val type: String)

data class PhotoAttachment(
    val photo: Photo
) : Attachment(type = "photo")

data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

data class VideoAttachment(
    val video: Video
) : Attachment(type = "video")

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

data class AudioAttachment(
    val audio: Audio
) : Attachment(type = "audio")

data class Audio(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

data class DocAttachment(
    val doc: Document
) : Attachment(type = "doc")

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int
)

data class LinkAttachment(
    val link: Link
) : Attachment(type = "link")

data class Link(
    val url: String,
    val title: String,
    val description: String
)

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
    val likes: Like = Like(),
    val attachments: List<Attachment> = emptyList()
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
            posts[index] = updatedPost
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

    val photo = Photo(1, 1, "https://vk.com/photo_130", "https://vk.com/photo_604")
    val video = Video(1, 1, "A Funny Video", 30)

    val post1 = Post(
        id = 0,
        ownerId = 123,
        fromId = 123,
        createdBy = true,
        date = 1643723400,
        text = "Hello, world!",
        attachments = listOf(
            PhotoAttachment(photo),
            VideoAttachment(video)
        )
    )

    val addedPost1 = wallService.add(post1)

    println("Added post 1:")
    println("  ID: ${addedPost1.id}")
    println("  Owner ID: ${addedPost1.ownerId}")
    println("  From ID: ${addedPost1.fromId}")
    println("  Created by: ${addedPost1.createdBy}")
    println("  Date: ${addedPost1.date}")
    println("  Text: ${addedPost1.text}")
    println("  Attachments: ${addedPost1.attachments}")
    println()

    val posts = wallService.getPosts()

    posts.forEach { post ->
        println("Post ${post.id}:")
        println("  Owner ID: ${post.ownerId}")
        println("  From ID: ${post.fromId}")
        println("  Created by: ${post.createdBy}")
        println("  Date: ${post.date}")
        println("  Text: ${post.text}")
        println("  Attachments: ${post.attachments}")
        println()
    }
}

