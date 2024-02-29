package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.MedicalRepo;
import com.example.demo.model.Medical;

@Service
public class MedicalService {
    
    @Autowired
    MedicalRepo medicalrepo;
    
    public Medical addMedicine(Medical o){
        return medicalrepo.save(o);
    }

    public List<Medical> findMedicine(){
        return medicalrepo.findAll();
    }

    public Optional<Medical> getByMedicineId(int id){
        return medicalrepo.findById(id);
    }

    public Medical editMedical(int id,Medical o){
         Medical ob=medicalrepo.findById(id).orElse(null);
         if(ob!=null){
            ob.setMedicineBrand(o.getMedicineBrand());
            ob.setMedicineFor(o.getMedicineFor());
            ob.setMedicineName(o.getMedicineName());
            ob.setMedicinePrice(o.getMedicinePrice());
            ob.setExpiryDate(o.getExpiryDate());
            return medicalrepo.saveAndFlush(ob);
         }
         else return null;
         
    }

    public String deleteMedicine(int id){
        medicalrepo.deleteById(id);
        return "Medicine Deleted";
    }

   public List<Medical> pagination(int pageno,int pagesize){
    Pageable page=PageRequest.of(pageno, pagesize);
    return medicalrepo.findAll(page).getContent();
   }
   public List<Medical> pageandsort(int pageno,int pagesize,String field){
    Pageable page=PageRequest.of(pageno, pagesize,Sort.by(Sort.Direction.ASC,field));
    return medicalrepo.findAll(page).getContent();
   }

   public List<Medical>  sorting(String medicineBrand){
        Sort sort=Sort.by(Sort.Direction.ASC,medicineBrand);
        return medicalrepo.findAll(sort);
   }


}
