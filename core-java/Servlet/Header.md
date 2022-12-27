##### get headers and store it in ArrayList
```java
@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<body>");
			Collections.list(request.getHeaderNames()).forEach(name -> out.printf("%s : %s <br>", name, request.getHeader(name)));

			out.println("</body>");
			out.println("</html>");
			
		}
```
