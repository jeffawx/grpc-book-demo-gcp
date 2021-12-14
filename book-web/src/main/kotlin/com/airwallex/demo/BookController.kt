package com.airwallex.demo

import com.airwallex.grpc.annotations.GrpcClient
import demo.BookServiceRpc
import demo.listBookRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    @GrpcClient(channel = "book")
    private val bookService: BookServiceRpc
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/list")
    suspend fun list(): List<Book> {
        logger.info("sending request to book-service")

        return bookService.list(listBookRequest { }).let {
            logger.info("get response from book-service")

            it.booksList.map { book ->
                Book(book.id, book.title, book.author)
            }
        }
    }
}
