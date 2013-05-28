package org.cyetstar.clover.utils;

import static com.google.common.collect.Sets.*;

import java.util.HashSet;

import org.cyetstar.clover.entity.MovieAka;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class GuavaTest {

	@Test
	public void set() {
		HashSet<Integer> setA = newHashSet(1, 2, 3, 4, 5);
		HashSet<Integer> setB = newHashSet(4, 5, 6, 7, 8);

		SetView<Integer> union = Sets.union(setA, setB);
		System.out.println("union:");
		for (Integer integer : union)
			System.out.println(integer);

		SetView<Integer> difference = Sets.difference(setA, setB);
		System.out.println("difference:");
		for (Integer integer : difference)
			System.out.println(integer);

		SetView<Integer> intersection = Sets.intersection(setA, setB);
		System.out.println("intersection:");
		for (Integer integer : intersection)
			System.out.println(integer);

		HashSet<MovieAka> a = newHashSet(new MovieAka("123"), new MovieAka("abc"), new MovieAka("go"));
		HashSet<MovieAka> b = newHashSet(new MovieAka("123"), new MovieAka("abc"), new MovieAka("gogo"));

		for (MovieAka aka : Sets.union(a, b))
			System.out.println(aka.getTitle());

		System.out.println("==========");
		for (MovieAka aka : Sets.difference(a, b))
			System.out.println(aka.getTitle());
		System.out.println("==========");
		for (MovieAka aka : Sets.intersection(a, b))
			System.out.println(aka.getTitle());
	}
}
