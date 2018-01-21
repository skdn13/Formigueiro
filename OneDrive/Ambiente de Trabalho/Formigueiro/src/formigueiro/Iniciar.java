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
     * @throws recursos.exceptions.ElementNotFoundException
     */
    public static void main(String[] args) throws ElementNotFoundException {
        //Inicializar variáveis
        Sala sala1 = new Sala(1, 350, 300, "Sala 1");
        Formigueiro formigueiro = new Formigueiro(sala1);
        Sala sala2 = (Sala) formigueiro.criaSala(2, "Sala 2", 200, 200);
        Processamento sala3 = (Processamento) formigueiro.criaProcessamento(3, "Sala 3", 750, 250);
        Sala sala4 = (Sala) formigueiro.criaSala(4, "Sala 4", 250, 500);
        Silo sala5 = (Silo) formigueiro.criaSilo(5, "Sala 5", 50, 100);
        Processamento sala6 = (Processamento) formigueiro.criaProcessamento(6, "Sala 6", 400, 100);

        //Adicionar formigas e comida às salas
        sala5.guardaComida(formigueiro.criaComida(1, 2));
        sala5.guardaComida(formigueiro.criaComida(2, 2));
        sala5.guardaComida(formigueiro.criaComida(3, 5));
        sala5.entraFormiga(formigueiro.criaFormiga(1, 4));
        sala5.entraFormiga(formigueiro.criaFormiga(2, 5));

        //Adicionar as salas ao formigueiro (Exceto a sala de entrada)
        formigueiro.addSala(sala2);
        formigueiro.addSala(sala3);
        formigueiro.addSala(sala4);
        formigueiro.addSala(sala5);
        formigueiro.addSala(sala6);

        //Criar túneis para ligar as salas
        formigueiro.ligaSala(formigueiro.getSala(1), formigueiro.getSala(2), 2);
        formigueiro.ligaSala(formigueiro.getSala(2), formigueiro.getSala(6), 2);
        formigueiro.ligaSala(formigueiro.getSala(2), formigueiro.getSala(3), 2);
        formigueiro.ligaSala(formigueiro.getSala(2), formigueiro.getSala(5), 2);
        formigueiro.ligaSala(formigueiro.getSala(2), formigueiro.getSala(4), 2);
        formigueiro.ligaSala(formigueiro.getSala(3), formigueiro.getSala(6), 2);
        formigueiro.ligaSala(formigueiro.getSala(4), formigueiro.getSala(3), 2);
        formigueiro.ligaSala(formigueiro.getSala(4), formigueiro.getSala(5), 2);
        formigueiro.ligaSala(formigueiro.getSala(5), formigueiro.getSala(6), 2);
        //Desenhar o caminho na JFrame
        formigueiro.printNetwork();
        FormigueiroViewer janela = new FormigueiroViewer(formigueiro);
        janela.pintaCaminho(formigueiro.iteratorCarregaEMoveFormigaShortestPath(1, 4));

    }

}
