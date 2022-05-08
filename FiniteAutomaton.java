import Spe_Final_Source.Edge;
import Spe_Final_Source.Graph;
import Spe_Final_Source.Node;
import Spe_Final_Source.Pair;
import java.util.ArrayList;
import java.util.HashMap;

class FiniteAutomaton {
    private Graph auto_graph = new Graph();
    private ArrayList <String> state_list;
    private String start_state;
    private ArrayList <String> finish_states;
    private HashMap <Pair <String, String>, ArrayList <String>> transitions;

    public FiniteAutomaton(ArrayList <String> istate_list, String istart_state, ArrayList <String> ifinish_states
                            ,HashMap <Pair <String, String>, ArrayList <String>> itransitions)
    {
        this.state_list = istate_list;
        this.finish_states = ifinish_states;
        this.start_state = istart_state;
        this.transitions = itransitions;

        // add nodes in graph
        for(Pair <String, String> p : transitions.keySet())
        {
            auto_graph.addNode(new Node(p.getFirst()));
            for(String s: transitions.get(p))
                auto_graph.addNode(new Node(s));
        }

        // add edges in graph

        for(Pair <String, String> p: transitions.keySet())
        {
            for(String s: transitions.get(p))
                auto_graph.addEdge(new Edge(new Node(p.getFirst()), new Node(s)), p.getSecond());
        }
    }
    public ArrayList <String> getStates()
    {
        return this.state_list;
    }
    public ArrayList <String> getFinalStates()
    {
        return this.finish_states;
    }
    public String getStartState()
    {
        return this.start_state;
    }
    public Graph getGraph()
    {
        return this.auto_graph;
    }
    public HashMap <Pair <String, String>, ArrayList <String>> getTransitions()
    {
        return this.transitions;
    }
    
    public static FiniteAutomaton Nfa_to_Gfa(FiniteAutomaton nfa)
    {
        ArrayList <String> gfa_states = nfa.getStates();
        HashMap <Pair <String, String>, ArrayList <String>> gfa_transitions = nfa.getTransitions();
        gfa_states.add("gfa_start");
        gfa_states.add("gfa_end");

        ArrayList <String> to_start = new ArrayList<>();
        to_start.add(nfa.getStartState());

        gfa_transitions.put(new Pair <String, String> ("gfa_start", "$"), to_start);

        for(String end_state: nfa.getFinalStates())
        {
            if(!gfa_transitions.containsKey(new Pair <String, String>(end_state, "$")))
            {
                ArrayList <String> empty = new ArrayList<>();
                gfa_transitions.put(new Pair <String, String>(end_state, "$"), empty);
            }
            gfa_transitions.get(new Pair <String, String> (end_state, "$")).add("gfa_end");
        }
        ArrayList <String> gfa_end_states = new ArrayList<>();
        gfa_end_states.add("gfa_end");
        FiniteAutomaton gfa = new FiniteAutomaton(gfa_states, "gfa_start", gfa_end_states, gfa_transitions);

        return gfa;
    }

    public void addTransition(String s1, String s2, String tr)
    {
        Node ns1 = new Node(s1);
        Node ns2 = new Node(s2);
        Edge e = new Edge(ns1, ns2);
        String newtr = "";
        if(this.auto_graph.getEdgeTransition().containsKey(e))
        {
            newtr = "(" + newtr + "+" + this.auto_graph.getEdgeTransition().get(e) + ")";   
        }
        if(newtr == "")
            this.auto_graph.addEdge(e, tr);
        else 
            this.auto_graph.addEdge(e, newtr);
    }

    public void removeState(String s)
    {
        this.state_list.remove(s);
        this.auto_graph.deleteNode(new Node(s));
    }
    public String removeStates()
    {
        String ans = "";
        while(this.state_list.size()>2)
        {
            for(String to_remove: this.getStates())
            {
                if((to_remove != "gfa_start")&&(to_remove!= "gfa_end"))
                {
                    String mid = "";

                    if(this.auto_graph.getEdgeTransition().containsKey(new Edge(new Node(to_remove), new Node (to_remove))))
                    {
                        mid = "(" + this.auto_graph.getEdgeTransition().get(new Edge (new Node(to_remove), new Node(to_remove))) 
                                + "*" + ")";
                    }
                    for(Node prev: this.auto_graph.getRevList().get(new Node(to_remove)))
                    {
                        if(prev.getlabel()!= to_remove)
                        {
                            for(Node child: this.auto_graph.getRevList().get(new Node(to_remove)))
                            {
                                if(child.getlabel()!= to_remove)
                                {
                                    String pref = this.auto_graph.getEdgeTransition().get(new Edge(prev, new Node(to_remove)));
                                    String suff = this.auto_graph.getEdgeTransition().get(new Edge(new Node(to_remove), child));
                                    this.addTransition(prev.getlabel(), child.getlabel(), pref + "."+ mid + "." + suff);
                                }
                            }
                        }
                    }
                    this.removeState(to_remove);
                    break;
                }
            }
        }
        return ans;
    }
}