package com.eeb.dropwizardmongo.cms;

import com.eeb.dropwizardmongo.cms.resources.BasicPageResource;
import com.eeb.dropwizardmongo.health.MongoHealthCheck;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Created by eeb on 7/2/14.
 */
public class CMSApplication extends Application<CMSConfiguration> {

    public static void main(String[] args) throws Exception {
        new CMSApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<CMSConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
//        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
//        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
//        bootstrap.addBundle(new AssetsBundle("/assets/fonts", "/fonts", null, "fonts"));
//        bootstrap.addBundle(new AssetsBundle("/assets/img", "/img", null, "img"));
    }

    @Override
    public void run(CMSConfiguration cmsConfiguration, Environment environment) throws Exception {
        MongoClient mongoClient = cmsConfiguration.getMongoFactory().buildClient(environment);
        DB db = cmsConfiguration.getMongoFactory().buildDB(environment);

        //Allows assets servlet to be the root
        //environment.jersey().setUrlPattern("/api/*");

        //Register Health Checks
        environment.healthChecks().register("MongoDB", new MongoHealthCheck(mongoClient));

        //Register Resources
        environment.jersey().register(new BasicPageResource(db));

    }
}
