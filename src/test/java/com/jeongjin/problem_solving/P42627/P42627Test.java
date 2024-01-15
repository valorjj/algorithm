package com.jeongjin.problem_solving.P42627;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class P42627Test {
	
	
	@Test
	void test() {
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
		int answer1 = solution1(jobs);
		int answer2 = solution2(jobs);
		
		Assertions.assertThat(answer1).isEqualTo(9);
		Assertions.assertThat(answer2).isEqualTo(9);
	}
	
	int solution1(int[][] jobs) {
		// 시간의 오름차순으로 정렬합니다.
		// Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
		Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
		// 우선 순위큐는 소요되는 시간의 오름차순으로 정렬되도록 합니다.
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		
		int index = 0, count = 0, total = 0, end = 0;
		
		// 모든 작업을 확인할 때 까지 반복문을 돌립니다.
		while (count < jobs.length) {
			// 작업 중인 job 소요시간을 end 로 설정합니다.
			// end 이내에 들어오는 job 들만 우선순위 큐에 추가합니다.
			while (index < jobs.length && jobs[index][0] <= end) {
				pq.add(jobs[index++]);
			}
			// end 전에 요청된 job 이 없고, 우선순위 큐가 비어있는 경우
			// job 에서 하나를 꺼내서 end 로 설정합니다.
			if (pq.isEmpty()) {
				end = jobs[index][0];
			}
			// 작업이 존재하는 경우,
			// 우선순위 큐에서 작업을 꺼내서 수행할 때마다 end 를 업데이트 합니다.
			else {
				int[] current = pq.poll();
				total += current[1] + end - current[0];
				end += current[1];
				count++;
			}
		}
		
		
		return total / jobs.length;
	}
	
	
	// https://codevang.tistory.com/316
	int solution2(int[][] jobs) {
		int answer = 0;
		int end = 0;
		int jobsIdx = 0;
		int count = 0;
		
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		
		while (count < jobs.length) {
			
			while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
				pq.add(jobs[jobsIdx++]);
			}
			
			if (pq.isEmpty()) {
				end = jobs[jobsIdx][0];
			} else {
				int[] temp = pq.poll();
				//
				answer += temp[1] + end - temp[0];
				end += temp[1];
				count++;
			}
			
			
		}
		
		
		return (int) Math.floor((double) answer / jobs.length);
	}
	
}