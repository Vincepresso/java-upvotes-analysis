package com.vinhuang.main;

import java.util.ArrayList;
import java.util.List;

public class UpvotesAnalyzer {

	/**
	 * Problem: At Quora, we have aggregate graphs that track the number of upvotes we get each day.
	 * As we looked at patterns across windows of certain sizes, we thought about ways to track trends
	 * such as non-decreasing and non-increasing subranges as efficiently as possible. For this problem,
	 * you are given N days of upvote count data, and a fixed window size K. For each window of K days,
	 * from left to right, find the number of non-decreasing subranges within the window minus the number
	 * of non-increasing subranges within the window. A window of days is defined as contiguous range of
	 * days. Thus, there are exactly N−K+1N−K+1 windows where this metric needs to be computed.
	 * A non-decreasing subrange is defined as a contiguous range of indices [a,b][a,b], a<ba<b,
	 * where each element is at least as large as the previous element. A non-increasing subrange is
	 * similarly defined, except each element is at least as large as the next. There are up to K(K−1)/2K(K−1)/2
	 * of these respective subranges within a window, so the metric is bounded by [−K(K−1)/2,K(K−1)/2][−K(K−1)/2,K(K−1)/2].
	 * https://www.quora.com/challenges#upvotes Problem #1
	 * 
	 * Throwing a runtime exception can be modified accordingly. I use runtime exception for simplicity
	 * Log can be used instead of the sysout. As I mentioned before, it is for simplicity
	 */
	public static void main(String[] args) {
		// Input - Can be modified accordingly
		Integer n = 5;	// # of days
		Integer k = 3;	// # of windows
		Integer[] values = {1, 2, 3, 1, 1};
		
		// Constraints
		if(n == null || k == null || n < 1 || n > 100000 || k < 1 || k > n || values.length != n) {
			throw new RuntimeException();
		}
		System.out.println("N: " + n);
		System.out.println("K: " + k);
		System.out.print("Values: ");
		for(int i = 0; i < values.length; i++) {
			System.out.print(values[i] + " ");
		}
		System.out.println("");
		System.out.println("Results: " + findDeltaRange(n, k, values));
	}
	
	/**
	 * Finding the difference between non-decreasing and non-increasing subranges:
	 * First, find the window from the given parameters
	 * Then run determineNonDecreasing() and determineNonIncreasing()
	 * Then find the difference for each window
	 * 
	 * @param n
	 * @param k
	 * @param values
	 * @return
	 */
	public static List<Integer> findDeltaRange(Integer n, Integer k, Integer[] values) {
		List<Integer> results = new ArrayList<Integer>();
		Integer windowCount = n - k + 1;
		List<List<Integer>> windows = new ArrayList<List<Integer>>();
		for(int i = 0; i < values.length; i++) {
			List<Integer> window = new ArrayList<Integer>();
			if(i < k) {
				for(int j = i; j < windowCount + i; j++) {
					window.add(values[j]);
				}
				windows.add(window);
			}
		}
		System.out.println("Windows: " + windows);
		for(List<Integer> window : windows) {
			results.add(determineNonDecreasing(window) - determineNonIncreasing(window));
		}
		if(results.size() != windowCount) {
			throw new RuntimeException();
		}
		return results;
	}
	
	/**
	 * Loop through the window/data, check whether each value is less than or
	 * equals to the next value. If so, retrieve the sublist
	 * 
	 * @param data
	 * @return
	 */
	public static Integer determineNonDecreasing(List<Integer> data) {
		List<List<Integer>> subranges = new ArrayList<List<Integer>>();
		for(int i = 0; i < data.size() - 1; i++) {
			for(int j = 1; j < data.size(); j++) {
				// Checking end value to avoid out of bound exception
				if(i + j >= data.size()) {
					continue;
				}
				if(data.get(i + j - 1) <= data.get(i + j)) {
					subranges.add(data.subList(i, i + j + 1));
				} else {
					break;
				}
			}
		}
		System.out.println("Window: " + data + " - Non-Decreasing subranges: " + subranges);
		return subranges.size();
	}
	
	/**
	 * Loop through the window/data, check whether each value is greater than or
	 * equals to the next value. If so, retrieve the sublist
	 * 
	 * @param data
	 * @return
	 */
	public static Integer determineNonIncreasing(List<Integer> data) {
		List<List<Integer>> subranges = new ArrayList<List<Integer>>();
		for(int i = 0; i < data.size() - 1; i++) {
			for(int j = 1; j < data.size(); j++) {
				// Checking end value to avoid out of bound exception
				if(i + j >= data.size()) {
					continue;
				}
				if(data.get(i + j - 1) >= data.get(i + j)) {
					subranges.add(data.subList(i, i + j + 1));
				} else {
					break;
				}
			}
		}
		System.out.println("Window: " + data + " - Non-Increasing subranges: " + subranges);
		return subranges.size();
	}

}
