package com.example.demo.service;

import com.example.demo.dao.ContactsDao;
import com.example.demo.dto.ContactDto;
import com.example.demo.model.Contacts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsDao contactsDao;

    public List<ContactDto> getContactsDtoByResumeId(int id) {
        List<Contacts> contacts = contactsDao.getContactsByResumeId(id);
        return contacts.stream()
                .map(contact -> ContactDto.builder()
                        .type(contact.getType())
                        .value(contact.getValue()).build()
                ).collect(Collectors.toList());

    }

}
