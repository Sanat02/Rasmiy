package com.example.demo.dao;

import com.example.demo.model.Contacts;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileEditorDao {
    private final UserDao userDao;


    public void editAccountName(int userId, String account_name) {
        userDao.updateAccountName(userId,account_name);
    }
}
