package br.com.ufba.grouprecommendation.model;

import java.util.List;

public class User {
    
    
    private String Name; /* Nome do usuáiro */
    private String id;
    private List<Vote> Vote;  

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<Vote> getVote() {
        return Vote;
    }

    public void setVote(List<Vote> Vote) {
        this.Vote = Vote;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
        
       
        
        
}
