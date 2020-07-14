package com.ttrung.supershop.urp.api.facebook;

import com.ttrung.supershop.urp.api.ApiBinding;
import com.ttrung.supershop.urp.dto.Profile;

public class FacebookService extends ApiBinding {

    private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v2.12";

    public FacebookService(String accessToken) {
        super(accessToken);
    }

    public Profile getProfile() {
        return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me?fields=id,name,email", Profile.class);
    }
}
