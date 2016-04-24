package org.zlx.mail;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class getBase64 {

	/**
	 * @param args
	 */
	private static sun.misc.BASE64Encoder base64encoder = new sun.misc.BASE64Encoder();

	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		// 一般不直接对输入流操作
		// 而是封装成其它的工具辅助类，比如Scanner
		Scanner scan = new Scanner(is);
		// 这段代码用来读取键盘输入的字符串
		System.out.print("input name:");
		System.out.println("name:" + get(scan.next()));

		// 这段代码用来读取键盘输入的字符串
		System.out.print("input pwd:");
		System.out.println("pwd:" + get(scan.next()));

	}	

	public static String get(String para) {
		return base64encoder.encode(para.getBytes());
	}

}
