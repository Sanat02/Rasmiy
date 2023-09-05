package com.example.demo.service;

import com.example.demo.dao.ContactsDao;
import com.example.demo.dto.ContactDto;
import com.example.demo.enums.ContactType;
import com.example.demo.model.Contacts;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsDao contactsDao;
    private final ContactRepository contactRepository;
    private final ResumeRepository resumeRepository;

    public List<ContactDto> getContactsDtoByResumeId(int id) {
        List<Contacts> contacts=contactRepository.findContactsByResumeId(id);
        return contacts.stream()
                .map(contact -> ContactDto.builder()
                        .type(contact.getType())
                        .value(contact.getValuec()).build()
                ).collect(Collectors.toList());

    }

    public void saveContacts(List<ContactDto> contacts, int resumeId) {
        List<Contacts> contactsList = contacts.stream()
                .map(e -> Contacts.builder()
                        .valuec(e.getValue())
                        .type(e.getType())
                        .resume(resumeRepository.findById(resumeId).get())
                        .build()
                ).collect(Collectors.toList());
        for (int i = 0; i < contactsList.size(); i++) {
            contactRepository.save(contactsList.get(i));
        }
    }

}
