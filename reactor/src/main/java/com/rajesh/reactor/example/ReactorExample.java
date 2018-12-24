package com.rajesh.reactor.example;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;

public class ReactorExample {
	public static void main(String[] args) {
		
		//Multiplication using Flux
		int multiplier = 6, limit=10;
		
		Flux<Object> flux = Flux.generate(
					() -> 1,
					(state, sink) -> {
						sink.next(multiplier+" = x "+state+" = "+multiplier*state);
						if(state == limit) sink.complete();
							return state+1;
					}
				);
		
		flux.subscribe(System.out::println);
		
		System.out.println("======================================");
		
		List<String> words = Arrays.asList("the","quick","brown","fox","jumps","over","the","lazy","dog");
		
		Flux.fromIterable(words)
			.flatMap(word -> Flux.fromArray(word.split("")))
			.distinct()
			.sort()
			.zipWith(Flux.range(1, 30), (word, line) -> line+". "+word)
			.subscribe(System.out::println);
		
	}
}
