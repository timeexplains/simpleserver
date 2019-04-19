package owen.simpleserver.http;

public class RequestPacket {
	Header header;
	Body body;
	URL url;
	Protocol protcol;
	Method method;
	
	
	
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	
	public RequestPacket() {
		
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public Protocol getProtcol() {
		return protcol;
	}
	public void setProtcol(Protocol protcol) {
		this.protcol = protcol;
	}
	
	
	
	
}
