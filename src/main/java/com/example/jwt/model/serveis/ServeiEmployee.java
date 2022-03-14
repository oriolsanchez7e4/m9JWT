package com.example.jwt.model.serveis;

import com.example.jwt.model.entitats.Employee;
import com.example.jwt.model.repositoris.RepositoriEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServeiEmployee {
    private final RepositoriEmployee repositoriEmployee;

    public List<Employee> llistarVideojocs(){
        return repositoriEmployee.findAll();
    }

    public Employee consultarPerId(Long id){
        return repositoriEmployee.findById(id).orElse(null);
    }

    public Employee eliminarVideojoc(Long id){
        Employee res=repositoriEmployee.findById(id).orElse(null);
        if(res!=null) repositoriEmployee.deleteById(id);
        return res;
    }

    public Employee afegirVideojoc(Employee v){
        return repositoriEmployee.save(v);
    }

    public Employee modificarVideojoc(Employee v){
        Employee res=null;
//        if(Employee.existsById(v.getId())) res=repositoriEmployee.save(v);
        return res;
    }


}
