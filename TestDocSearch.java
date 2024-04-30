import static org.junit.Assert.*;
import java.io.File;
import org.junit.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

public class TestDocSearch {
	@Test 
	public void testIndex1() throws URISyntaxException, IOException {
    Handler h = new Handler("./technical/");
    URI rootPath = new URI("http://localhost/");
    assertEquals("There are 1391 total files to search.", h.handleRequest(rootPath));
	}
	@Test 
	public void testSearch1() throws URISyntaxException, IOException {
    Handler h = new Handler("./technical/");
    String sep = File.separator;
    URI rootPath = new URI("http://localhost/search?q=Resonance");
    String expect = String.format("Found 2 paths:\n.%stechnical%sbiomed%sar615.txt\n.%stechnical%splos%sjournal.pbio.0020150.txt", sep, sep, sep, sep, sep, sep);
    assertEquals(expect, h.handleRequest(rootPath));
	}




	@Test 
	public void testSearch2() throws URISyntaxException, IOException {
    Handler h = new Handler("./technical/biomed");
    String sep = File.separator;
    URI rootPath = new URI("http://localhost/search?q=CEC%20staff");
    String expect = String.format("Found 1 paths:\n.%stechnical%sbiomed%scvm-2-4-180.txt", sep, sep, sep);
    assertEquals(expect, h.handleRequest(rootPath));
	}

	@Test 
	public void testIndex2() throws URISyntaxException, IOException {
    Handler h = new Handler("./technical/biomed");
    URI rootPath = new URI("http://localhost/");
    assertEquals("There are 837 total files to search.", h.handleRequest(rootPath));
	}

	@Test 
	public void doesntExist() throws URISyntaxException, IOException {
    Handler h = new Handler("./technical/biomed");
    String sep = File.separator;
    URI rootPath = new URI("http://localhost/search?q=hello");
    String expect = String.format("Found 0 paths:\n");
    assertEquals(expect, h.handleRequest(rootPath));
	}
}

