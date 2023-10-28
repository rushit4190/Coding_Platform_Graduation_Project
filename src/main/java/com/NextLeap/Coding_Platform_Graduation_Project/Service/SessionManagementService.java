package com.NextLeap.Coding_Platform_Graduation_Project.Service;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.User;
import com.NextLeap.Coding_Platform_Graduation_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SessionManagementService {
    private long SESSION_TIMEOUT = 864000000; // SESSION_TIMEOUT in milliseconds, 10 days = 10*24*60*60*1000
    private final UserRepository userRepository;
    @Autowired
    public SessionManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HttpHeaders validate(String sessionToken) {

        Optional<User> user = userRepository.findBySessionToken(sessionToken);

        //Assuming session token is never incorrect or tampered
        if(user.isEmpty() || user.get().getSessionCreation() + SESSION_TIMEOUT < System.currentTimeMillis()){
                sessionToken = createSession();
        }
//        System.out.println("Session token is up to date -" + sessionToken);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("sessionToken", sessionToken);

//        System.out.println("response header returned");
        return responseHeader;
    }

    private String createSession(){
        String sessionToken = UUID.randomUUID().toString();
        String guestId = UUID.randomUUID().toString();

        User user = new User();
        user.setUserId(guestId);
        user.setFirstName("Guest");
        user.setSessionToken(sessionToken);
        user.setSessionCreation(System.currentTimeMillis());

        System.out.println("user created with guest id " + guestId + " Session token " + sessionToken );

        userRepository.save(user);
//        System.out.println("user detail saved in repo ");

        return sessionToken;
    }
}
