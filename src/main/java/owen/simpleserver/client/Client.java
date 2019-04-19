package owen.simpleserver.client;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
	public static void main(String[] args) {
		try {
			SocketChannel channel = SocketChannel.open();
			channel.connect(new InetSocketAddress("127.0.0.1", 80));
		//	channel.configureBlocking(false);
			
			ByteBuffer writeBuffer = ByteBuffer.allocate(30);
			ByteBuffer readBuffer = ByteBuffer.allocate(5);

			writeBuffer.put("hello from client".getBytes());
			writeBuffer.flip();

			while (true) {

				writeBuffer.rewind();
				channel.write(writeBuffer);

				readBuffer.clear();
				
				StringBuffer sb = new StringBuffer();
				
				InputStream inputStream = channel.socket().getInputStream();
				
				byte[] b = new byte[10];
				int len = 0;
				while( (len = inputStream.read(b)) != -1) {
					sb.append(new String( b , 0 , len ));
					
					System.out.println(sb);
				}
				
//				ReadableByteChannel ch = Channels.newChannel(inputStream);
//				
//				while( ch.read(readBuffer) > 0) {
//
//					readBuffer.flip();
//					
//					sb.append(new String( readBuffer.array() , 0 , readBuffer.limit() ));
//					
//					System.out.println(" hi :" + sb );
//					
//					readBuffer.clear();
//				}
//				

				System.out.println(sb + " receive!");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}



