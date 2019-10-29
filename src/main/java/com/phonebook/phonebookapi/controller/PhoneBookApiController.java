package com.phonebook.phonebookapi.controller;

import com.phonebook.phonebookapi.data.Entry;
import com.phonebook.phonebookapi.data.PhoneBook;
import com.phonebook.phonebookapi.service.PhoneBookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:8000")
public class PhoneBookApiController {

  @Autowired PhoneBookService phoneBookService;

  @GetMapping(path = "{phoneBookId}/phone-book")
  public ResponseEntity addEntry(@PathVariable int phoneBookId) {
    return ResponseEntity.ok(phoneBookService.getPhoneBooksById(phoneBookId));
  }

  @GetMapping(path = "phone-books")
  public List<PhoneBook> getPhoneBooks() {
    return phoneBookService.getPhoneBooks();
  }

  @PostMapping(path = "phone-book/{id}/entry")
  public Entry addEntry(@PathVariable int id, @Valid @RequestBody Entry entryRequest) {
    return phoneBookService.addPhoneBookEntry(id, entryRequest);
  }

  @GetMapping(path = "phone-book/{id}/entry")
  public Page<Entry> getAllEntriesById(@PathVariable int id, Pageable pageable) {
    return phoneBookService.getAllEntriesById(id, pageable);
  }
}
