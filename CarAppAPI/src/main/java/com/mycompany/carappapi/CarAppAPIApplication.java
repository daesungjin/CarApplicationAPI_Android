package com.mycompany.carappapi;

import com.mycompany.carappapi.dao.CarDAO;
import com.mycompany.carappapi.dao.CarInfoDAO;
import com.mycompany.carappapi.dao.UserDAO;
import com.mycompany.carappapi.resources.CarInfoResource;
import com.mycompany.carappapi.resources.CarResource;
import com.mycompany.carappapi.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


public class CarAppAPIApplication extends Application<CarAppAPIConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CarAppAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "CarAppAPI";
    }

    @Override
    public void initialize(final Bootstrap<CarAppAPIConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CarAppAPIConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        CarDAO carDAO = jdbi.onDemand(CarDAO.class);
        UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        CarInfoDAO carInfoDAO =jdbi.onDemand(CarInfoDAO.class);
        CarResource carResource = new CarResource(carDAO);
        UserResource userResource = new UserResource(userDAO);
        CarInfoResource carInfoResource = new CarInfoResource(carInfoDAO);
        environment.jersey().register(carResource);
        environment.jersey().register(userResource);
        environment.jersey().register(carInfoResource);
    }

}
