package com.rajesh.reactor.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorThreadExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService=Executors.newCachedThreadPool();
		List<Integer> nums_1= Arrays.asList(1,2,3);
		List<Integer> nums_2= Arrays.asList(4,5,6);
		
		Callable<Integer> call_1 = () -> calculateSum(nums_1);
		Callable<Integer> call_2 = () -> calculateSum(nums_2);
		
		//Call single callable
		Future<Integer> sum =  executorService.submit(call_1);
		System.out.println(sum.get());
		System.out.println(executorService.submit(call_2).get());
		
		//Call multi callable
		List<Future<Integer>> sumOfList = executorService.invokeAll(Arrays.asList(call_1,call_2));
		System.out.println(sumOfList.stream().map(i -> convertToInt(i)).mapToInt(Integer::intValue).sum());
		
	}

	private static Integer convertToInt(Future<Integer> num) {
		Integer val = null;
		try {
			val = num.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return val;
	}

	private static Integer calculateSum(List<Integer> nums) {
		return nums.stream().mapToInt(Integer::intValue).sum();
	}
}
