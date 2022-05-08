package Spe_Final_Source;

public class Node {
    private String label;

    public Node(String ilabel)
    {
        this.label = ilabel;

    }

    

    public String getlabel()
    {
        return this.label;
    }

    @Override
    public boolean equals(Object n)
    {
        return(this.label == ((Node)n).getlabel());
    }

    @Override
    public int hashCode()
    {
        return this.label.hashCode();
    }

    @Override
    public String toString()
    {
        return this.label;
    }
    
    public static void main(String [] args)
    {
        Node n1 = new Node("x");

        Node n2 = new Node("y");

        System.out.println(n1.getlabel());


        System.out.println(n2.getlabel());


    }
}