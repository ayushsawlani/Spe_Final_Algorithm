package Spe_Final_Source;

import Spe_Final_Source.Edge;
import Spe_Final_Source.Node;
import java.util.HashMap;
import java.util.HashSet;

public class Graph{
    
    private HashSet <Node> NodeSet;
    private HashMap <Edge, String> Edge_expr;
    private HashMap <Node, HashSet <Node>> Adj_list;
    private HashMap <Node, HashSet <Node>> Rev_Adj_list;

    public Graph()
    {
        NodeSet = new HashSet <Node> ();
        Edge_expr = new HashMap <> ();
        Adj_list = new HashMap <Node, HashSet <Node>> ();
        Rev_Adj_list = new HashMap <Node, HashSet <Node>> ();
    }

    public HashMap <Edge, String> getEdgeTransition()
    {
        return this.Edge_expr;
    }

    public HashMap <Node, HashSet <Node>> getRevList()
    {
        return this.Rev_Adj_list;
    }
    
    public HashMap <Node, HashSet <Node>> getAdjList()
    {
        return this.Adj_list;
    }
    
    public void addNode(Node n)
    {
        //System.out.println(n.getlabel());
        NodeSet.add(n);
        Adj_list.put(n, new HashSet <Node> ());
        Rev_Adj_list.put(n, new HashSet <Node> ());
       // System.out.println(Adj_list.get(n).size());
       // System.out.println(Rev_Adj_list.get(n).size());
    }

    public void addEdge(Edge e, String tr)
    {
        Edge_expr.put(e, tr);
       // System.out.println("");
      //  System.out.println(e.gettail());
     //   System.out.println(e.gethead());
        Adj_list.get(e.gettail()).add(e.gethead());
        Rev_Adj_list.get(e.gethead()).add(e.gettail());
    }

    public void deleteNode(Node n)
    {
        HashMap <Node, HashSet <Node>> Adj_list1 = new HashMap<>(Adj_list);
        NodeSet.remove(n);
        Adj_list.remove(n);

        for(Node n_it: Rev_Adj_list.get(n))
        {
            Adj_list.get(n_it).remove(n);
        }

        for(Node n_it: Adj_list1.get(n))
        {
            Rev_Adj_list.get(n_it).remove(n);
        }

        Rev_Adj_list.remove(n);

    }

    public void printG()
    {
        for(Node n_it: NodeSet)
        {
            System.out.println(n_it);
            System.out.println("Children are:");
            for(Node n_it1: Adj_list.get(n_it))
            {
                System.out.println(n_it1);
            }
            System.out.println("");
        }
    }



    public static void main(String [] args)
    {
        Node n1 = new Node("x");

        Node n2 = new Node("y");

        Edge e1 = new Edge(n1, n2);

        Graph mygraph = new Graph();
        
        mygraph.addNode(n1);
        mygraph.addNode(n2);
        mygraph.addEdge(e1, "Abcd");

        mygraph.printG();

        mygraph.deleteNode(n1);

        mygraph.printG();



    }

    
    
}