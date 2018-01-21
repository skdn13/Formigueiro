/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excecoes;

/**
 *
 * @author pmms8
 */
public class ProcessedException extends Exception {

    /**
     *
     * @param mensagem
     */
    public ProcessedException(String mensagem) {
        System.out.println(mensagem);
    }
}
