package miu.videokabbee.controller;
import lombok.RequiredArgsConstructor;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.dto.LogInRequest;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private UserInterfaceService userInterfaceService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateToken(@RequestBody LogInRequest request) {
        var user = userInterfaceService.authenticate(request.getEmail(), request.getPassword());

            return user.equals("not authenticated")?
                    new ResponseEntity<>(user, HttpStatus.NOT_FOUND):
                    new ResponseEntity<>(user,HttpStatus.OK);
        }

   }


