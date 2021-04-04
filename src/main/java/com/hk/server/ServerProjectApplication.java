package com.hk.server;

import com.hk.server.dao.AdminMapper;
import com.hk.server.entity.Admin;
import com.hk.server.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ServletComponentScan("com/hk/server/Listener")
@SpringBootApplication
@RestController
public class ServerProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerProjectApplication.class, args);
    }


}
