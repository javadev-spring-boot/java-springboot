package com.example.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactService {

    public static final Map<UUID, Contact> contactMap= new HashMap<>();
    public static final ObjectMapper mapper= new ObjectMapper();

    public List<Contact> findAll() {
        List<Contact> all = new ArrayList<>();
        for (Map.Entry<UUID, Contact> enrty : contactMap.entrySet()) {
            all.add(enrty.getValue());
        }
        return all;
    }

    public int count() {
        return contactMap.keySet().size();
    }

    public Contact save(Contact contact) throws Exception {
        for (Map.Entry<UUID, Contact> entry : contactMap.entrySet()) {
            if (contact.getId().equals(entry.getKey())) {
                throw new Exception("Contact already existed");
            }
        }
        contactMap.put(contact.getId(), contact);
        return contactMap.get(contact.getId());
    }

    public Contact findById(UUID id) throws Exception {
        Contact contact = contactMap.get(id);
        if (contact==null) {
            throw new Exception("Contact does not existed");
        }
        else return contact;
    }

    public void update(Contact contact)
            throws Exception {
          Contact contact1 = contactMap.get(contact.getId());
          if (contact1 == null) {
              throw new Exception("Contact does not existed");
          }
        contactMap.replace(contact.getId(), contact);
    }

    public void deleteById(UUID id) throws Exception {
        Contact contact1 = contactMap.get(id);
        if (contact1 == null) {
            throw new Exception("Contact does not existed");
        }
        contactMap.remove(id);
    }

}
