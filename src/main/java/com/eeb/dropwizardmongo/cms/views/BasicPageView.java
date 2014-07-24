package com.eeb.dropwizardmongo.cms.views;

import com.eeb.dropwizardmongo.cms.api.BasicPage;
import io.dropwizard.views.View;

/**
 * Created by eeb on 7/23/14.
 */
public class BasicPageView extends View {

    private final BasicPage basicPage;

    public BasicPageView(BasicPage basicPage) {
        super("basicpage.mustache");
        this.basicPage = basicPage;
    }

    public BasicPage getBasicPage() {
        return basicPage;
    }
}
