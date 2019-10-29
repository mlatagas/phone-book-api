package com.phonebook.phonebookapi.data.dao;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "PhoneBookDaoBuilder", toBuilder = true)
public class PhoneBookDao {
    private int id;
    private String name;


    @JsonPOJOBuilder(withPrefix = "")
    public static class PhoneBookDaoBuilder {
        public PhoneBookDao.PhoneBookDaoBuilder dto(PhoneBookDto phoneBookDto) {
            id(phoneBookDto.getId());
            name(phoneBookDto.getName());

            return this;
        }
    }
}
