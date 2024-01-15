package com.jeongjin.problem_solving.p42839;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class P42839Test {
	
	static boolean[] visited = new boolean[7];  // numbers 는 1-7 문자열
	static Set<Integer> set = new HashSet<>();
	
	@Test
	void test() {
		
		String given = "17";
		dfs(given, "", 0);
		
		Assertions.assertThat(calc()).isEqualTo(3);
	}
	
	public int calc() {
		int cnt = 0;
		for (int num : set) {
			if (isPrime(num)) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public void dfs(String numbers, String str, int depth) {
		if (depth > numbers.length()) {
			return;
		}
		
		for (int i = 0; i < numbers.length(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				set.add(Integer.parseInt(str + numbers.charAt(i)));
				dfs(numbers, str + numbers.charAt(i), depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}
		
		for (int i = 2; i < (int) Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
}