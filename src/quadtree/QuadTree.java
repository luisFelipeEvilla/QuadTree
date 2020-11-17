/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisf
 */
public class QuadTree {

    final int CAPACIDAD_MAXIMA = 4;
    int nivel = 0;
    List<Nodo> nodos;
    QuadTree norOeste = null;
    QuadTree norEste = null;
    QuadTree surOeste = null;
    QuadTree surEste = null;
    Limite limite;

    QuadTree(int level, Limite boundry) {
        this.nivel = level;
        nodos = new ArrayList<Nodo>();
        this.limite = boundry;
    }

    /* Recorrido del grafo por dfs*/
    public static void dfs(QuadTree arbol) {
        if (arbol == null) {
            return;
        }

        System.out.printf("\nNivel = %d [X1=%d Y1=%d] \t[X2=%d Y2=%d] ",
                arbol.nivel, arbol.limite.getxMin(), arbol.limite.getyMin(),
                arbol.limite.getxMax(), arbol.limite.getyMax());

        for (Nodo node : arbol.nodos) {
            System.out.printf(" \n\t  x=%d y=%d", node.x, node.y);
        }
        if (arbol.nodos.size() == 0) {
            System.out.printf(" \n\t Nodo hoja");
        }
        dfs(arbol.norOeste);
        dfs(arbol.norEste);
        dfs(arbol.surOeste);
        dfs(arbol.surEste);

    }

    /*
     Subidividir el nodo en cuatro regiones más pequeñas
    */
    public void subdividir() {
        int compensacionX = this.limite.getxMin()
                + (this.limite.getxMax() - this.limite.getxMin()) / 2;
        int compensacionY = this.limite.getyMin()
                + (this.limite.getyMax() - this.limite.getyMin()) / 2;

        norOeste = new QuadTree(this.nivel + 1, new Limite(
                this.limite.getxMin(), this.limite.getyMin(), compensacionX,
                compensacionY));
        norEste = new QuadTree(this.nivel + 1, new Limite(compensacionX, this.limite.getyMin(), this.limite.getxMax(), compensacionY));

        surOeste = new QuadTree(this.nivel + 1, new Limite(this.limite.getxMin(), compensacionY, compensacionX, this.limite.getyMax()));
        surEste = new QuadTree(this.nivel + 1, new Limite(compensacionX, compensacionY,
                this.limite.getxMax(), this.limite.getyMax()));

    }

    /*
        Insertar un nodo en el árbol
    */
    public void insertar(int x, int y, int value) {
        if (!this.limite.enRango(x, y)) {
            return;
        }

        Nodo node = new Nodo(x, y, value);
        if (nodos.size() < CAPACIDAD_MAXIMA) {
            nodos.add(node);
            return;
        }
        // Si excede la capacidad máxima de nodos, se subdivide en cuatro más
        if (norOeste == null) {
            subdividir();
        }

        // Check coordinates belongs to which partition 
        if (this.norOeste.limite.enRango(x, y)) {
            this.norOeste.insertar(x, y, value);
        } else if (this.norEste.limite.enRango(x, y)) {
            this.norEste.insertar(x, y, value);
        } else if (this.surOeste.limite.enRango(x, y)) {
            this.surOeste.insertar(x, y, value);
        } else if (this.surEste.limite.enRango(x, y)) {
            this.surEste.insertar(x, y, value);
        } else {
            System.out.printf("ERROR : Unhandled partition %d %d", x, y);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuadTree anySpace = new QuadTree(1, new Limite(0, 0, 1000, 1000));
        anySpace.insertar(100, 100, 1);
        anySpace.insertar(500, 500, 1);
        anySpace.insertar(600, 600, 1);
        anySpace.insertar(700, 600, 1);
        anySpace.insertar(800, 600, 1);
        anySpace.insertar(900, 600, 1);
        anySpace.insertar(510, 610, 1);
        anySpace.insertar(520, 620, 1);
        anySpace.insertar(530, 630, 1);
        anySpace.insertar(540, 640, 1);
        anySpace.insertar(550, 650, 1);
        anySpace.insertar(555, 655, 1);
        anySpace.insertar(560, 660, 1);
        //Traveling the graph
        QuadTree.dfs(anySpace);
    }

}
