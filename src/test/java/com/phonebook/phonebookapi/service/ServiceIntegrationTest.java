package com.phonebook.phonebookapi.service;

import com.phonebook.phonebookapi.data.Entry;
import com.phonebook.phonebookapi.data.repository.EntryRepository;
import com.phonebook.phonebookapi.data.repository.PhoneBookRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceIntegrationTest {

  @TestConfiguration
  static class PhoneServiceTestConfig {
    @Autowired private EntryRepository entryRepository;

    @Autowired private PhoneBookRepository phoneBookRepository;

    @Bean
    public PhoneBookService phoneBookService() {
      return new PhoneBookServiceImpl(phoneBookRepository, entryRepository);
    }
  }

  @Autowired private PhoneBookService phoneBookService;

  @Test
  public void itShouldAddPhoneBookEntry() {
    Entry entry = phoneBookService.addPhoneBookEntry(1, getFakeEntry());
    TestCase.assertNotNull(entry);
    TestCase.assertTrue(entry.getName() == "Fake Name");
    TestCase.assertTrue(entry.getPhoneNumber() == "+27123456789");
  }


  @Test
  public void itShouldRetrieveAPagenatedListOfEntries() {
    Page<Entry> entries = phoneBookService.getAllEntriesById(1,Pageable.unpaged());
    TestCase.assertNotNull(entries);
    TestCase.assertFalse(entries.isEmpty());
  }


  private Entry getFakeEntry() {
    Entry entry = new Entry();
    entry.setName("Fake Name");
    entry.setPhoneNumber("+27123456789");
    return entry;
  }
}
