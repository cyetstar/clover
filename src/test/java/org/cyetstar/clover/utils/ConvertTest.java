package org.cyetstar.clover.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ClassUtils;
import org.cyetstar.clover.entity.MovieGenre;
import org.hibernate.engine.profile.Fetch;

public class ConvertTest {

	public static void main(String[] args) {
		String name = MovieGenre.class.getName();
		name = name.substring(name.lastIndexOf(".") + 1);
		System.out.println(ClassUtils.getPackageCanonicalName(Fetch.Style.class));
		System.out.println(ClassUtils.getPackageName(MovieGenre.class));
		System.out.println(ClassUtils.getShortCanonicalName(Fetch.Style.class));
		System.out.println(ClassUtils.getSimpleName(Fetch.Style.class));
		System.out.println(ClassUtils.getShortClassName(Fetch.Style.class));
	}

	/**
	 * 转换测试 void
	 * 
	 */
	public static void convertTest() {
		int len = 10;
		String[] arr = new String[len];
		for (int i = 0; i < len; i++) {
			arr[i] = "" + i;
		}
		long begin = System.currentTimeMillis();
		ConvertUtils.convert(arr, Long.class);
		System.out.println(System.currentTimeMillis() - begin);

		begin = System.currentTimeMillis();
		Long[] longArr = new Long[len];
		for (int i = 0; i < len; i++) {
			longArr[i] = Long.parseLong(arr[i]);
		}
		System.out.println(System.currentTimeMillis() - begin);
	}

}
