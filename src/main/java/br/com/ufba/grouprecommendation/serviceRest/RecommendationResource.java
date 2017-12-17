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
import br.com.ufba.grouprecommendation.algoritmos.LeastMisery;
import br.com.ufba.grouprecommendation.algoritmos.Multiplicative;
import br.com.ufba.grouprecommendation.algoritmos.BordaCount;
import br.com.ufba.grouprecommendation.algoritmos.MostPleasure;
import br.com.ufba.grouprecommendation.model.User;
import br.com.ufba.grouprecommendation.model.Vote;
import br.com.ufba.grouprecommendation.dao.Data;
import br.com.ufba.grouprecommendation.model.Recomendacao;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;

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
    @Path("/recommenderIndividual/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRecommendation(@PathParam("id") int id)  {
        Data createData = new Data();
        AlgorithmsFactory factory = new AlgorithmsFactory();
        List<User> listUser = createData.getLastVotoTemp();
        Gson gson = new Gson();
        Recomendacao recomendacao = new Recomendacao();
        
        
        switch (id) {
             case 1: 
                AverageWithoutMisery averageWith = (AverageWithoutMisery) factory.getAlgorithm(AlgorithmsType.Type.AverageWithoutMisery);
                Vote voteaverageWith = averageWith.GetResult(listUser, 1);
                
                recomendacao.setNameAlgorithms("AverageWithoutMisery");
                recomendacao.setConsenso(voteaverageWith);
                recomendacao.setTimeStamp(Timestamp.valueOf( LocalDateTime.now()));
                        
                        
                return gson.toJson(recomendacao);
                
             case 2:  
                BordaCount borderCount = (BordaCount) factory.getAlgorithm(AlgorithmsType.Type.BorderCount);
                Vote voteborderCount = borderCount.GetResult(listUser);
                
                recomendacao.setNameAlgorithms("BorderCount");
                recomendacao.setConsenso(voteborderCount);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
                        
                        
                return gson.toJson(recomendacao);
             case 3:
                LeastMisery leastMisery = (LeastMisery) factory.getAlgorithm(AlgorithmsType.Type.LeastMisery);
                Vote voteleastMisery = leastMisery.GetResult(listUser);
                
                recomendacao.setNameAlgorithms("LeastMisery");
                recomendacao.setConsenso(voteleastMisery);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
                        
                        
                return gson.toJson(recomendacao);
                 
                 
             case 4:  
                MostPleasure mostPleasure = (MostPleasure) factory.getAlgorithm(AlgorithmsType.Type.MostPleasure);
                Vote voteMostPleasure = mostPleasure.GetResult(listUser);
                
                recomendacao.setNameAlgorithms("MostPleasure");
                recomendacao.setConsenso(voteMostPleasure);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
                        
                        
                return gson.toJson(recomendacao);
                 
             case 5:
                Multiplicative multiplicative = (Multiplicative) factory.getAlgorithm(AlgorithmsType.Type.Multiplicative);
                Vote voteMultiplicative = multiplicative.GetResult(listUser);
                
                recomendacao.setNameAlgorithms("Multiplicative");
                recomendacao.setConsenso(voteMultiplicative);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
                        
                        
                return gson.toJson(recomendacao);
        
            }
        
        recomendacao.setNameAlgorithms("Error parâmetro inválido");
        return  gson.toJson(recomendacao);
    }
    
   
    @GET
    @Path("/recommenderAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRecommendation(){
        Data createData = new Data();
        AlgorithmsFactory factory = new AlgorithmsFactory();
        List<User> listUser = createData.getLastVotoTemp();
        Gson gson = new Gson();
        List<Recomendacao> listRecomendacao = new ArrayList<>();   
        
        AverageWithoutMisery averageWith = (AverageWithoutMisery) factory.getAlgorithm(AlgorithmsType.Type.AverageWithoutMisery);
        Vote voteaverageWith = averageWith.GetResult(listUser, 1);
        Recomendacao avarageRecomendacao = new Recomendacao();
        
        avarageRecomendacao.setNameAlgorithms("AverageWithoutMisery");
        avarageRecomendacao.setConsenso(voteaverageWith);
        avarageRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
        
        listRecomendacao.add(avarageRecomendacao);
        
        BordaCount borderCount = (BordaCount) factory.getAlgorithm(AlgorithmsType.Type.BorderCount);
        Vote voteborderCount = borderCount.GetResult(listUser);
                
        Recomendacao borderRecomendacao = new Recomendacao();
        
        borderRecomendacao.setNameAlgorithms("BorderCount");
        borderRecomendacao.setConsenso(voteborderCount);
        borderRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
                
        listRecomendacao.add(borderRecomendacao);
        
        LeastMisery leastMisery = (LeastMisery) factory.getAlgorithm(AlgorithmsType.Type.LeastMisery);
        Vote voteleastMisery = leastMisery.GetResult(listUser);
        
        Recomendacao leastMiseryRecomendacao = new Recomendacao();
                
        leastMiseryRecomendacao.setNameAlgorithms("LeastMisery");
        leastMiseryRecomendacao.setConsenso(voteleastMisery);
        leastMiseryRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
        
        listRecomendacao.add(leastMiseryRecomendacao);     
        
        MostPleasure mostPleasure = (MostPleasure) factory.getAlgorithm(AlgorithmsType.Type.MostPleasure);
        Vote voteMostPleasure = mostPleasure.GetResult(listUser);
                
        Recomendacao mostPleasureRecomendacao = new Recomendacao();
        
        mostPleasureRecomendacao.setNameAlgorithms("MostPleasure");
        mostPleasureRecomendacao.setConsenso(voteMostPleasure);
        mostPleasureRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
        
        listRecomendacao.add(mostPleasureRecomendacao);     
        
        Multiplicative multiplicative = (Multiplicative) factory.getAlgorithm(AlgorithmsType.Type.Multiplicative);
        Vote voteMultiplicative = multiplicative.GetResult(listUser);
        
        Recomendacao multiplicativeRecomendacao = new Recomendacao();
        
        multiplicativeRecomendacao.setNameAlgorithms("Multiplicative");
        multiplicativeRecomendacao.setConsenso(voteMultiplicative);
        multiplicativeRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
        
        listRecomendacao.add(multiplicativeRecomendacao);     
        
        return gson.toJson(listRecomendacao);
       
    }
    
    
}
