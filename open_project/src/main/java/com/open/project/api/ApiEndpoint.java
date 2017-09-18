package com.open.project.api;

import retrofit.Endpoint;

public class ApiEndpoint implements Endpoint {

    @Override
    public String getUrl() {

        /*LIVE*/
        return "https://appapi.airasiabig.com";

        /*STAGING*/
        //return "http://airasiabig.me-tech.com.my";

        //} else {
            /*return "http://airasiabig2.me-tech.com.my";*/
        //}
        /*PREPRODAPI*/
        //return "http://stgappapi.tbdbig.com";

    }

    @Override
    public String getName() {
        return "Production Endpoint";
    }
}

