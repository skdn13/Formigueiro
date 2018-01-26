/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import colecoes.ArrayUnorderedList;
import colecoes.Network;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
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
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author pmms8
 */
public class Formigueiro implements IFormigueiro {

    private Sala entrada;
    private int tunelID;
    private UnorderedListADT<ISala> salas;
    private UnorderedListADT<IFormiga> formigas;
    private colecoes.Network<ISala> network;

    /**
     *
     * @param entrada
     * @throws ElementNotFoundException
     */
    public Formigueiro(Sala entrada) throws ElementNotFoundException {
        formigas = new colecoes.DoubleLinkedUnorderedList<>();
        salas = new colecoes.DoubleLinkedUnorderedList<>();
        this.network = new Network<>();
        this.entrada = entrada;
        this.salas.addToRear((Sala) entrada);
        this.network.addVertex(getSalaLista(entrada.getId()));
        this.tunelID = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public ISala getEntrada() {
        return this.entrada;
    }

    /**
     *
     * @param isala
     */
    @Override
    public void setEntrada(ISala isala) {
        this.entrada = (Sala) isala;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<ISala> iterator() {
        return this.salas.iterator();
    }

    /**
     *
     * @param isala
     * @return
     */
    @Override
    public Iterator<ISala> iteratorBFS(ISala isala) {
        return network.iteratorBFS(isala);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<ISala> iteratorBFS() {
        return network.iteratorBFS(entrada);
    }

    /**
     *
     * @param isala
     */
    @Override
    public void addSala(ISala isala) {
        try {
            this.network.addVertex(this.getSalaLista(isala.getId()));
        } catch (ElementNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    /**
     *
     * @param isala
     * @param isala1
     * @param i
     */
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

    /**
     *
     */
    public void printNetwork() {
        System.out.println(this.network.toString());
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaxY() {
        Iterator<ISala> salas = this.iterator();
        int maxY = 0;
        while (salas.hasNext()) {
            ISala next = salas.next();
            if (next.getY() > maxY) {
                maxY = next.getY();
            }
        }
        return maxY;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaxX() {
        Iterator<ISala> salas = this.iterator();
        int maxX = 0;
        while (salas.hasNext()) {
            ISala next = salas.next();
            if (next.getX() > maxX) {
                maxX = next.getX();
            }
        }
        return maxX;
    }

    /**
     *
     * @param isala
     * @return
     */
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
        return (Iterator<IPair<ISala, ITunel>>) iterador.iterator();
    }

    /**
     *
     * @param i
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public ISala getSalaFormiga(int i) throws ElementNotFoundException {

        Formiga formigaProcura = (Formiga) this.getFormiga(i);
        Iterator<ISala> salas = this.salas.iterator();
        while (salas.hasNext()) {
            Sala next = (Sala) salas.next();
            Iterator<IFormiga> iterator = next.listaFormigas().iterator();
            while (iterator.hasNext()) {
                Formiga formigaIterador = (Formiga) iterator.next();
                if (formigaProcura.equals(formigaIterador)) {
                    return next;
                }
            }
        }
        throw new ElementNotFoundException("Não existe nenhum sala com esse id");
    }

    /**
     *
     * @param i
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public IFormiga getFormiga(int i) throws ElementNotFoundException {
        Iterator<IFormiga> formigasIt = this.formigas.iterator();
        while (formigasIt.hasNext()) {
            Formiga next = (Formiga) formigasIt.next();
            if (i == next.getId()) {
                return next;
            }
        }
        throw new ElementNotFoundException("Formiga não existe!");
    }

    /**
     *
     * @param i
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public ISala getSala(int i) throws ElementNotFoundException {

        Iterator<ISala> salas = this.network.iteratorVertex();

        while (salas.hasNext()) {
            ISala next = salas.next();

            if (i == next.getId()) {
                return next;
            }
        }
        return null;
    }

    /**
     *
     * @param i
     * @return
     * @throws ElementNotFoundException
     */
    public ISala getSalaLista(int i) throws ElementNotFoundException {

        Iterator<ISala> salas = this.salas.iterator();

        while (salas.hasNext()) {
            ISala next = salas.next();

            if (i == next.getId()) {
                return next;
            }
        }
        return null;
    }

    /**
     *
     * @param isala
     * @param isala1
     * @return
     */
    @Override
    public boolean vizinhos(ISala isala, ISala isala1) {
        return this.network.getElement(this.network.getElementIndex(isala), this.network.getElementIndex(isala1)) != null;
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     * @throws ElementNotFoundException
     */
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

    /**
     *
     * @param idFormiga
     * @param i1
     * @return
     * @throws ElementNotFoundException
     */
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

    /**
     *
     * @param idFormiga
     * @param i1
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public Iterator<ISala> iteratorCarregaEMoveFormigaShortestPath(int idFormiga, int i1) throws ElementNotFoundException {
        Iterator<ISala> iterador = null;
        if (idFormiga < 1) {
            throw new ElementNotFoundException("A formiga não existe");
        } else if (i1 < 1) {
            throw new ElementNotFoundException("A sala de destino não existe");
        } else if (this.getSala(i1) instanceof Processamento) {
            try {
                iterador = this.vaiParaSalaProcessamento(idFormiga, i1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return iterador;
        } else if (this.getSala(i1) instanceof Silo) {
            try {
                iterador = this.vaiParaSilo(idFormiga, i1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return iterador;
        } else {
            try {
                iterador = this.vaiParaSala(idFormiga, i1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return iterador;
        }
    }

    /**
     *
     * @param idFormiga
     * @param idSalaDestino
     * @return
     * @throws ElementNotFoundException
     * @throws Exception
     */
    public Iterator<ISala> vaiParaSalaProcessamento(int idFormiga, int idSalaDestino) throws ElementNotFoundException, Exception {
        Formiga formiga = (Formiga) getFormiga(idFormiga);
        Processamento salaDestino = (Processamento) this.getSala(idSalaDestino);
        Iterator<ISala> iteradorFinal = null;
        iteradorFinal = this.verificarSalaOrigem(formiga, salaDestino, iteradorFinal);
        salaDestino.entraFormiga(formiga);
        formiga.removeTodasAsComidas();
        return iteradorFinal;
    }

    /**
     *
     * @param idFormiga
     * @param idSalaDestino
     * @return
     * @throws ElementNotFoundException
     * @throws Exception
     */
    public Iterator<ISala> vaiParaSala(int idFormiga, int idSalaDestino) throws ElementNotFoundException, Exception {
        Formiga formiga = (Formiga) getFormiga(idFormiga);
        Sala salaDestino = (Sala) this.getSala(idSalaDestino);
        Iterator<ISala> iteradorFinal = null;
        iteradorFinal = this.verificarSalaOrigem(formiga, salaDestino, iteradorFinal);
        salaDestino.entraFormiga(formiga);
        return iteradorFinal;
    }

    /**
     *
     * @param idFormiga
     * @param idSalaDestino
     * @return
     * @throws ElementNotFoundException
     * @throws Exception
     */
    public Iterator<ISala> vaiParaSilo(int idFormiga, int idSalaDestino) throws ElementNotFoundException, Exception {
        Formiga formiga = (Formiga) getFormiga(idFormiga);
        Silo salaDestino = (Silo) this.getSala(idSalaDestino);
        Iterator<ISala> iteradorFinal = null;
        iteradorFinal = this.verificarSalaOrigem(formiga, salaDestino, iteradorFinal);
        salaDestino.entraFormiga(formiga);
        formiga.removeTodasAsComidas();
        return iteradorFinal;
    }

    /**
     *
     * @param formiga
     * @param salaDestino
     * @param iteradorFinal
     * @return
     * @throws Exception
     */
    public Iterator<ISala> verificarSalaOrigem(Formiga formiga, Sala salaDestino, Iterator<ISala> iteradorFinal) throws Exception {
        if (this.getSalaFormiga(formiga.getId()) instanceof Silo) {
            return this.saiDoSilo(formiga, salaDestino, iteradorFinal);
        } else if (this.getSalaFormiga(formiga.getId()) instanceof Processamento) {
            return this.saiDoProcessamento(formiga, salaDestino, iteradorFinal);
        } else if (this.getSalaFormiga(formiga.getId()) instanceof Sala) {
            return this.saiDaSala(formiga, salaDestino, iteradorFinal);
        }
        throw new Exception();
    }

    /**
     *
     * @param formiga
     * @param salaDestino
     * @param iteradorFinal
     * @return
     * @throws Exception
     */
    public Iterator<ISala> saiDoProcessamento(Formiga formiga, Sala salaDestino, Iterator<ISala> iteradorFinal) throws Exception {
        Processamento origem = (Processamento) this.getSalaFormiga(formiga.getId());
        this.processaComida(origem, formiga, salaDestino);
        try {
            iteradorFinal = this.network.iteratorShortestPathTunel(origem.getId() - 1, salaDestino.getId() - 1, formiga.getCarga());
        } catch (Exception ex) {
            System.out.println("Não existe nenhum caminho para a formiga percorrer!");
        }
        if (!iteradorFinal.hasNext()) {
            throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
        }
        this.entregaComida(formiga, salaDestino, origem);
        return iteradorFinal;
    }

    /**
     *
     * @param formiga
     * @param salaDestino
     * @param iteradorFinal
     * @return
     * @throws Exception
     */
    public Iterator<ISala> saiDoSilo(Formiga formiga, Sala salaDestino, Iterator<ISala> iteradorFinal) throws Exception {
        Silo origem = (Silo) this.getSalaFormiga(formiga.getId());
        this.descarregaSilo(origem, formiga, salaDestino);
        try {
            iteradorFinal = this.network.iteratorShortestPathTunel(origem.getId() - 1, salaDestino.getId() - 1, formiga.getCarga());
        } catch (Exception ex) {
            System.out.println("Não existe nenhum caminho para a formiga percorrer!");
        }
        if (!iteradorFinal.hasNext()) {
            throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
        }
        this.entregaComida(formiga, salaDestino, origem);
        return iteradorFinal;
    }

    /**
     *
     * @param formiga
     * @param salaDestino
     * @param iteradorFinal
     * @return
     * @throws Exception
     */
    public Iterator<ISala> saiDaSala(Formiga formiga, Sala salaDestino, Iterator<ISala> iteradorFinal) throws Exception {
        Sala origem = (Sala) this.getSalaFormiga(formiga.getId());
        this.saiFormigaSala(origem, formiga, salaDestino);
        try {
            iteradorFinal = this.network.iteratorShortestPathTunel(origem.getId() - 1, salaDestino.getId() - 1, formiga.getCarga());
        } catch (Exception ex) {
            System.out.println("Não existe nenhum caminho para a formiga percorrer!");
        }
        if (!iteradorFinal.hasNext()) {
            throw new Exception("Não existe nenhum caminho para a formiga percorrer!");
        }
        this.entregaComida(formiga, salaDestino, origem);
        return iteradorFinal;
    }

    /**
     *
     * @param formiga
     * @param salaDestino
     * @param origem
     * @throws ElementNotFoundException
     */
    public void entregaComida(Formiga formiga, Sala salaDestino, Sala origem) throws ElementNotFoundException {

        Iterator<IComida> it = formiga.listarComidas().iterator();
        if (!it.hasNext()) {
            throw new recursos.exceptions.ElementNotFoundException("A Formiga não tem comidas!");

        }
        while (it.hasNext()) {
            IComida next = it.next();
            if (salaDestino instanceof Silo) {
                Silo silo = (Silo) salaDestino;
                silo.guardaComida(next);
            } else if (salaDestino instanceof Processamento) {
                Processamento processamento = (Processamento) salaDestino;
                processamento.acrescentaComida(next);
            }
        }
        origem.saiFormiga(formiga.getId());
    }

    /**
     *
     * @param origem
     * @param formiga
     * @param salaDestino
     */
    public void processaComida(Processamento origem, Formiga formiga, Sala salaDestino) {
        int cargaAtual = 0;
        while (true) {
            Comida comida = null;
            try {
                comida = (Comida) origem.getProximaComida();
                cargaAtual++;
                formiga.setCarga(cargaAtual);
                formiga.addComida(comida);
            } catch (ProcessedException ex) {
                ex.printStackTrace();
            } catch (EmptyCollectionException ex) {
                ex.printStackTrace();
                return;
            } catch (FormigaCheiaException ex) {
                origem.acrescentaComida(comida);
                ex.printStackTrace();
                return;
            }
        }
    }

    /**
     *
     * @param origem
     * @param formiga
     * @param salaDestino
     */
    public void descarregaSilo(Silo origem, Formiga formiga, Sala salaDestino) {
        int cargaAtual = 0;
        while (true) {
            Comida comida = null;
            try {
                comida = (Comida) origem.retiraComida();
                cargaAtual++;
                formiga.setCarga(cargaAtual);
                formiga.addComida(comida);
            } catch (EmptyCollectionException ex) {
                ex.printStackTrace();
                return;
            } catch (FormigaCheiaException ex) {
                origem.guardaComida(comida);
                ex.printStackTrace();
                return;
            }
        }
    }

    /**
     *
     * @param origem
     * @param formiga
     * @param salaDestino
     */
    public void saiFormigaSala(Sala origem, Formiga formiga, Sala salaDestino) {
        int cargaAtual = formiga.getCarga();
        while (true) {
            Comida comida = null;
            try {
                if (salaDestino instanceof Processamento || salaDestino instanceof Silo) {
                    comida = (Comida) formiga.removeComida();
                    cargaAtual--;
                    formiga.setCarga(cargaAtual);
                } else {
                    return;
                }
            } catch (EmptyCollectionException ex) {
                ex.printStackTrace();
                return;
            }
        }
    }

    /**
     *
     * @param itrtr
     * @return
     */
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

    /**
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public IFormiga criaFormiga(int i, int i1) {
        Formiga formiga = new Formiga(i, i1);
        this.formigas.addToRear(formiga);
        return formiga;
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public IComida criaComida(int i, int i1) {
        Comida comida = new Comida(i, i1);
        return comida;
    }

    /**
     *
     * @param i
     * @param string
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public ISala criaSala(int i, String string, int i1, int i2) {
        Sala sala = new Sala(i, i1, i2, string);
        this.salas.addToRear((Sala) sala);
        return sala;
    }

    /**
     *
     * @param i
     * @param string
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public ISilo criaSilo(int i, String string, int i1, int i2) {
        Silo silo = new Silo(i, i1, i2, string);
        this.salas.addToRear(silo);
        return silo;
    }

    /**
     *
     * @param i
     * @param string
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public IProcessamento criaProcessamento(int i, String string, int i1, int i2) {
        Processamento salaProcessamento = new Processamento(i, i1, i2, string);
        this.salas.addToRear(salaProcessamento);
        return salaProcessamento;
    }

}
