# [Gold V] 탑 - 2493 

[문제 링크](https://www.acmicpc.net/problem/2493) 

### 성능 요약

메모리: 126972 KB, 시간: 3452 ms

### 분류

자료 구조, 스택

### 문제 설명

<p>KOI 통신연구소는 레이저를 이용한 새로운 비밀 통신 시스템 개발을 위한 실험을 하고 있다. 실험을 위하여 일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다. </p>

<p>예를 들어 높이가 6, 9, 5, 7, 4인 다섯 개의 탑이 수평 직선에 일렬로 서 있고, 모든 탑에서는 주어진 탑 순서의 반대 방향(왼쪽 방향)으로 동시에 레이저 신호를 발사한다고 하자. 그러면, 높이가 4인 다섯 번째 탑에서 발사한 레이저 신호는 높이가 7인 네 번째 탑이 수신을 하고, 높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신을 한다. 높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신을 하지 못한다.</p>

<p>탑들의 개수 N과 탑들의 높이가 주어질 때, 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램을 작성하라. </p>

### 입력 

 <p>첫째 줄에 탑의 수를 나타내는 정수 N이 주어진다. N은 1 이상 500,000 이하이다. 둘째 줄에는 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 주어진다. 탑들의 높이는 1 이상 100,000,000 이하의 정수이다.</p>

### 출력 

 <p>첫째 줄에 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력한다. 만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력한다.</p>


---

## 코드
```java

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		Deque<Integer> stack = new ArrayDeque<>();
		List<Integer> list = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1. 탑 입력받기
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		// 2. 끝에서부터 탐색
		for(int i=N-1; i>=0; i--) {
			
			// 3. 스택이 비어있으면 push(addFirst)
			if(stack.isEmpty()) {
				stack.addFirst(arr[i]);
			} else {
				// 4. 현재 원소가 스택의 top보다 클 때: pop & list에 수신받은 탑 인덱스 add & 현재 원소 push
				if(stack.peek() < arr[i]) {
					while(!stack.isEmpty() && stack.peek() < arr[i]) { // 현재 원소보다 작은 값인 스택 요소를 전부 pop&list.add
						stack.poll();
						list.add(i+1);
					}
					stack.addFirst(arr[i]);
					
				// 5. 현재 원소가 스택의 top보다 작을 때: 스택에 push
				} else if(stack.peek() >= arr[i]) {
					stack.addFirst(arr[i]);
				}
			}
		}
		
		// 배열을 한바퀴 다 돌고도 스택에 남아있는 탑들 (자기자신보다 높은 탑을 발견하지 못해 수신받지 못한 탑들)
		while(!stack.isEmpty()) {
			int idx = Arrays.asList(arr).indexOf(stack.pollLast());
			list.add(arr.length-idx-1, 0); // 해당 원소 인덱스에 0 삽입
		}
		
		
		// 답 출력
		Collections.reverse(list);
		for(Integer i:list ) {
			sb.append(i).append(" ");
		}
		
		sb.deleteCharAt(sb.length() - 1); // 마지막 공백 제거
		System.out.println(sb);
	}
}
```