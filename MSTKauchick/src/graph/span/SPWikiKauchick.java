/**
 * Copyright 2015, Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package graph.span;

import graph.Edge;
import graph.Graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class SPWikiKauchick
{
    List<String> titles;
    int[][] links;

    public SPWikiKauchick(InputStream inTitles, InputStream inLinks, int source, int target) throws Exception
    {
        titles = getTitles(inTitles);
        links  = getLinks(inLinks, titles.size());

        Graph g = new Graph(titles.size());
        Dijkstra d = new Dijkstra();

        for(int i = 0; i<titles.size(); i++){
            for(int j = 0; j < links[i].length; j++){
                   g.setDirectedEdge(i, links[i][j], 1);
            }
        }

        List<Integer> path = new ArrayList<Integer>();
        boolean notfound = true;
        Integer array[] = d.getShortestPath(g,source,target);
        int index = source;
        while(notfound){
            int nextElement = array[index];
            index = nextElement;
            if(nextElement == target){
                path.add(nextElement);
                notfound=false;
            }
            else{
                path.add(nextElement);
            }

        }
        System.out.print(titles.get(source));
        for(Integer x : path){
            System.out.print(" -> " + titles.get(x));
        }
        System.out.print("\n");

    }

    public List<String> getTitles(InputStream in) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        List<String> list = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null)
            list.add(line.trim());

        reader.close();
        return list;
    }

    public int[][] getLinks(InputStream in, int size) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Pattern p = Pattern.compile(" ");
        int[][] array = new int[size][];
        int[] links;
        String line;
        String[] t;
        int i, j;

        for (i=0; (line = reader.readLine()) != null; i++)
        {
            line = line.trim();

            if (line.isEmpty())
                array[i] = new int[0];
            else
            {
                t = p.split(line);
                links = new int[t.length];
                array[i] = links;

                for (j=0; j<links.length; j++)
                    links[j] = Integer.parseInt(t[j]);
            }
        }

        return array;
    }

    static public void main(String[] args) throws Exception
    {

        final String TITLE_FILE = "/Users/kauchqui/Desktop/wiki-titles-small.txt";
        final String LINK_FILE  = "/Users/kauchqui/Desktop/wiki-links-small.txt";
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),0, 3735);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),13, 73);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),264, 3002);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),12, 3543);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),1, 943);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),532, 301);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),733, 2000);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),3, 4);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),6, 3285);
        new SPWikiKauchick(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE),9, 1583);



    }
}