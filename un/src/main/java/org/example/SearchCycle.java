package org.example;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class SearchCycle {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
        }

        int[] color = new int[n + 1]; // 0 - unvisited, 1 - in stack, 2 - done
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        int[] idx = new int[n + 1];

        for (int start = 1; start <= n; start++) {
            if (color[start] != 0) continue;

            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(start);
            parent[start] = -1;

            while (!stack.isEmpty()) {
                int u = stack.peek();

                if (color[u] == 0) {
                    color[u] = 1;
                }

                boolean pushed = false;
                while (idx[u] < adj[u].size()) {
                    int v = adj[u].get(idx[u]++);
                    if (color[v] == 0) {
                        parent[v] = u;
                        stack.push(v);
                        pushed = true;
                        break;
                    } else if (color[v] == 1) {
                        ArrayList<Integer> cycle = new ArrayList<>();
                        int cur = u;
                        cycle.add(cur);
                        while (cur != v) {
                            cur = parent[cur];
                            cycle.add(cur);
                        }
                        Collections.reverse(cycle);
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
                        out.println("YES");

                        for (int i = 0; i < cycle.size(); i++) {
                            if (i > 0) out.print(" ");
                            out.print(cycle.get(i));
                        }
                        out.println();
                        out.flush();
                        return;
                    }
                }

                if (!pushed) {
                    color[u] = 2;
                    stack.pop();
                }
            }
        }

        PrintWriter out = new PrintWriter(System.out);
        out.println("NO");
        out.flush();
    }
}