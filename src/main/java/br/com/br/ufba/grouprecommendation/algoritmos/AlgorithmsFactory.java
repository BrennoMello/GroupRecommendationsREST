package br.com.br.ufba.grouprecommendation.algoritmos;

import br.com.br.ufba.grouprecommendation.algoritmos.AlgorithmsType.Type;

public class AlgorithmsFactory {

         public Algorithms getAlgorithm(AlgorithmsType.Type T) {
        
        if (T == Type.LeastMisery) {
              return new LeastMisery();
        } else if (T == Type.AverageWithoutMisery) {
              return new AverageWithoutMisery();
        } else if (T == Type.Multiplicative) {
              return new Multiplicative();
        } else if (T == Type.MostPleasure) {
              return new MostPleasure();
        } else {
            return null;
        }
    }
}