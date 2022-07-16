import java.io.*;
import java.util.*;

class Solution {
    
    public boolean checkCycle(ArrayList<ArrayList<Integer>> list, 
                    boolean[] visited, 
                    boolean[] dfsVisited, 
                    int current){
        
        visited[current] = true;
        dfsVisited[current] = true;
        
        //check all adjacent nodes.
        for(Integer i: list.get(current)){
            //if we have visited this node, then no need to check further children.
            if(!visited[i]){
                 if(checkCycle(list, visited, dfsVisited, i)){
                     return true;
                }
            }
            //if we have dfsVisited this node, 
            else if (dfsVisited[i]){
                return true;
            }
        }
        
        dfsVisited[current] = false;
        return false;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        //initialise variables
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] dfsVisited = new boolean[numCourses];
    
        //create a graph using the prerequisities
        for (int i=0; i< numCourses; i++){
            list.add(new ArrayList<>());
        }
        
        for(int[] prereq: prerequisites){
            list.get(prereq[1]).add(prereq[0]);
        }
        
        //Use dfs to check if a cycle is present
        for(int i=0;i<numCourses;i++){
            if(!visited[i] && checkCycle(list, visited, dfsVisited, i)){
                return false;
            }
        }
        
        return true;
    }
}