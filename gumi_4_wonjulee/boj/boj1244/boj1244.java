import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		
		int []data = new int[2];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				data[j] = Integer.parseInt(st.nextToken());
			}
			
			if(data[0] == 1) { // 남자일 경우
				arr = boy(data[1]);
			} else { // 여자일 경우
				//
				arr = girl(data[1]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<arr.length; i++) {
			sb.append(arr[i]+" ");
			if((i+1)% 20 == 0) sb.append("\n"); 
		}
		System.out.println(sb);
	}
	
	public static int[] boy(int data) {
		for (int i = data-1; i < arr.length; i+=data) {
			arr[i] ^= 1;
		}
		return arr;
	}
	
	public static int[] girl(int data) {
		data--;
		arr[data] ^= 1;
		for(int i=1; i<=data; i++) {
			if(data+i >= arr.length || data-i < 0) {
				break;
			}
			if(arr[data+i] == arr[data-i]) {
				arr[data+i] ^= 1;
				arr[data-i] ^= 1;
			} else {
				return arr;
			}
		}
		return arr;
	}
}
