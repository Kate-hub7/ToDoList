package com.edu_netcracker.sukhikh.Controllers;

import com.edu_netcracker.sukhikh.Controllers.Info.AffairInfo;
import com.edu_netcracker.sukhikh.Controllers.Info.UserCreationInfo;
import com.edu_netcracker.sukhikh.Data.Entities.AffairType;
import com.edu_netcracker.sukhikh.Data.Entities.UserEntity;
import com.edu_netcracker.sukhikh.Services.AffairService;
import com.edu_netcracker.sukhikh.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;
    private final AffairService affairService;


    public UserRestController(@Autowired UserService userService, @Autowired AffairService affairService) {
        this.userService = userService;
        this.affairService = affairService;
    }

    @RequestMapping(value = "add-affair", method = RequestMethod.POST)
    public ResponseEntity<Calendar> addAffair(@RequestBody AffairInfo info) throws ParseException {


        Calendar day = Calendar.getInstance();
        day.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(info.getDay()));

       // AffairType type = AffairType.values()[info.getType()];
        AffairType type = AffairType.valueOf(info.getType());

        affairService.addAffair(type, info.getName(), info.getDescription(), day, userService.getById(info.getUser()));
        return new ResponseEntity<>(day , HttpStatus.CREATED);
    }

    @RequestMapping(value = "create-user", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserCreationInfo info){

        UserEntity user = userService.createUser(info.getFirstName(), info.getLastName(), info.getEmail());
        return new ResponseEntity(user , HttpStatus.CREATED);

    }

    @RequestMapping(value = "delete-affair", method = RequestMethod.POST)
    public ResponseEntity deleteAffair(@RequestBody AffairInfo info) throws ParseException {

        affairService.deleteAffair(info.getId());
        return new ResponseEntity( HttpStatus.CREATED);
    }

    @RequestMapping(value = "finish-affair", method = RequestMethod.POST)
    public ResponseEntity finishAffair(@RequestBody AffairInfo info) throws ParseException {

        affairService.finishAffair(info.getId());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "find-all", method = RequestMethod.GET)
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "find-by-id/{id}")
    public UserEntity findByPathVar(@PathVariable Long id){
        return userService.getById(id);
    }

    @RequestMapping(value = "see-dairy/{id}")
    public String seeDairy(@PathVariable Long id){
        return userService.printDairy(id);
    }

    @RequestMapping(value = "see-dairy/{id}/{day}")
    public String seeDairy(@PathVariable Long id, @PathVariable String day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(day));

        return userService.printAffairDay(id, calendar);
    }
}
