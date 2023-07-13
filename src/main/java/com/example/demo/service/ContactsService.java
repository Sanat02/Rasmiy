package com.example.demo.service;

import com.example.demo.dao.ContactsDao;
import com.example.demo.model.Contacts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsDao contactsDao;

    public List<Contacts> getContactsById(int id){
        return contactsDao.getContactsById(id);
    }
}
