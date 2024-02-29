package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Medical;
import com.example.demo.service.MedicalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class MedicalController {
    
    @Autowired
    MedicalService medicalservice;

    @PostMapping("/post")
    public Medical postMethodName(@RequestBody Medical M){
        return medicalservice.addMedicine(M);
    }
    @GetMapping("/get1")
    public ResponseEntity<List<Medical>> getMethodName() {
        return new ResponseEntity<>(medicalservice.findMedicine(),HttpStatus.OK);
        
    }
    @GetMapping("get2/{id}")
    public ResponseEntity<Optional<Medical>> getMethodName(@PathVariable int id) {
        return new ResponseEntity<>(medicalservice.getByMedicineId(id),HttpStatus.OK);
    }
    
    @PutMapping("/put/{medicineId}")
    public ResponseEntity<Medical> putMethodName(@PathVariable int medicineId, @RequestBody Medical ent) {
        return new ResponseEntity<>(medicalservice.editMedical(medicineId, ent),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable int id){
        return new ResponseEntity<>(medicalservice.deleteMedicine(id),HttpStatus.OK);
    }
    @GetMapping("/pagination/{pageno}/{pagesize}")
    public List<Medical> getPagination(@PathVariable int pageno,@PathVariable int pagesize ){
        return medicalservice.pagination(pageno, pagesize);
    }
    @GetMapping("/pagination/{pageno}/{pagesize}/{field}")
    public List<Medical> getPageandSort(@PathVariable int pageno,@PathVariable int pagesize,@PathVariable String field ){
        return medicalservice.pageandsort(pageno, pagesize,field);
    }
    @GetMapping("/sorting/{field}")
    public List<Medical> getSorting(@PathVariable String field ){
        return medicalservice.sorting(field);
    }

}
