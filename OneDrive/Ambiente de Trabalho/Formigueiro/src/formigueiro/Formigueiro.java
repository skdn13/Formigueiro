/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import colecoes.ArrayUnorderedList;
import colecoes.Network;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.FormigaCheiaException;
import recursos.exceptions.ProcessedException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.IFormigueiro;
import recursos.interfaces.IPair;
import recursos.interfaces.IProcessamento;
import recursos.interfaces.ISala;
import recursos.interfaces.ISilo;
import recursos.interfaces.ITunel;

/**
 *
 * @author pmms8
 */
public class Formigueiro implements IFormigueiro {

    private Sala entrada;
    private int tunelID;
    private colecoes.ArrayUnorderedList<Sala> salas;
    private colecoes.ArrayUnorderedList<Formiga> formigas;
    private colecoes.Network<ISala> network;

    public Formigueiro(Sala entrada) throws ElementNotFoundException {
        formigas = new colecoes.ArrayUnorderedList<>();
        salas = new colecoes.ArrayUnorderedList<>();
        this.network = new Network<>();
        this.entrada = entrada;
        this.salas.addToRear((Sala) entrada);
        this.network.addVertex(getSalaLista(entrada.getId()));
        this.tunelID = 0;
    }

    @Override
    public ISala getEntrada() {
        return this.entrada;
    }

    @Override
    public void setEntrada(ISala isala) {
        this.entrada = (Sala) isala;
    }

    @Override
    public Iterator<ISala> iterator() {
        return (Iterator<ISala>) this.salas.getIterator();
    }

    @Override
    public Iterator<ISala> iteratorBFS(ISala isala) {
        return network.iteratorBFS(isala);
    }

    @Override
    public Iterator<ISala> iteratorBFS() {
        return network.iteratorBFS(entrada);
    }

