package owen.simpleserver.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SimpleNioServer {
	public static void main(String[] args) {
		Thread t = null;
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		SelectionKey selectionKey = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 80));
			serverSocketChannel.configureBlocking(false);

			selector = Selector.open();
			selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				int readyNum = selector.select();

				if (readyNum == 0) {
					continue;
				}

				Set<SelectionKey> set = selector.selectedKeys();
				Iterator<SelectionKey> iterator = set.iterator();

				while (iterator.hasNext()) {

					selectionKey = iterator.next();

					if (selectionKey.isAcceptable()) {
						// accept

						SocketChannel channel = serverSocketChannel.accept();
						channel.configureBlocking(false);

						SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

					}

					if (selectionKey.isReadable()) {
						// readable;

						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

						SocketChannel channel = (SocketChannel) selectionKey.channel();

						int res = channel.read(byteBuffer);

						byteBuffer.flip();

						System.out.println("recieved :" + new String(byteBuffer.array(),0,byteBuffer.limit()));

						selectionKey.interestOps(SelectionKey.OP_WRITE);
						
						System.out.println("recieved finished!");

					}

					if (selectionKey.isWritable()) {


						ByteBuffer byteBuffer = ByteBuffer.wrap("hello from server!".getBytes());

						SocketChannel channel = (SocketChannel) selectionKey.channel();
						byteBuffer.rewind();

						channel.write(byteBuffer);

						selectionKey.interestOps(SelectionKey.OP_READ);
						
						
						channel.close();//是否要close

					}
					iterator.remove();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
