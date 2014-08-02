package com.eeb.dropwizardmongo.cms;

import com.eeb.dropwizardmongo.cms.resources.BasicPageResource;
import com.eeb.dropwizardmongo.health.MongoHealthCheck;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Created by eeb on 7/2/14.
 */
public class CMSApplication extends Application<CMSConfiguration> {


    @Override
    public void initialize(Bootstrap<CMSConfiguration> cmsConfigurationBootstrap) {
       cmsConfigurationBootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(CMSConfiguration cmsConfiguration, Environment environment) throws Exception {
        MongoClient mongoClient = cmsConfiguration.getMongoFactory().buildClient(environment);
        DB db = cmsConfiguration.getMongoFactory().buildDB(environment);

        //Register Health Checks
        environment.healthChecks().register("MongoDB",new MongoHealthCheck(mongoClient));

        //Register Resources
       environment.jersey().register(new BasicPageResource(db));

    }
}
