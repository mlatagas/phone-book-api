package com.phonebook.phonebookapi.data;

import com.phonebook.phonebookapi.data.repository.EntryRepository;
import com.phonebook.phonebookapi.data.repository.PhoneBookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PhoneBookRepositoryTest {

  @Autowired private TestEntityManager entityManager;

  @Autowired private EntryRepository entryRepository;

  @Autowired private PhoneBookRepository phoneBookRepository;

  @Test
  public void itShouldReturnAllPhoneBooks() {
    List<PhoneBook> found = phoneBookRepository.findAll();
    Assert.assertTrue(found.size() == 1);
  }

  @Test
  public void itShouldReturnAllExistingPhoneBookEntries() {
    Page found = entryRepository.findByPhoneBookId(1, Pageable.unpaged());
    Assert.assertTrue(found.getTotalElements() == 4);
  }
}
