package com.eeb.dropwizardmongo.cms;

import com.eeb.dropwizardmongo.cms.resources.BasicPageResource;
import com.eeb.dropwizardmongo.health.MongoHealthCheck;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by eeb on 7/2/14.
 */
public class CMSApplication extends Application<CMSConfiguration> {


    @Override
    public void initialize(Bootstrap<CMSConfiguration> cmsConfigurationBootstrap) {

    }

    @Override
    public void run(CMSConfiguration cmsConfiguration, Environment environment) throws Exception {
        MongoClient mongoClient = cmsConfiguration.getMongoClientFactory().build(environment);
        DB db = cmsConfiguration.getMongoDBFactory().build(mongoClient);

        //Register Health Checks
        environment.healthChecks().register("MongoDB",new MongoHealthCheck(mongoClient));

        //Register Resources
       environment.jersey().register(new BasicPageResource());

    }
}
