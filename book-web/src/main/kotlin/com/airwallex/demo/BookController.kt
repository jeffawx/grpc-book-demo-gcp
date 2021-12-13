package com.airwallex.demo

import com.airwallex.grpc.annotations.GrpcClient
import demo.BookServiceRpc
import demo.listBookRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    @GrpcClient(channel = "book")
    private val bookService: BookServiceRpc
) {

    @GetMapping("/list")
    suspend fun list() = bookService.list(listBookRequest { }).let {
        it.booksList.map { book ->
            Book(book.id, book.title, book.author)
        }
    }
}
