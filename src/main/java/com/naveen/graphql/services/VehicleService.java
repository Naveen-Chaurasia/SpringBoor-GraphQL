package com.naveen.graphql.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naveen.graphql.entities.Vehicle;
import com.naveen.graphql.repositories.VehicleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository ;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository ;
    }

    @Transactional
    public Vehicle createVehicle(final String type,final String modelCode, final String brandName, final String launchDate) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate(LocalDate.parse(launchDate));
        return this.vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles(final int count) {
        return this.vehicleRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleRepository.findById(id);
    }
}




//Spring creates proxies for classes that declare @Transactional on the class itself or on members. 
//The proxy is mostly invisible at runtime. It provides a way for Spring to inject behaviors before, after, or around method
//calls into the object being proxied. Transaction management is just one example of the behaviors that can be hooked in.
//Security checks are another. And you can provide your own, too, for things like logging. So when you annotate a method with
//@Transactional, Spring dynamically creates a proxy that implements the same interface(s) as the class you're annotating. And
//when clients make calls into your object, the calls are intercepted and the behaviors injected via the proxy mechanism.






//A transaction is an atomic operation that consists of one or more statements. It's atomic because all statements within this operation either succeed 
//(are committed) or fail (are rolled back), which means all or nothing. The letter ‘A' of the ACID properties represents the atomicity of transactions.
//
//Another critical thing to understand is that all statements in the InnoDB engine become a transaction, if not explicitly, then implicitly. Such a concept gets
// a lot harder to understand when we add concurrency to the equation. Then, we need to clarify another ACID property, the ‘I' of Isolation.
//
//Understanding the isolation level property is essential for us to be able to reason about trade-offs of performance vs. consistency guarantees. However, before going 
//into details about isolation level, remember that as all the statements in InnoDB are transactions, they can be committed or rolled back. If no transaction is specified,
// the database creates one, and based on the autocommit property, it may be committed or not