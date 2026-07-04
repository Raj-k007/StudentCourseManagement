package com.example.studentcoursemanagement;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Async("taskExecutor")
    public void sendEmail(String email){
        System.out.println("Sending mail to "+ email);

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Sent email to "+email);
    }
}
