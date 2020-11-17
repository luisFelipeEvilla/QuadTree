package quadtree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luisf
 */
public class Nodo {

    private int x, y, value;
    private Nodo link;

    Nodo(int x, int y, int value) {
        this.x = x;
        this.y = y;
        /* información a almacenar*/
        this.value = value;
        this.link = null;
    }
    
    /**
     * Añadir un nodo en forma de cola
     * @param nodo nodo a ser insertado al final de la lista
     */
    public void add(Nodo nodo) {
        Nodo p = this;
        
        while (p.getLink() != null) {
            p = p.getLink();
        }
        
        p.setLink(nodo);
    }
    
    public int getSize() {
        int cont = 0;
        Nodo p = this;
        
        while (p != null) {
            p = p.getLink();
            cont++;
        }
        
        return cont;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Nodo getLink() {
        return link;
    }

    public void setLink(Nodo link) {
        this.link = link;
    }
}
