package com.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Lista<T> extends ArrayList<T>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lista() {
        super(); //chame o construtor de ArrayList
    }

    /*Esse m�todo sobrecarrega o metodo contains, e ir� funcionar
    com arrays de objetos. Voc� pode sobregarregar, sobrescrever
    ou adicionar mais m�todos a esta classe, de forma que
    ela fique ainda mais poderosa, e usar ela, em vez de ArrayList.
    */
    public boolean contains(Object[] o){
        //percorre o bjeto 'Lista':
        for(int i=0; i< this.size(); i++){
            /* cria um array de objetos e faz um 'type-cast'
            desta pr�pria classe atribuindo a ele o valor do
            elemento, e isso para cada elemento, de forma que
            todos os elementos sejam tratados como arrays de
            objetos:
            */
            Object[] toCompare = (Object[])this.get(i);
            /* atrav�s do metodo equals da classe Arrays, e do loop
            for-each, verifica se algum elemento do array candidato ao
            add() j� est� adicionado na lista, se sim � retornado true,
            se n�o, � retornado false.
            */
            if(Arrays.equals(o, toCompare)){
                return true;
            }
        }
        return false;
    }
} 