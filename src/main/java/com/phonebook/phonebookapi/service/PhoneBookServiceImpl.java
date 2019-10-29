package com.phonebook.phonebookapi.service;

import com.phonebook.phonebookapi.data.Entry;
import com.phonebook.phonebookapi.data.PhoneBook;
import com.phonebook.phonebookapi.data.repository.EntryRepository;
import com.phonebook.phonebookapi.data.repository.PhoneBookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@Log4j2
public class PhoneBookServiceImpl implements PhoneBookService {

  private PhoneBookRepository phoneBookEntryRepository;
  private EntryRepository entryRepository;

  @Autowired
  public PhoneBookServiceImpl(
      PhoneBookRepository phoneBookEntryRepository, EntryRepository entryRepository) {
    this.phoneBookEntryRepository = phoneBookEntryRepository;
    this.entryRepository = entryRepository;
  }

  @Override
  public List<PhoneBook> getPhoneBooks() {
    return phoneBookEntryRepository.findAll();
  }

  @Override
  public Entry addPhoneBookEntry(int phoneBookId, Entry phoneBookEntryRequest) {
    log.info("Adding entry: {}, to phoneBookId: {}", phoneBookEntryRequest.toString(), phoneBookId);

    if (!phoneBookEntryRepository.existsById(phoneBookId)) {
      log.error("PhoneBook Id: {} not found", phoneBookId);
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
   return phoneBookEntryRepository.findById(phoneBookId).map(phoneBook -> {
      phoneBookEntryRequest.setPhoneBook(phoneBook);
      return entryRepository.save(phoneBookEntryRequest);
    }).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"PhoneBook with id: " + phoneBookId +" not found."));

  }

  @Override
  public PhoneBook getPhoneBooksById(int id) {
    log.info("retrieving phone books for id: {}", id);

    return phoneBookEntryRepository
        .findById(id)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
  }

  @Override
  public Page<Entry> getAllEntriesById(int id, Pageable pageable) {
    log.info("retrieving phone book entries for id: {}", id);
    return entryRepository.findByPhoneBookId(id, pageable);
  }
}