    @Override
    public void addSala(ISala isala) {
        try {
            this.network.addVertex(this.getSalaLista(isala.getId()));
        } catch (ElementNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void ligaSala(ISala isala, ISala isala1, int i) {
        int distance = (int) Math.hypot(isala.getX() - isala1.getX(), isala.getY() - isala1.getY());
        Tunel tunel = new Tunel(distance, i, this.tunelID);
        try {
            this.network.addEdge(isala, isala1, tunel);
            this.tunelID++;
        } catch (ElementNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void printNetwork() {
        System.out.println(this.network.toString());
    }

    @Override
    public int getMaxY() {
        Iterator<ISala> salas = (Iterator<ISala>) this.network.iteratorVertex();
        int maxY = 0;
        while (salas.hasNext()) {
            ISala next = salas.next();
            if (next.getY() > maxY) {
                maxY = next.getY();
            }
        }
        return maxY;
    }

    @Override
    public int getMaxX() {
        Iterator<ISala> salas = (Iterator<ISala>) this.network.iteratorVertex();
        int maxX = 0;
        while (salas.hasNext()) {
            ISala next = salas.next();
            if (next.getX() > maxX) {
                maxX = next.getX();
            }
        }
        return maxX;
    }

    @Override
    public Iterator<IPair<ISala, ITunel>> ligacoesDe(ISala isala) {
        ArrayUnorderedList<IPair<ISala, ITunel>> iterador = new ArrayUnorderedList<>();
        Iterator<ISala> vizinhos = this.network.iteratorNeighbour(this.network.getElementIndex(isala));
        while (vizinhos.hasNext()) {
            Sala next = (Sala) vizinhos.next();
            Tunel tunel = (Tunel) this.network.getElement(this.network.getElementIndex(isala), this.network.getElementIndex(next));
            if (tunel.getDistance() != Double.POSITIVE_INFINITY) {
                Pair pair = new Pair(next, tunel);
                iterador.addToRear(pair);
            }
        }
        return (Iterator<IPair<ISala, ITunel>>) iterador.getIterator();
    }

    @Override
    public ISala getSalaFormiga(int i) throws ElementNotFoundException {

        Formiga formigaProcura = (Formiga) this.getFormiga(i);
        Iterator<ISala> salas = (Iterator<ISala>) this.salas.getIterator();
        while (salas.hasNext()) {
            Sala next = (Sala) salas.next();
            Iterator<IFormiga> iterator = (Iterator<IFormiga>) next.listaFormigas().iterator();
            while (iterator.hasNext()) {
                Formiga formigaIterador = (Formiga) iterator.next();
                if (formigaProcura.equals(formigaIterador)) {
                    return next;
                }
            }
        }
        throw new ElementNotFoundException("Não existe nenhum sala com esse id");
    }

    @Override
    public IFormiga getFormiga(int i) throws ElementNotFoundException {
        Iterator<IFormiga> formigasIt = (Iterator<IFormiga>) this.formigas.getIterator();
        while (formigasIt.hasNext()) {
            Formiga next = (Formiga) formigasIt.next();
            if (i == next.getId()) {
                return next;
            }
        }
        throw new ElementNotFoundException("Formiga não existe!");
    }

    @Override
    public ISala getSala(int i) throws ElementNotFoundException {

        Iterator<ISala> salas = (Iterator<ISala>) this.network.iteratorVertex();

        while (salas.hasNext()) {
            ISala next = salas.next();

            if (i == next.getId()) {
                return next;
            }
        }
        return null;
    }

    public ISala getSalaLista(int i) throws ElementNotFoundException {

        Iterator<ISala> salas = (Iterator<ISala>) this.salas.getIterator();

        while (salas.hasNext()) {
            ISala next = salas.next();

            if (i == next.getId()) {
                return next;
            }
        }
        return null;
    }

    @Override
    public boolean vizinhos(ISala isala, ISala isala1) {
        return this.network.getElement(this.network.getElementIndex(isala), this.network.getElementIndex(isala1)) != null;
    }

    @Override
    public Iterator<ISala> iteratorShortestPath(int i, int i1) throws ElementNotFoundException {
        int position1 = i - 1, position2 = i1 - 1;
        if (i < 1) {
            throw new ElementNotFoundException("A sala1 não existe");
        } else if (i1 < 1) {
            throw new ElementNotFoundException("A sala2 não existe");
        } else {
            return this.network.iteratorShortestPath(position1, position2);
        }
    }

    @Override
    public Iterator<ISala> iteratorMoveFormigaShortestPath(int idFormiga, int i1) throws ElementNotFoundException {
        int destino = i1 - 1;
        if (idFormiga < 1) {
            throw new ElementNotFoundException("A formiga não existe");
        } else if (i1 < 1) {
            throw new ElementNotFoundException("A sala de destino não existe");
        } else {
            Formiga formiga = (Formiga) getFormiga(idFormiga);
            Sala origem = (Sala) this.getSalaFormiga(idFormiga);
            origem.saiFormiga(idFormiga);
            Sala salaDestino = (Sala) this.getSala(i1);
            salaDestino.entraFormiga(formiga);
            return this.network.iteratorShortestPath(origem.getId() - 1, destino);
        }
    }

    @Override
    public Iterator<ISala> iteratorCarregaEMoveFormigaShortestPath(int idFormiga, int i1) throws ElementNotFoundException {
        Iterator<ISala> iterador = null;
        if (idFormiga < 1) {
            throw new ElementNotFoundException("A formiga não existe");
        } else if (i1 < 1) {
            throw new ElementNotFoundException("A sala de destino não existe");
        } else if (this.getSala(i1) instanceof Processamento) {
            try {
                iterador = this.moveFormigaProcessamento(idFormiga, i1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return iterador;
        } else if (this.getSala(i1) instanceof Silo) {
            try {
                iterador = this.moveFormigaSilo(idFormiga, i1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return iterador;
        } else {
            try {
                iterador = this.moveFormigaSala(idFormiga, i1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return iterador;
        }
    }

    public Iterator<ISala> moveFormigaProcessamento(int idFormiga, int idSalaDestino) throws ElementNotFoundException, Exception {

        Formiga formiga = (Formiga) getFormiga(idFormiga);
        Processamento salaDestino = (Processamento) this.getSala(idSalaDestino);
        Iterator<ISala> iteradorFinal = null;
        int cargaAtual = 0;
        if (this.getSalaFormiga(idFormiga) instanceof Silo) {
            Silo origem = (Silo) this.getSalaFormiga(idFormiga);
            int comidas = origem.getNumeroComidas();
            if (origem.getNumeroComidas() >= formiga.getCapacidadeCarga()) {
                try {
                    iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCapacidadeCarga());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                formiga.setCarga(origem.getNumeroComidas());
                try {
                    iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, origem.getNumeroComidas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            Iterator<IComida> it = origem.iteratorComida();
            while (it.hasNext() && formiga.getCapacidadeCarga() != 0) {
                Comida comida = (Comida) it.next();
                try {
                    formiga.addComida(comida);
                    if (comidas >= formiga.getCapacidadeCarga()) {
                        cargaAtual++;
                        formiga.setCarga(cargaAtual);
                    }
                    origem.retiraComida();
                    try {
                        if (iteradorFinal.hasNext()) {
                            salaDestino.acrescentaComida(comida);
                        }
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                } catch (FormigaCheiaException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            if (!iteradorFinal.hasNext()) {
                throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
            }
            origem.saiFormiga(idFormiga);
        } else if (this.getSalaFormiga(idFormiga) instanceof Processamento) {
            Processamento origem = (Processamento) this.getSalaFormiga(idFormiga);
            int comidas = origem.getNumeroComidas();
            if (formiga.getCapacidadeCarga() == origem.getNumeroComidas()) {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCapacidadeCarga());
            } else {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, origem.getNumeroComidas());
            }
            if (!iteradorFinal.hasNext()) {
                throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
            }
            Iterator<IComida> it = origem.iteratorComida();
            while (it.hasNext() && formiga.getCapacidadeCarga() != 0) {
                Comida comida = (Comida) it.next();
                try {
                    formiga.addComida(comida);
                    if (comidas >= formiga.getCapacidadeCarga()) {
                        cargaAtual++;
                        formiga.setCarga(cargaAtual);
                    }
                    origem.retiraComida();
                    try {
                        if (iteradorFinal.hasNext()) {
                            try {
                                origem.getProximaComida();
                            } catch (ProcessedException ex) {
                                ex.printStackTrace();
                            }
                            salaDestino.acrescentaComida(comida);
                        }
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                } catch (FormigaCheiaException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            origem.saiFormiga(idFormiga);
        } else if (this.getSalaFormiga(idFormiga) instanceof Sala) {
            Sala origem = (Sala) this.getSalaFormiga(idFormiga);
            iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCarga());
            origem.saiFormiga(idFormiga);
        }
        salaDestino.entraFormiga(formiga);
        formiga.removeTodasAsComidas();
        formiga.setCarga(0);
        return iteradorFinal;
    }

    public Iterator<ISala> moveFormigaSala(int idFormiga, int idSalaDestino) throws ElementNotFoundException, Exception {
        Formiga formiga = (Formiga) getFormiga(idFormiga);
        Sala salaDestino = (Sala) this.getSala(idSalaDestino);
        Iterator<ISala> iteradorFinal = null;
        int cargaAtual = 0;
        if (this.getSalaFormiga(idFormiga) instanceof Silo) {
            Silo origem = (Silo) this.getSalaFormiga(idFormiga);
            int comidas = origem.getNumeroComidas();
            if (origem.getNumeroComidas() >= formiga.getCapacidadeCarga()) {
                try {
                    iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCapacidadeCarga());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                formiga.setCarga(origem.getNumeroComidas());
                try {
                    iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, origem.getNumeroComidas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            Iterator<IComida> it = origem.iteratorComida();
            while (it.hasNext() && formiga.getCapacidadeCarga() != 0) {
                Comida comida = (Comida) it.next();
                try {
                    formiga.addComida(comida);
                    if (comidas >= formiga.getCapacidadeCarga()) {
                        cargaAtual++;
                        formiga.setCarga(cargaAtual);
                    }
                    origem.retiraComida();
                } catch (FormigaCheiaException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            if (!iteradorFinal.hasNext()) {
                throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
            }
            origem.saiFormiga(idFormiga);
        } else if (this.getSalaFormiga(idFormiga) instanceof Processamento) {
            Processamento origem = (Processamento) this.getSalaFormiga(idFormiga);
            int comidas = origem.getNumeroComidas();
            if (formiga.getCapacidadeCarga() == origem.getNumeroComidas()) {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCapacidadeCarga());
            } else {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, origem.getNumeroComidas());
            }
            if (!iteradorFinal.hasNext()) {
                throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
            }
            Iterator<IComida> it = origem.iteratorComida();
            while (it.hasNext() && formiga.getCapacidadeCarga() != 0) {
                Comida comida = (Comida) it.next();
                try {
                    formiga.addComida(comida);
                    if (comidas >= formiga.getCapacidadeCarga()) {
                        cargaAtual++;
                        formiga.setCarga(cargaAtual);
                    }
                    origem.retiraComida();
                    try {
                        if (iteradorFinal.hasNext()) {
                            try {
                                origem.getProximaComida();
                            } catch (ProcessedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                } catch (FormigaCheiaException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            origem.saiFormiga(idFormiga);
        } else if (this.getSalaFormiga(idFormiga) instanceof Sala) {
            Sala origem = (Sala) this.getSalaFormiga(idFormiga);
            iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCarga());
            origem.saiFormiga(idFormiga);
        }
        salaDestino.entraFormiga(formiga);
        formiga.removeTodasAsComidas();
        return iteradorFinal;
    }

    public Iterator<ISala> moveFormigaSilo(int idFormiga, int idSalaDestino) throws ElementNotFoundException, Exception {
        Formiga formiga = (Formiga) getFormiga(idFormiga);
        Silo salaDestino = (Silo) this.getSala(idSalaDestino);
        Iterator<ISala> iteradorFinal = null;
        int cargaAtual = 0;
        if (this.getSalaFormiga(idFormiga) instanceof Silo) {
            Silo origem = (Silo) this.getSalaFormiga(idFormiga);
            int comidas = origem.getNumeroComidas();
            if (origem.getNumeroComidas() >= formiga.getCapacidadeCarga()) {
                try {
                    iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCapacidadeCarga());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                formiga.setCarga(origem.getNumeroComidas());
                try {
                    iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, origem.getNumeroComidas());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            Iterator<IComida> it = origem.iteratorComida();
            while (it.hasNext() && formiga.getCapacidadeCarga() != 0) {
                Comida comida = (Comida) it.next();
                try {
                    formiga.addComida(comida);
                    if (comidas >= formiga.getCapacidadeCarga()) {
                        cargaAtual++;
                        formiga.setCarga(cargaAtual);
                    }
                    origem.retiraComida();
                    try {
                        if (iteradorFinal.hasNext()) {
                            salaDestino.guardaComida(comida);
                        }
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                } catch (FormigaCheiaException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            if (!iteradorFinal.hasNext()) {
                throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
            }
            origem.saiFormiga(idFormiga);
        } else if (this.getSalaFormiga(idFormiga) instanceof Processamento) {
            Processamento origem = (Processamento) this.getSalaFormiga(idFormiga);
            int comidas = origem.getNumeroComidas();
            if (formiga.getCapacidadeCarga() == origem.getNumeroComidas()) {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCapacidadeCarga());
            } else {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, origem.getNumeroComidas());
            }
            if (!iteradorFinal.hasNext()) {
                throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
            }
            Iterator<IComida> it = origem.iteratorComida();
            while (it.hasNext() && formiga.getCapacidadeCarga() != 0) {
                Comida comida = (Comida) it.next();
                try {
                    formiga.addComida(comida);
                    if (comidas >= formiga.getCapacidadeCarga()) {
                        cargaAtual++;
                        formiga.setCarga(cargaAtual);
                    }
                    origem.retiraComida();
                    try {
                        if (iteradorFinal.hasNext()) {
                            try {
                                origem.getProximaComida();
                            } catch (ProcessedException ex) {
                                ex.printStackTrace();
                            }
                            salaDestino.guardaComida(comida);
                        }
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                } catch (FormigaCheiaException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            origem.saiFormiga(idFormiga);
        } else if (this.getSalaFormiga(idFormiga) instanceof Sala) {
            Sala origem = (Sala) this.getSalaFormiga(idFormiga);
            int comidas = formiga.getCarga();
            if (formiga.getCapacidadeCarga() == formiga.getCarga()) {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCapacidadeCarga());
            } else {
                iteradorFinal = this.network.iteratorShortestPathTunel(this.getSalaFormiga(idFormiga).getId() - 1, idSalaDestino - 1, formiga.getCarga());
            }
            if (!iteradorFinal.hasNext()) {
                throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
            }
            if (formiga.getCarga() != 0 && formiga.getCapacidadeCarga() != 0) {
                for (int i = 0; i < formiga.getCarga(); i++) {
                    Comida c = (Comida) formiga.getComida(i);
                    salaDestino.guardaComida(c);
                }
            }
            origem.saiFormiga(idFormiga);
        }
        salaDestino.entraFormiga(formiga);
        formiga.removeTodasAsComidas();
        formiga.setCarga(0);
        return iteradorFinal;
    }

    @Override
    public int custoDoCaminho(Iterator<ISala> itrtr) {
        int custo = 0, position = 0;
        while (itrtr.hasNext()) {
            if (position >= this.network.numberOfVertices() - 1) {
                break;
            }
            custo += this.network.getElement(position, position + 1).getDistance();
            position++;
        }
        return custo;
    }

    @Override
    public IFormiga criaFormiga(int i, int i1) {
        Formiga formiga = new Formiga(i, i1);
        this.formigas.addToRear(formiga);
        return formiga;
    }

    @Override
    public IComida criaComida(int i, int i1) {
        Comida comida = new Comida(i, i1);
        return comida;
    }

    @Override
    public ISala criaSala(int i, String string, int i1, int i2) {
        Sala sala = new Sala(i, i1, i2, string);
        this.salas.addToRear((Sala) sala);
        return sala;
    }

    @Override
    public ISilo criaSilo(int i, String string, int i1, int i2) {
        Silo silo = new Silo(i, i1, i2, string);
        this.salas.addToRear(silo);
        return silo;
    }

    @Override
    public IProcessamento criaProcessamento(int i, String string, int i1, int i2) {
        Processamento salaProcessamento = new Processamento(i, i1, i2, string);
        this.salas.addToRear(salaProcessamento);
        return salaProcessamento;
    }

}
