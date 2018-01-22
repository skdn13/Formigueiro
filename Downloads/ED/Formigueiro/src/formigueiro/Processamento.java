/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formigueiro;

import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.ProcessedException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.IProcessamento;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author pmms8
 */
public class Processamento extends Sala implements IProcessamento {

    private colecoes.ArrayUnorderedList<Formiga> formigas;
    private colecoes.ArrayUnorderedList<Comida> comida;

    public Processamento(int id, int x, int y, String descricao) {
        super(id, x, y, descricao);
        formigas = new colecoes.ArrayUnorderedList<>();
        comida = new colecoes.ArrayUnorderedList<>();
    }
         
    @Override
    public void acrescentaComida(IComida ic) {
        this.comida.addToRear((Comida) ic);
    }

    @Override
    public IComida getProximaComida() throws EmptyCollectionException, ProcessedException {
        int f, d;
         if (comida.isEmpty()) {
            throw new recursos.exceptions.EmptyCollectionException("Empty List");
            
         }else if(this.comida.last().getTamanho()==1){
            
            return this.comida.removeLast(); //isto manda a ultima comida que é retirada?
       
       }else{
                f = this.comida.last().getTamanho();
               for (int i = 0; i < f; i++) {
                   Comida cni= new Comida(this.comida.last().getId()+i, 1);
                   acrescentaComida(cni);
            }
               throw new recursos.exceptions.ProcessedException(this.comida.removeLast());
        }
            
    }

    
    @Override
    public Iterator<IComida> iteratorComida() {
        return (Iterator<IComida>) this.comida.getIterator();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int i) {
        super.setId(i);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int i) {
        super.setX(i);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int i) {
        super.setY(i);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao();
    }

    @Override
    public void setDescricao(String string) {
        super.setDescricao(string);
    }

    @Override
    public void entraFormiga(IFormiga i) {
        this.formigas.addToRear((Formiga) i);
    }

    @Override
    public IFormiga saiFormiga(int i) throws EmptyCollectionException, ElementNotFoundException {
        return this.formigas.removeLast();
    }

    @Override
    public UnorderedListADT<IFormiga> listaFormigas() {
        return (UnorderedListADT<IFormiga>) this.formigas.getIterator();
    }

}
