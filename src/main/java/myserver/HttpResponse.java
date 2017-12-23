 package myserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.NavigableMap;
import java.util.TreeMap;

public class HttpResponse {

	private static final String protocol = "HTTP/1.0";

	private String status;
	private NavigableMap<String, String> headers = new TreeMap<String, String>();
	private byte[] body = null;

	public HttpResponse(String status) {
		this.status = status;
		setDate(new Date());
	}

	/*
	 * Creating response string
	HTTP/1.1 200 OK
	Date: Mon, 27 Jul 2009 12:28:53 GMT
	Server: Apache/2.2.14 (Win32)
	Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
	Content-Length: 88
	Content-Type: text/html
	Connection: Closed
	 */
	public HttpResponse withFile(File f) {
		if (f.isFile()) {
			try {
				FileInputStream reader = new FileInputStream(f);
				int length = reader.available();
				body = new byte[length];
				reader.read(body);
				reader.close();
				
				setContentLength(length);
				if (f.getName().endsWith(".htm") || f.getName().endsWith(".html")) {
					setContentType("text/html");
				} else {
					setContentType("text/plain");
				}
			} catch (IOException e) {
				System.err.println("Error while reading " + f);
			}
			return this;
		} else {
			return new HttpResponse("501 Not Implemented")
				.withHtmlBody("<html><body>File " + f + " not found.</body></html>");
		}
	}

	public HttpResponse withHtmlBody(String msg) {
		setContentLength(msg.getBytes().length);
		setContentType("text/html");
		body = msg.getBytes();
		return this;
	}

	public void setDate(Date date) {
		headers.put("Date", date.toString());
	}

	public void setContentLength(long value) {
		headers.put("Content-Length", String.valueOf(value));
	}

	public void setContentType(String value) {
		headers.put("Content-Type", value);
	}

	public void removeBody() {
		body = null;
	}

	@Override
	public String toString() {
		String result = protocol + " " + status +"\n";
		for (String key : headers.descendingKeySet()) {
			result += key + ": " + headers.get(key) + "\n";
		}
		result += "\r\n";
		if (body != null) {
			result += new String(body);
		}
		return result;
	}



}
