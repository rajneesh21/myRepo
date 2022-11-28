package com.pms.user.controller;


import com.pms.user.model.UserEmail;
import com.pms.user.model.KeyCloakUser;
import com.pms.user.model.PatientEmergencyContact;
import com.pms.user.model.UserLogin;
import com.pms.user.model.Users;
import com.pms.user.service.PatientEmergencyContactService;
import com.pms.user.service.EmailServiceImpl;
import com.pms.user.service.UserService;
import com.pms.user.service.Utility;
import com.pms.user.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private EmailServiceImpl emailService;

    private final UserService userService;

    private final RestTemplate restTemplate;

    private final PatientEmergencyContactService contactService;

    @Value("${keyUrlReg}")
    private String keyCloakUrlReg;

    @Value("${keyUrlLogin}")
    private String keyCloakUrlLogin;


    public UserController(UserService userService, RestTemplate restTemplate, PatientEmergencyContactService contactService) {
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.contactService = contactService;
    }

    @GetMapping("/status")
    public String status() {
        return "pms-user running...!";
    }

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        try {
            //Before adding user in keycloak, try to check whether the user exists or not.
            if (user != null && user.getEmail() != null && validate(user.getEmail()) && userService.getUserByEmail(user.getEmail()) == null) {
                //hit key cloak /keyRegister
                String role = setRole(user);
                KeyCloakUser keyCloakUser = new KeyCloakUser(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        role);
                keyCloakUser = restTemplate.postForObject(keyCloakUrlReg, keyCloakUser, KeyCloakUser.class);
                Users dashBoardUser = userService.addUser(user);
                return ResponseEntity.ok(dashBoardUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    private String setRole(Users user) {
        String role="";
        if(user.getRoleId()==2){
            role="patient";
        }
        else if((user.getRoleId()==3)){
            role="doctor";
            //generate pass & set it
            user.setPassword(Utility.generatePassword());
        }
        else if((user.getRoleId()==4)){
            role="nurse";
            //generate pass & set it
            user.setPassword(Utility.generatePassword());
        }
        return role;
    }


    @PostMapping("/updateUser")
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        Users dashBoardUser = userService.updateUser(user);
        return dashBoardUser != null ? ResponseEntity.ok(dashBoardUser) :
                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }


    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@RequestBody UserLogin userLogin){
        try{
            return ResponseEntity.ok(restTemplate.postForObject(keyCloakUrlLogin, userLogin, Object.class));
        }catch(Exception exception){
            exception.printStackTrace();
        }
       return null;
    }

    /*@PostMapping("/forgetPassword/{emailId}")
    public ResponseEntity<?> forgetPassword(@RequestParam("emailId") String emailId){
        Users users = null;
        try{
            if (emailId!=null && userService.getUserByEmail(emailId)!=null){

            }
        }catch (Exception e){
            ResponseEntity.internalServerError();
        }
        return ResponseEntity.ok(users);
    }*/

    @GetMapping("/forgetPassword")
    public ResponseEntity<Boolean> forgetPassword(@RequestParam("emailId") String emailId){
        Users user = userService.getUserByEmail(emailId);
        try{
            if((emailId!=null || emailId.length()>3) && user!=null){
                UserEmail emailEntity = new UserEmail();
                emailEntity.setAuthCode(Utility.generatePassword());
                emailEntity.setEmailType(Constant.Email.Type.FORGETPASSWORDEMAIL);
                emailEntity.setUserId(user.getUserId().longValue());
                boolean isEmailSent = emailService.sendEmail(emailEntity,user);

                if(isEmailSent){
                    return ResponseEntity.ok(true);
                }else {
                    return ResponseEntity.ok(false);
                }
            }else{
                return ResponseEntity.ok(false);
            }
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/getUserDetail/{email}")
    public ResponseEntity<Users> getUserDetailsFromEmail(@PathVariable("email") String email) {
        Users user = null;
        if (email != null && validate(email)) {
            user = userService.getUserByEmail(email);
        }
        return user != null ? ResponseEntity.ok(user) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @GetMapping("/getUserInfo/{id}")
    public ResponseEntity<Users> getUserInfo(@PathVariable Integer id) {
        Users user = userService.getUser(id);
        return user != null ? ResponseEntity.ok(user) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/addEmergencyContact")
    public ResponseEntity<PatientEmergencyContact> addPatientEmergencyContact(@RequestBody PatientEmergencyContact contact) {
        PatientEmergencyContact emergencyContact = contactService.addPatientEmergencyContact(contact);
        return emergencyContact != null ? ResponseEntity.ok(emergencyContact) :
                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }

    @GetMapping("/getEmergencyContact/{id}")
    public ResponseEntity<PatientEmergencyContact> getPatientEmergencyContact(@PathVariable Integer id) {
        PatientEmergencyContact emergencyContact = contactService.getPatientEmergencyContact(id);
        return emergencyContact != null ? ResponseEntity.ok(emergencyContact) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/getAllDesignation")
    public ResponseEntity<List<String>> getAllDesignation() {
        List<String> allDesignation = userService.getAllDesignation();
        return !CollectionUtils.isEmpty(allDesignation) ? ResponseEntity.ok(allDesignation) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getDoctorByDesignation/{value}")
    public ResponseEntity<List<Users>> getDoctorByDesignation(@PathVariable String value) {
        List<Users> allDesignation = userService.getDoctorByDesignation(value);
        return !CollectionUtils.isEmpty(allDesignation) ? ResponseEntity.ok(allDesignation) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/getUserEmailDetail/{authPasscode}")
    public ResponseEntity<UserEmail> getUserEmailDetailsFromAuthCode(@PathVariable("authPasscode") String authPasscode) {
        UserEmail userEmail = null;
        if (authPasscode != null) {
            userEmail = userService.getUserByAuthPasscode(authPasscode);
        }
        return userEmail != null ? ResponseEntity.ok(userEmail) :
                ResponseEntity.status(HttpStatus.OK).build();
    }

}
