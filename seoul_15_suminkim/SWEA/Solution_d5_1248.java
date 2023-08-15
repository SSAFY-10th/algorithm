import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_d5_1248 {
	
	static int v;
	static List<Integer>[] adj;
	static int[][] sTable;
	static int[] depth;
	static int[] subTree;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			k = 32 - Integer.numberOfLeadingZeros(v);
			int e = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			setGraph(br.readLine(), e);
			setParent();
			setSTable();
			setSubTree();
			int lca = lca(v1, v2);
			sb.append("#" + t + " " + lca + " " + subTree[lca] + "\n");
		}
		System.out.println(sb);
	}

	private static void setGraph(String readLine, int e) {
		StringTokenizer st = new StringTokenizer(readLine);
		adj = new List[v + 1];
		for (int i = 1; i <= v; i++) {
			adj[i] = new LinkedList<>();
		}
		for (int i = 0; i < e; i++) {
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adj[v1].add(v2);
			adj[v2].add(v1);
		}
	}
	
	private static void setParent() {
		sTable = new int[v + 1][k];
		depth = new int[v + 1];
		sTable[1][0] = 1;
		setParent(1);
	}
	
	private static void setParent(int root) {
		for (int child : adj[root]) {
			if (sTable[child][0] > 0) continue;
			sTable[child][0] = root;
			depth[child] = depth[root] + 1;
			setParent(child);
		}
	}
	
	private static void setSTable() {
		for (int d = 1; d < k; d++) {
			for (int i = 1; i <= v; i++) {
				sTable[i][d] = sTable[sTable[i][d - 1]][d - 1];
			}
		}
	}
	
	private static void setSubTree() {
		subTree = new int[v + 1];
		setSubTree(1);
	}
	
	private static int setSubTree(int root) {
		if (subTree[root] != 0) return subTree[root];
		subTree[root] = 1;
		for (int child : adj[root]) {
			if (sTable[child][0] != root) continue;
			subTree[root] += setSubTree(child);
		}
		return subTree[root];
	}
	
	private static int lca(int v1, int v2) {
		// v1이 더 깊은쪽이 되도록 변환
		if (depth[v1] < depth[v2]) {
			int temp = v1;
			v1 = v2;
			v2 = temp;
		}
		// v1, v2 depth 맞추기
		for (int i = k - 1; i >= 0; i--) {
			if (depth[v1] - depth[v2] >= (1 << i)) {
				v1 = sTable[v1][i];
			}
		}
		// 같아지면 같아진값 반환
		if (v1 == v2) return v1;
		// 공통 조상 찾기 : 가장 위쪽의 서로 다른 조상
		for (int i = k - 1; i >= 0; i--) {
			if (sTable[v1][i] != sTable[v2][i]) {
				v1 = sTable[v1][i];
				v2 = sTable[v2][i];
			}
		}
		return sTable[v1][0];
	}

}
