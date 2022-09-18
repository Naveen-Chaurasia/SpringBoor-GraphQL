package com.naveen.graphql.queries;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naveen.graphql.entities.Vehicle;
import com.naveen.graphql.services.VehicleService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Optional;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

    @Autowired
    private VehicleService vehicleService;

    public List<Vehicle> getVehicles(final int count) {
        return this.vehicleService.getAllVehicles(count);
    }

    
    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleService.getVehicle(id);
    }
}


//https://graphql.org/learn/queries/
