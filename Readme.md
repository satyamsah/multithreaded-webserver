## Creating MultiThreaded WebService

Implemented Classes

1) Application.java : Main Method of Program and calls the Server thread

2) Server.java : Server.java does the heavy lifting of  creating a threadpool which takes the number of thread created as a parmeter. If not given the number of threads spawn would be 10. Also, it is reponsible to create socket for server  using **ServerSocket** and accepts the calls from client using **ServerSocket.accept()*** method

3) Connection.java : This is reponsible for creating a connection between Server and client. It then takes input from Client which uses it own socket to communicate to server. The client uses input and  

3) HttpRequest.java : It takes the input from the client as a stream and construct the http request message as below and retun thr request object:
 ```
  GET /prod/languagecomparison HTTP/1.1
		   Host: ksatyam.execute-api.us-east-2.amazonaws.com
		   Content-Type: application/json
		   
```
4) HttpResponse.java : After the request has been invoked the HttpReponse class would be responsible to render the the requested web page along with reponse code in the header and return the response object.Creation of http response header will have the following values:

```
Creating response string
HTTP/1.1 200 OK
Date: Mon, 27 Jul 2009 12:28:53 GMT
Server: Apache/2.2.14 (Win32)
Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
Content-Length: 88
Content-Type: text/html
Connection: Closed
```


