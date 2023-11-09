package be.condorcet.projetsb.webservices;

import be.condorcet.projetsb.services.InterfSessionCoursService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/sessionCours")
public class RestSessionCours {
    private InterfSessionCoursService sessionCoursServiceImpl;

}
