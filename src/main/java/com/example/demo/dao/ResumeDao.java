package com.example.demo.dao;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.ContactType;
import com.example.demo.model.Contacts;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component


public class ResumeDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ResumeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Выборка всех резюме с указанием пользователя и их контактов
    public List<Resume> getAllResumes() {
        String query = "SELECT r.id AS resume_id, r.job, r.expectedsalary, r.jobexperience, r.education, " +
                "u.id AS user_id, u.accountname, u.email, u.password, u.accounttype," +
                "c.type, c.`value` " +
                "FROM resume r " +
                "JOIN users u ON r.userid = u.id " +
                "LEFT JOIN contacts c ON r.id = c.resumeid";
                System.out.println(query);

        Map<Integer, Resume> resumeMap = new HashMap<>();

        jdbcTemplate.query(query, rs -> {
            while (rs.next()) {
                int resumeId = rs.getInt("resume_id");
                Resume resume = resumeMap.get(resumeId);

                if (resume == null) {
                    resume = new Resume();
                    resume.setId(resumeId);
                    resume.setJob(rs.getString("job"));
                    resume.setExpectedSalary(rs.getInt("expectedsalary"));
                    resume.setJobExperience(rs.getString("jobexperience"));
                    resume.setEducation(rs.getString("education"));
                    resume.setContacts(new ArrayList<>());
                    resumeMap.put(resumeId, resume);

                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setAccountName(rs.getString("ACCOUNTNAME"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setAccountType(AccountType.valueOf(rs.getString("accounttype")));
                    resume.setUser(user);
                }


                Contacts contact = new Contacts();

                contact.setType(ContactType.valueOf(rs.getString("type")));
                contact.setValue(rs.getString("value"));
                resume.getContacts().add(contact);

            }
        });
        System.out.println(resumeMap);

        return new ArrayList<>(resumeMap.values());
    }

    //Выборка созданных пользователем резюме,идентификатор пользователя -> email
    public List<Resume> getResumesByUser(String email){
        List<Resume> resumeList=getAllResumes();
        var list=resumeList.stream().filter(e->e.getUser().getEmail().equals(email)).collect(Collectors.toList());
        return list;
    }

    //Поиск пользователя по номеру телефона
    public User getUserByPhone(String phone) {
        List<Resume> resumeList = getAllResumes();

        Optional<User> user = resumeList.stream()
                .filter(resume -> resume.getContacts().stream()
                        .anyMatch(contact -> contact.getValue().equals(phone) ))
                .map(Resume::getUser)
                .findFirst();

        return user.orElse(null);
    }


}
