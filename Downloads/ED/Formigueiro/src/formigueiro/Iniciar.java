/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import recursos.exceptions.ElementNotFoundException;
import recursos.utils.FormigueiroViewer;

/**
 *
 * @author pmms8
 */
public class Iniciar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ElementNotFoundException {
        Sala entrada = new Sala(1, 300, 400, "Sala 1");
        Sala sala2 = new Sala(2, 400, 200, "Sala 2");
        Sala sala3 = new Sala(3, 800, 100, "Sala 3");
        Sala sala4 = new Sala(4, 300, 50, "Sala 4");
        Sala sala5 = new Sala(5, 600, 300, "Sala 5");
        Formigueiro formigueiro = new Formigueiro(entrada, 900, 500);
        entrada.entraFormiga(formigueiro.criaFormiga(1, 10));
        sala2.entraFormiga(formigueiro.criaFormiga(2, 10));
        sala3.entraFormiga(formigueiro.criaFormiga(3, 10));
        sala4.entraFormiga(formigueiro.criaFormiga(4, 5));
        sala5.entraFormiga(formigueiro.criaFormiga(5, 10));
        formigueiro.addSala(sala2);
        formigueiro.addSala(sala3);
        formigueiro.addSala(sala4);
        formigueiro.addSala(sala5);
        formigueiro.ligaSala(sala3, sala2, 5);
        FormigueiroViewer janela = new FormigueiroViewer(formigueiro);
       janela.pintaCaminho(formigueiro.iteratorCarregaEMoveFormigaShortestPath(1,2));
    }

}
