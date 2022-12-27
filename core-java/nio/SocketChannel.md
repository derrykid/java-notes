## Example
```java
try (SocketChannel socketChannel = SocketChannel.open()) {
	socketChannel.connect(new InetSocketAddress("https://derry.club/", 80));

	ByteBuffer buffer = ByteBuffer.allocate(48);

	int byteRead = socketChannel.read(buffer);
	while(byteRead != -1) {

		buffer.flip();

		while(buffer.hasRemaining()) {
			System.out.println((char) buffer.get());
		}
		buffer.clear();
		byteRead = socketChannel.read(buffer);
	}

} catch (IOException e) {
	throw new RuntimeException(e);
 
```

## Open a `SocketChannel`
```java
SocketChannel socketChannel = SocketChannel.open();
socketChannel.connect(new InetSocketAddress("https://derry.club/", 80));
```

## Close the channel
```java
socketChannel.close();
```