## API endpoint

An API endpoint is a **specific "point of entry"** in an API. It's the most crucial part of an API's documentation. Endpoints are the main thing to make requests. So if an API's endpoints aren't listed clearly in the documentation, the API is essentially unusable.

### Make a API request

To make an API call, you'll need to create an API URL path. This is usually done by **appending an endpoint to your base URL.** 


Base URL, or domain name:
```
https://derry.club/
```

To access the endpoint
```
https://derry.club/support
```

`/support` is the endpoint. It's called "point of entry".

If you access an API through its different endpoints, you can expect different sets of responses for each one.

## API Headers

It tells:
1. Request and response body
2. Request authorization
3. Response caching
4. Response cookies

### Find headers in browser

Open Developer mode > Inspect. Navigate to the tab "NetWork", Use `Ctrl + R` to reload. Find the `Name` tab under the flow chart. Click the source url, the headers tab is shown.

### API key - authentication

Sometimes, you’ll need some sort of authentication to make a request. For this, you might need an API Key, which is often provided by the server.

In the header:
```
{
 "Authorization" : "[API key]"
}
```

### Use cURL tool to get Header information

```
curl -v google.com
```

This displays the HTTP conversation. Requests are precededby `>`, while responses are preceded by `<`.


## HTTP Verb

### GET

`GET` is the simplest type of HTTP request method - the one that browsers use each time you click a link or type a URL into the address bar. It instructs the server to transmit the data identified by the URL to the client. 
**Data should never be modified on the server side** as a result of a `GET` request. 

`GET` request is read-only, but of course, **once the client receives the data, it's free to do any operation on its end, e.g. format it for display.** 

### PUT

`PUT` request is used when you wish to **create or update** the resource. For example, `PUT /clients/robin` might create a client called Robin on the server.

This request contains the data to use in updating or creating the resource in the body. 

In cURL, you can add data to the request with `-d`:
```bash
curl -v -X PUT -d "some text"
```

### DELETE

`DELETE` should perform the contrary of `PUT`. It's used when you want to delete the resource identified by the URL of the request.

```bash
curl -v -X DELETE /clients/anne
```

This will delete all data associated with the resource, identified by `/clients/anne`.

### POST

> According to the RFC 2616 standard, the POST method should be used to request the server to accept the enclosed entity as a subordinate of the existing resource identified by the Request-URI.

This means **the POST method call will create a child resource** under a collection of resources.

#### POST vs PUT

`PUT` method is an **idempotent method while POST is not.** For example, calling the PUT method multiple times will **either create or update the same resource.** On the contrary multiple POST requests **will lead to the creation of the same resource multiple times.** 


## Classify HTTP methods

### Safe and unsafe methods

- `GET` - safe
- `PUT`, `POST`, `DELETE` is unsafe because they may result in a modification of resources.

### Idempotent Methods

`GET`, `PUT`, `DELETE` achieve same result, no matter how many times the request repeated.

No matter how many times a `PUT` or `DELETE` request is repeated, the result should be the same as if it had been done only once.

### Representations

The HTTP client and HTTP server exchange information about resources identified by URLs.

The request and response **contain a representation of the resources.** By representation, it means information, in a certain format, about the state of the resource or how the state should be in the future. **Both the header and the body are pieces of the representation.** 

####  HTTP header and format

> The HTTP headers, which contain metadata, are tightly defined by the HTTP spec; they can only contain plain text and must be formatted in a certain manner.

#### HTTP body and content-type

The body can contain data in any format, and this is where the power of HTTP truly shines. You know that you can send plain text, pictures, HTML, and XML in any human language. Through request metadata or different URLs, you can choose between different representations for the same resource.

You can send plain text, pictures, HTML, etc. **The HTTP response header specify the content type of the body.** In `Content-Type: application/json`. If it's web page, `Content-Type: text/html`.


## Response code

### 200 OK

This response code indicates that the request was successful.

### 201 Created

This indicates the request was successful and a resource was created. It is used to confirm the success of a `PUT` or `POST` request.

### 400 Bad Request

The request was malformed. This happens especially with `POST` and `PUT` requests, when the data does not pass validation or is in the wrong format.

### 404 Not Found

This response indicates that the required resource could not be found. This is generally returned to all requests which point to a URL with no corresponding resource.

### 401 Unauthorized

This error indicates that you need to perform authentication before accessing the resource.

### 405 Method Not Allowed

The HTTP method used is not supported for this resource.

### 409 Conflict

This indicates a conflict. For instance, you're using a `PUT` request to create the same resource twice.

### 500 Internal Server Error

When all else fails; generally, a 500 response is used when processing fails due to unanticipated circumstances on the server side, which cause the server to error out.
