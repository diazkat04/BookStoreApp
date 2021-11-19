package edu.fiu.cen4010.g5.BookStoreApp.controller;

import edu.fiu.cen4010.g5.BookStoreApp.model.Cart;
import edu.fiu.cen4010.g5.BookStoreApp.model.Wishlist;
import edu.fiu.cen4010.g5.BookStoreApp.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistservice){
        this.wishlistService = wishlistservice;
    }

    // CRUD operations for Wishlists

    // CREATE/POST


    // READ/GET


    // UPDATE/PUT


    // DELETE

    @PostMapping
    public ResponseEntity addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts(){
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @PutMapping
    public ResponseEntity updateCart(@RequestBody Cart cart) {
        cartService.updateCart(cart);
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // Extended functionality to add/remove books and display wishlist contents
    // As well as push a book to a cart and remove from a wishlist

    @GetMapping("/{cartid}")
    public ResponseEntity<List<Book>> getCartContents(@PathVariable String cartid){
        return ResponseEntity.ok(cartService.getCartContents(cartid));
    }

    @PutMapping("/{cartid}/addBook/{bookid}")
    public ResponseEntity addBookToCart(@PathVariable String cartid, @PathVariable String bookid) {
        cartService.AddBookToCart(cartid, bookid);
        return ResponseEntity.ok(cartService.getCartContents(cartid));
    }

    @PutMapping("/{cartid}/removeBook/{bookid}")
    public ResponseEntity removeBookFromCart(@PathVariable String cartid, @PathVariable String bookid) {
        cartService.RemoveBookFromCart(cartid, bookid);
        return ResponseEntity.ok(cartService.getCartContents(cartid));
    }

    /*
    @PostMapping
    public ResponseEntity addBook(@RequestBody Wishlist book) {
        wishlistService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllBooks() {
        return ResponseEntity.ok(wishlistService.getAllBooks());
    }

    @GetMapping("/byuser/{userid}")
    public ResponseEntity<List<Wishlist>> getWishlistByUser(@PathVariable String userid) {
        return ResponseEntity.ok(wishlistService.getWishlistByUser(userid));
    }

    @GetMapping("/bywishlistname/{wishlistName}")
    public ResponseEntity<List<Wishlist>> getWishlistByWishlistName(@PathVariable String wishlistName) {
        return ResponseEntity.ok(wishlistService.getWishlistByWishlistName(wishlistName));
    }


    @PutMapping("/{wishlistname}/pushBook/{bookid}/toCart/{cartid}")
    public void pushBookToCart(@PathVariable String wishlistname, @PathVariable String bookid, @PathVariable String cartid) {

        wishlistService.pushBookToCart(wishlistname, bookid, cartid);
    }

     */



}
