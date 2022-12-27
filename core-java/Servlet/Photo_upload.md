1. Code the write method ourselves
```java
@MultipartConfig
@WebServlet("/Photo")
public class Photo extends HttpServlet {
	private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Part photo = req.getPart("photo");
		String fileName = getSubmittedFileName(photo);
		write(photo, fileName);
	}

	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("Content-disposition");
		Matcher matcher = fileNameRegex.matcher(header);
		matcher.find();
		String fileName = matcher.group(1);
		if (fileName.contains("\\")) {
			return fileName.substring(fileName.lastIndexOf("\\") + 1);
		}
		return fileName;
	}

	private void write(Part photo, String filename) throws IOException, FileNotFoundException {
		try(InputStream in = photo.getInputStream(); FileOutputStream out = new FileOutputStream(String.format("/home/derry/temp/%s", filename))) {
			byte[] buffer = new byte[1024];
			int length = -1;
			while((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
		}
		
	}
}

```

2. Use `write():void` method that's provided by `Part`
```java
@MultipartConfig(location="/home/derry/...");    // location to save
// skip

protected void doPost(... ) {
    // skip
    request.setCharacterEncoding("UTF-8");
    Part photo = request.getPart("photo");
    // file name regex
    photo.write(filename);
}
```

### Get multiple uploaded files by `getParts(): Collection<Part>`

html form:
```html
<form action="upload" method="post">
    file 1 <input type="file" name="file1">
    file 2 <input type="file" name="file2">
    file 3 <input type="file" name="file3">
```

```java
protected void doPost() {
    request.setCharacterEncoding("UTF-8");
    
    // use stream to collect
    request.getParts()
            .stream()
            .filter(part -> part.getName().startsWith("file"))
            .forEach(this::write);
}
```
