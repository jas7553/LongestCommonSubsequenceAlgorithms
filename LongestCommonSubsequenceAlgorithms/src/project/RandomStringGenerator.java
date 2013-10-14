package project;
import java.util.Iterator;
import java.util.Random;

public class RandomStringGenerator implements Iterator<String> {

	private char[] alphabet;
	private int size;
	private final Random randomNumberGenerator;

	public RandomStringGenerator(char[] alphabet) {
		this(alphabet, 5);
	}

	public RandomStringGenerator(char[] alphabet, long seed) {
		this(alphabet, 5, seed);
	}

	public RandomStringGenerator(char[] alphabet, int size) {
		this.alphabet = alphabet;
		this.size = size;
		this.randomNumberGenerator = new Random();
	}

	public RandomStringGenerator(char[] alphabet, int size, long seed) {
		this.alphabet = alphabet;
		this.size = size;
		this.randomNumberGenerator = new Random(seed);
	}

	public void setAlphabet(char[] alphabet) {
		this.alphabet = alphabet;
	}

	public void setStringSize(int size) {
		this.size = size;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public String next() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int index = randomNumberGenerator.nextInt(alphabet.length);
			s.append(alphabet[index]);
		}

		return s.toString();
	}

	@Override
	public void remove() {
	}

	public static void main(String[] args) {
		char[] binaryAlphabet = new char[] { '0', '1' };
		char[] letterAlphabet = new char[] { 'A', 'C', 'G', 'T' };

		RandomStringGenerator generator = new RandomStringGenerator(letterAlphabet);
		for (int i = 0; i < 6; i++) {
			System.out.println(generator.next());
		}

		System.out.println();

		generator.setAlphabet(binaryAlphabet);
		for (int i = 0; i < 6; i++) {
			System.out.println(generator.next());
		}
	}
}
