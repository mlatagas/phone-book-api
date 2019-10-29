package com.phonebook.phonebookapi.service;

import com.phonebook.phonebookapi.data.Entry;
import com.phonebook.phonebookapi.data.PhoneBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneBookService {

  List<PhoneBook> getPhoneBooks();

  //TODO probably wouldn't return the entire phone-book or parent table on every item entry in a prod system as entries could get very large
  Entry addPhoneBookEntry(int phoneBookId, Entry phoneBookEntryRequest);

  PhoneBook getPhoneBooksById(int id);

  Page<Entry> getAllEntriesById(int id, Pageable pageable);
}
