package com.eeb.dropwizardmongo.cms.test;

import com.eeb.dropwizardmongo.cms.api.Author;
import com.eeb.dropwizardmongo.cms.api.BasicDetail;
import com.eeb.dropwizardmongo.cms.api.BasicPage;
import com.eeb.dropwizardmongo.cms.api.Metadata;

import java.util.Arrays;

/**
 * Created by eeb on 7/23/14.
 */
public class Mocks {


    public static class MockBasicPage extends BasicPage {

        public MockBasicPage() {
            Author a = new Author();
            a.setName("Tom");

            BasicDetail bd = new BasicDetail();
            bd.setText("asdf");

            Metadata md = new Metadata();
            md.setType("basic-page");
            md.setSection("my-photos");
            md.setSlug("about");
            md.setTitle("About Us");
            md.setAuthor(a);
            md.setTags(Arrays.asList(new String[]{"a", "b"}));
            md.setDetail(bd);



            this.setMetadata(md);
        }

    }

}
