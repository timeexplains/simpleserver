package owen.simpleserver.http;

import java.util.Optional;
import owen.simpleserver.exception.InvalidHeaderException;

public class HttpParse {
	private static final String CRLF = "\r\n";

	public static RequestPacket getHeader(String requestStr) throws Exception {
		Header header = new Header();
		Body body = new Body();
		URL url = new URL();
		Protocol protocol = new Protocol();
		Method method = new Method();

		int crlfIndex = requestStr.indexOf(CRLF);

		if (crlfIndex == -1) {
			throw new InvalidHeaderException();
		}
		// ------------解析第一行;
		String firstLine = requestStr.substring(0, crlfIndex);
		String[] parts = firstLine.split(" ");

		if (parts.length < 3) {
			throw new InvalidHeaderException();
		}

		int urlIndex = parts[1].indexOf("?");

		url.setUrl(parts[1]);

		if (urlIndex != -1) {
			url.setUrl(parts[1].substring(0, urlIndex));

			String param = parts[1].substring(urlIndex + 1, parts[1].length());
			String[] entry = param.split("&");

			for (int i = 0, len = entry.length; i < len; i++) {
				String[] kv = entry[i].split("=");

				body.addEntry(kv[0], kv[1]);
			}
		}

		protocol.setProtocol(parts[2]);
		method.setMethod(parts[0]);

		// ----------解析剩下header行
		String[] headLine = requestStr.split(CRLF);

		for (String line : headLine) {
			if ("".equals(line)) {
				continue;
			}

			int index = line.indexOf(":");

			if (index != -1 && index+1 < line.length()) {
				String[] kv = line.split(":");
				header.addEntry(kv[0], kv[1]);
			}

			else {
				//body;部分
			}
		}

		//
		if ("POST".equals(parts[0])) {

		}

		RequestPacket packet = new RequestPacket();
		packet.setBody(body);
		packet.setHeader(header);
		packet.setUrl(url);
		packet.setProtcol(protocol);
		packet.setMethod(method);

		return packet;
	}

}
