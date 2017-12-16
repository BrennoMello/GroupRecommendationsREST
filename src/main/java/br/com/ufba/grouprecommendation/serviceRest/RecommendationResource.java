/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufba.grouprecommendation.serviceRest;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/* Testes */
import br.com.ufba.grouprecommendation.algoritmos.AlgorithmsFactory;
import br.com.ufba.grouprecommendation.algoritmos.AlgorithmsType;
import br.com.ufba.grouprecommendation.algoritmos.AverageWithoutMisery;
import br.com.ufba.grouprecommendation.algoritmos.BordaCount;
import br.com.ufba.grouprecommendation.algoritmos.LeastMisery;
import br.com.ufba.grouprecommendation.algoritmos.MostPleasure;
import br.com.ufba.grouprecommendation.algoritmos.Multiplicative;
import br.com.ufba.grouprecommendation.model.User;
import br.com.ufba.grouprecommendation.model.Vote;
import br.com.ufba.grouprecommendation.dao.Data;
import br.com.ufba.grouprecommendation.dao.MySQLObject;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;



        


/**
 * REST Web Service
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */

@Path("/recommendation")
public class RecommendationResource {

    @Context
    private UriInfo context;
    
    private List<User> ListData;
    

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
    public String getRecommendation(@QueryParam("id") int id) throws SQLException, ParseException {
        Data createData = new Data();
        AlgorithmsFactory factory = new AlgorithmsFactory();
        List<User> listUser = createData.getLastVotoTemp();
        
        switch (id) {
             case 1: 
                AverageWithoutMisery averageWith = (AverageWithoutMisery) factory.getAlgorithm(AlgorithmsType.Type.AverageWithoutMisery);
                Vote voteaverageWith = averageWith.GetResult(listUser, 1);
                
                return String.valueOf(voteaverageWith.getScaleValue() + " " + voteaverageWith.getVote());
                
             case 2:  
                BordaCount bordaCount = (BordaCount) factory.getAlgorithm(AlgorithmsType.Type.BorderCount);
                Vote votebordaCount = bordaCount.GetResult(listUser);
                 
                return String.valueOf(votebordaCount.getScaleValue());
             case 3:
                 LeastMisery leastMisery = (LeastMisery) factory.getAlgorithm(AlgorithmsType.Type.LeastMisery);
                 Vote voteleastMisery = leastMisery.GetResult(listUser);
                 
                 return String.valueOf(voteleastMisery.getVote());
             case 4:
                 MostPleasure mostPleasure = (MostPleasure) factory.getAlgorithm(AlgorithmsType.Type.MostPleasure);
                 Vote votemostPleasure = mostPleasure.GetResult(listUser);
                 
                 return String.valueOf(voteleastMisery.getVote());
        }
        
        return "VSF";
    }
    
   
    @GET
    @Path("/recommenderAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRecommendation(){
            
        /* TESTE DADOS BANCO */
        Data e = new Data();
        try {
            ListData =  e.getMySQLSyntheticData(Integer.valueOf(2)); /* RECUPERAR DADOS DAS ULTIMAS DUAS HORAS */
        } catch (SQLException ex) {
            Logger.getLogger(RecommendationResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
       
        
        return "ok";
        
        
        
//        AlgorithmsFactory f = new AlgorithmsFactory();
//        Vote R;
//        Multiplicative M;
//        M = (Multiplicative) f.getAlgorithm(AlgorithmsType.Type.Multiplicative);
//        return M.GetAll(ListData).toString();

               
        //return "FDP";
    }
    
    
}
