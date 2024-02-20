package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.House;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {
    private final HouseRepository houseRepository;


    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping
    public ResponseEntity<List<House>> getAll() {
        var houses = houseRepository.findAll();
        if (houses.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(houses);
    }

    @GetMapping("/{name}")
    public ResponseEntity<House> get(@PathVariable String  name) {
        var house = this.houseRepository.findById(name);
        if (house.isPresent()) {
            return ResponseEntity.ok(house.get());
        }
        return ResponseEntity.notFound().build();


    }


}
