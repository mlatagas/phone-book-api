package com.phonebook.phonebookapi.data.repository;

import com.phonebook.phonebookapi.data.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
    Page<Entry> findByPhoneBookId(Integer phoneBookId, Pageable pageable);

}
