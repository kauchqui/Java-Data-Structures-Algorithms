package graph;
import graph.span.MSTAll;
import graph.span.SpanningTree;
import java.util.*;

public class MSTKauchick implements MSTAll {


    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph)
    {
        Edge edge;
        List<SpanningTree> list = new ArrayList<>(); //list of minimum spanning trees
        // calling the prim algorithm to find a base MST so we have a weight to compare to
        list.add(getPrimTree(graph));
        //if the graph has no edges return an empty list

        for(int i = 0; i<graph.size(); i++) { // This for loop iterates from 0 to the size of the graph, and picks each node as the root.

            //creating new data structures
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            SpanningTree tree = new SpanningTree();
            Set<Integer> visited = new HashSet<>();

            //creating the priority queue of edges from the root vertex.
            add(queue, visited, graph, i);

            while (!queue.isEmpty()) {
                edge = queue.poll();

                //partitioning based on the number of edges that have an identical weight to the min weight edge from the current vertex.
                if (!queue.isEmpty()) {

                    while (edge.getWeight() == (queue.peek().getWeight())) {
                        Edge newEdge;
                        newEdge = queue.poll();
                        getMinimumSpanningTreesHelperFunction(graph, newEdge, tree, visited, list);
                        if (queue.isEmpty()) {
                            break;
                        }
                    }
                }
                if (!visited.contains(edge.getSource())) {
                    tree.addEdge(edge);

                    //If a spanning tree is found, break.
                    boolean equal = false;
                    boolean sameWeight = false;
                    if (tree.size() + 1 == graph.size()) {
                        for (SpanningTree Tree : list) {
                            if (tree.getUndirectedSequence().compareTo(Tree.getUndirectedSequence()) == 0) {
                                equal = true;
                                break;
                            } else if (tree.getTotalWeight() == Tree.getTotalWeight()) {
                                sameWeight = true;
                            }
                        }
                        if (!equal && sameWeight) {
                            list.add(tree);
                        } else {
                            break;
                        }
                    }
                    //Add all connecting vertices from target vertex to the queue
                    add(queue, visited, graph, edge.getSource());
                }

            }
        }
            return list;
    }



    private List<SpanningTree> getMinimumSpanningTreesHelperFunction(Graph graph,Edge edge,SpanningTree tree, Set<Integer> visited, List<SpanningTree> list){
        PriorityQueue<Edge> tempQueue = new PriorityQueue<>();
        Set<Integer> tempVisited = new HashSet<>(visited);
        SpanningTree tempTree = new SpanningTree(tree);


        tempTree.addEdge(edge);
        //Add all connecting vertices from start vertex to the queue
        add(tempQueue, tempVisited, graph, edge.getSource());

        while (!tempQueue.isEmpty())
        {
            edge = tempQueue.poll(); //change something here
            if(!tempQueue.isEmpty()) {
                while (edge.getWeight() == tempQueue.peek().getWeight()) {
                    //condition that previous edge cant be reused
                    Edge newEdge;
                    newEdge = tempQueue.poll();
                    getMinimumSpanningTreesHelperFunction(graph, newEdge, tempTree, tempVisited, list);

                    // if the queue is empty break. If you don't break here you get an exception and terminate before finding a full list.
                    if (tempQueue.isEmpty()) {
                        break;
                    }
                }
            }
            if (!tempVisited.contains(edge.getSource()))
            {
                tempTree.addEdge(edge);

                //If a spanning tree is found, break.
                boolean equal = false;
                boolean sameWeight = false;
                double newTreeW = tempTree.getTotalWeight();

                if (tempTree.size()+1 == graph.size()) {
                    for(SpanningTree Tree : list){
                        double TreeW = Tree.getTotalWeight();
                        if(tempTree.getUndirectedSequence().compareTo(Tree.getUndirectedSequence())==0){
                            equal = true;
                            break;
                        }
                        else if(newTreeW == TreeW){
                            sameWeight = true;
                        }
                    }
                    if (!equal && sameWeight) {
                        list.add(tempTree);
                    }
                    else {
                        break;
                    }
                }
                //Add all connecting vertices from current vertex to the queue
               add(tempQueue, tempVisited, graph, edge.getSource());
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



