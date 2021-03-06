## A multi-threaded web server with thread-pooling implemented in Java.

Implemented Classes

1) Application.java : Main Method of Program and calls the Server thread

2) Server.java : Server.java does the heavy lifting of  creating a threadpool which takes the number of thread created as an input argument. If not given the number of threads spawn would be 10. Also, it is responsible to create socket for server using **ServerSocket** and accepts the calls from client using **ServerSocket.accept()** method

3) Connection.java : This is reponsible for creating a connection between Server and client. It then takes input from Client which uses it own socket to communicate to server. The client uses input and  pass it to the HttpRequest class for creating a HTTP request call.

3) HttpRequest.java : It takes the input from the client as a stream and construct the http request message as below and return the request objectw with the following  request message :
 ```
  GET /prod/languagecomparison HTTP/1.1
		   Host: ksatyam.execute-api.us-east-2.amazonaws.com
		   Content-Type: application/json
		   
```
4) HttpResponse.java : After the request has been invoked the HttpResponse class would be responsible to render the the requested web page along with response code in the header and return the response object. Creation of http response header will have the following values:

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

5) Running the setup.Clone the repo and cd to `multithreaded-webserver`.


If you want to run the project in default mode, and run the following command which will expose the webpages inside the sampleroot web directory with web server hosted on port 9999 and default number of worker thread as 10:
``` 
	java -cp MultiThreadedWebServer-0.0.1-SNAPSHOT.jar myserver.Application 
```
Test by going to the browser and hitting the following url :

`http://localhost:9999/sample.html`

**OR**

If you want to have your own web-root directory for the hosting files, you can create this web-root directory inside the same directory(in this case multithreaded-webserver)  where the `MultiThreadedWebServer-0.0.1-SNAPSHOT.jar` file is located. And keep your webpages in this web-root directory. In addition, if you want to your customized port number and thread limit you can do so by running the following command in terminal.

```
java -cp MultiThreadedWebServer-0.0.1-SNAPSHOT.jar myserver.Application  <optional root path> <optional port> <optional threads limit>

```
Test by going to the browser and hitting the following url:

`http://localhost:<port>/<webpage>`


