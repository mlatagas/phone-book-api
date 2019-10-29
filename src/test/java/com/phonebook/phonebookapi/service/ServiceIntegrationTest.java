package com.phonebook.phonebookapi;

import com.phonebook.phonebookapi.controller.PhoneBookApiController;
import com.phonebook.phonebookapi.data.Entry;
import com.phonebook.phonebookapi.data.PhoneBook;
import com.phonebook.phonebookapi.data.repository.EntryRepository;
import com.phonebook.phonebookapi.data.repository.PhoneBookRepository;
import com.phonebook.phonebookapi.service.PhoneBookService;
import com.phonebook.phonebookapi.service.PhoneBookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceIntegrationTest {

  @Autowired private EntryRepository entryRepository;

  @Autowired private PhoneBookRepository phoneBookRepository;

  private PhoneBookService phoneBookService =
      new PhoneBookServiceImpl(phoneBookRepository, entryRepository);

  @Test
  public void itShouldAddPhoneBookEntry() {
    phoneBookService.addPhoneBookEntry(1, getFakeEntry());
  }

  private Entry getFakeEntry() {
   return Entry.builder()
           .name("Andile")
           .phoneNumber("+123456789")
           .build();
  }


  @Test
  public void itShouldReturnAllExistingPhoneBookEntries() {
    Page found = entryRepository.findByPhoneBookId(1, Pageable.unpaged());
    Assert.assertTrue(found.getTotalElements() == 4);
  }
}
