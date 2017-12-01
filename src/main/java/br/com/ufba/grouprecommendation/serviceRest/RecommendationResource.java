/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufba.grouprecommendation.serviceRest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */

@Path("/recommendation")
public class RecommendationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RecommendationResource
     */
    public RecommendationResource() {
    
    }

    /**
     * Retrieves representation of an instance of br.com.ufba.grouprecommendation.serviceRest.RecommendationResource
     * @param id
     * @return an instance of java.lang.String
     */
    
    
    @GET
    @Path("/recommenderIndividual")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRecommendation(@QueryParam("id") int id) {
        if(id==1)
            return "VSF2";
        
        return "VSF";
    }
    
   
    @GET
    @Path("/recommenderAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRecommendation(){
        
        return "FDP";
    }
    
    
}
