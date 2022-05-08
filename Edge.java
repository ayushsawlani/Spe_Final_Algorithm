package Spe_Final_Source;

import Spe_Final_Source.Node;

public class Edge{
    private Node head;
    private Node tail;

    public Edge(Node itail, Node ihead)
    {
        this.tail = itail;
        this.head = ihead;
    }

    public Node gettail()
    {
        return this.tail;
    }

    public Node gethead()
    {
        return this.head;
    }

    

    @Override
    public boolean equals(Object obj)
    {
        Edge e = (Edge)(obj);
        return (this.head.equals(e.gethead()) && this.tail.equals(e.gettail()));
    }

    @Override
    public int hashCode()
    {
        int p1 = 999999503;
        int p2 = 1000000007;
        return (this.head.hashCode() + p1*this.tail.hashCode())%p2;
    }

    public static void main(String [] args)
    {
        Node n1 = new Node("x");

        Node n2 = new Node("y");

        Edge e1 = new Edge(n1, n2); 

        System.out.println(e1.gettail().getlabel());
        System.out.println(e1.gethead().getlabel());
    
    }
}



