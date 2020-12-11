package Temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthoritiesController {

    @Autowired
    AuthoritiesService authoritiesService;

    @DeleteMapping("/delete/{username}")
    public String deleteAuth(@PathVariable String username){
         authoritiesService.deleteAuth(username);
         return "rol silindi";
    }
    @PutMapping("/update")
    public AuthoritiesDTO updateAuth(@RequestBody AuthoritiesDTO auth){ authoritiesService.updateAuth(auth);
    return auth;
    }

    @GetMapping("/id/{id}")
    public AuthoritiesDTO AuthById(@PathVariable String id){
        return authoritiesService.getAuthById(id);
    }

    @GetMapping("/listall")
    public List<AuthoritiesDTO> authoritiesList(){
        return authoritiesService.authoritiesList();
    }

    @PostMapping("/add")
    public String addAuth(@RequestBody AuthoritiesDTO authDto){
        authoritiesService.addAuth(authDto);
        return "rol eklendi";
    }
}