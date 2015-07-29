package com.tinyowl.rohan.json_parser_rest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by rohan on 28/07/15.
 */
public interface GitHubService {

    //public void getJournalInfo(@Path("url") String urlJournal, Callback<Journal> callback);

    @GET("/repos/{owner}/{repo}/contributors")
    void contributors(@Path("owner") String owner, @Path("repo") String repo, Callback<List<Contributor>> callback);

}
