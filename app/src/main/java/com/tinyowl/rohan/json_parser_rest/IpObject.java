package com.tinyowl.rohan.json_parser_rest;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by rohan on 28/07/15.
 */
public interface IpObject {

    @GET("/")
    List<String> stringList();

}
