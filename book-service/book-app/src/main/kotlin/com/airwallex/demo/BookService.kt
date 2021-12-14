package com.airwallex.demo

import demo.BookOuterClass
import demo.BookServiceRpc
import demo.book
import demo.listBookResponse
import java.net.InetAddress
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BookService : BookServiceRpc {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val hostName = InetAddress.getLocalHost().hostName

    private val allBooks = listOf(
        Book(1, "Spring in Action, Sixth Edition", "Craig Walls"),
        Book(2, "Head First Kotlin: A Brain-Friendly Guide", "Dawn Griffiths and David Griffiths"),
        Book(3, "Effective Kotlin: Best practices", "Marcin Moskala")
    )

    override suspend fun list(request: BookOuterClass.ListBookRequest): BookOuterClass.ListBookResponse {
        logger.info("list request received on host: {}", hostName)

        return listBookResponse {
            allBooks.forEach {
                books += book {
                    id = it.id
                    title = it.title
                    author = it.author
                }
            }
        }
    }
}
