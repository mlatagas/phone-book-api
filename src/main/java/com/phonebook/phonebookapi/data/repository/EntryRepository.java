package com.phonebook.phonebookapi.data.repository;

import com.phonebook.phonebookapi.data.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrRepository extends JpaRepository<PhoneBook, Integer> {
}
