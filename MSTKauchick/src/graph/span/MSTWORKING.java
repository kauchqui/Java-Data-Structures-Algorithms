package graph.span;

import graph.Edge;
import graph.Graph;
import graph.span.MSTAll;
import graph.span.SpanningTree;

import java.util.*;

public class MSTWORKING implements MSTAll {


    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph)
    {

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        SpanningTree tree = new SpanningTree();
        Set<Integer> visited = new HashSet<>();
        Edge edge;

        List<SpanningTree> list = new ArrayList<>(); //list of minimum spanning trees
        list.add(getPrimTree(graph));
        //if the graph has no edges return an empty list
        if (tree.size()+1 == graph.size()) {
            return list;
        }

        //Add all connecting vertices from start vertex to the queue
        add(queue, visited, graph, 0);

        while (!queue.isEmpty())
        {
            edge = queue.poll();

            // peek if there is another edge -> iterate
            // new tree, other structures
            // make a recursive call
            if(!queue.isEmpty()) {
                while (edge.getWeight() == (queue.peek().getWeight())) {
                    edge = queue.poll();
                    getMinimumSpanningTreesHelp(graph, edge, tree, visited, list);
                    if (queue.isEmpty()) {
                        break;
                    }
                }
            }

            if (!visited.contains(edge.getSource()))
            {
                tree.addEdge(edge);

                //If a spanning tree is found, break.
                boolean equal = false;
                boolean sameWeight = false;
                if (tree.size()+1 == graph.size()) {
                    for(SpanningTree Tree : list){
                        if(tree.getUndirectedSequence().compareTo(Tree.getUndirectedSequence())==0){
                            equal = true;
                            break;
                        }
                        else if(tree.getTotalWeight() == Tree.getTotalWeight()){
                            sameWeight = true;
                        }
                    }
                    if (!equal && sameWeight) {
                        list.add(tree);
                    }
                    else {
                        break;
                    }
                }
                //Add all connecting vertices from current vertex to the queue
                add(queue, visited, graph, edge.getSource());
            }

        }

        return list;
    }

    private List<SpanningTree> getMinimumSpanningTreesHelp(Graph graph,Edge edge,SpanningTree tree, Set<Integer> visited, List<SpanningTree> list){
        PriorityQueue<Edge> newQueue = new PriorityQueue<>();
        SpanningTree newTree = new SpanningTree(tree);
        Set<Integer> newSet = new HashSet<>(visited);

        newTree.addEdge(edge);
        //Add all connecting vertices from start vertex to the queue
        add(newQueue, newSet, graph, edge.getSource());

        while (!newQueue.isEmpty())
        {
            edge = newQueue.poll(); //change something here
            if(!newQueue.isEmpty()) {
                while (edge.getWeight() == newQueue.peek().getWeight()) {
                    //condition that previous edge cant be reused

                    edge = newQueue.poll();
                    getMinimumSpanningTreesHelp(graph, edge, newTree, newSet, list);
                }
            }
            if (!newSet.contains(edge.getSource()))
            {
                newTree.addEdge(edge);

                //If a spanning tree is found, break.
                boolean equal = false;
                boolean sameWeight = false;
                if (tree.size()+1 == graph.size()) {
                    for(SpanningTree Tree : list){
                        if(tree.getUndirectedSequence().compareTo(Tree.getUndirectedSequence())==0){
                            equal = true;
                            break;
                        }
                        else if(tree.getTotalWeight() == Tree.getTotalWeight()){
                            sameWeight = true;
                        }
                    }
                    if (!equal && sameWeight) {
                        list.add(tree);
                    }
                    else {
                        break;
                    }
                }
                //Add all connecting vertices from current vertex to the queue
                add(newQueue, newSet, graph, edge.getSource());
            }
        }
        return list;
    }


    /**
     * @param queue Queue of all edges awaited to explore
     * @param visited Set of visited vertices
     * @param graph Graph
     * @param target Target vertex
     */
    private void add(PriorityQueue<Edge> queue, Set<Integer> visited, Graph graph, int target)
    {
        visited.add(target);

        for (Edge edge : graph.getIncomingEdges(target))
        {
            if (!visited.contains(edge.getSource()))
                queue.add(edge);
        }
    }
    public SpanningTree getPrimTree(Graph graph)
    {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        SpanningTree tree = new SpanningTree();
        Set<Integer> visited = new HashSet<>();
        Edge edge;

        //Add all connecting vertices from start vertex to the queue
        add(queue, visited, graph, 0);

        while (!queue.isEmpty())
        {
            edge = queue.poll();

            if (!visited.contains(edge.getSource()))
            {
                tree.addEdge(edge);

                //If a spanning tree is found, break.
                if (tree.size()+1 == graph.size()) break;

                //Add all connecting vertices from current vertex to the queue
                add(queue, visited, graph, edge.getSource());
            }
        }

        return tree;
    }
}
