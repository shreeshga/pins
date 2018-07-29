package ga.shreesh.pins.model

import java.util.*

data class Post(
        val href: String,
        val hash: String,
        val meta: String,
        val toread: String,
        val shared: String,
        val description: String = "",
        val extended: String = "",
        val others: String = "",
        val tags: String = "",
        val time: Date = Date(0))