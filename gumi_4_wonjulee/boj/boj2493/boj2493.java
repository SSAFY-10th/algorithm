import java.util.*;
import java.io.*;

// 스택이 비어있으면 push
// 현재 값과 스택의 마지막값을 비교 -> 현재값이 더 클 경우 스택값 pop&현재 인덱스i 출력, 현재값push
// 							-> 현재값이 더 작을 경우 push만
//							-> i가 끝났는데 !isEmpty()일 경우 0 으로 채우기 

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
