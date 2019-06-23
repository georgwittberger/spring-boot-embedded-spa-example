# Spring Boot Embedded Single Page Application Example

> Example project demonstrating how to embed a single page application in a Spring Boot web application

## Overview

The goal of this example project is to demonstrate how a single page application (SPA) can be embedded in a Spring Boot web application while dealing with the following challenges:

- **Main HTML document served by multiple routes:** For SPAs which make use of routing the main HTML document must be served by all the routes. The reason is that if a user refreshes the browser window while on a specific route of the SPA the browser will query the server with the current URL of the window. The server must return the main HTML document even for this specific route in order to allow the SPA to load properly.
- **Initial server-side data:** The server should be able to provide initial data in the main HTML document which can be picked up by the SPA during initialization (e.g. context path of the server, CSRF token, etc.).

## Running the Example

1. Clone this repository.
2. Open a terminal in the project directory.
3. Run the command:

    ```bash
    ./mvnw spring-boot:run
    ```

4. Visit: <http://localhost:8080/> (should display `Hello World`)
5. Try the HTTP requests located in `test.http`

## Concepts

### Serving the Main HTML Document of the SPA

All requests for the main HTML document are handled by the `SinglePageController`. The controller method uses a special request mapping:

```java
@GetMapping(path = { "/", "/{name:^(?!api|css|js).+}/**" }, ...)
```

In addition to serving the document for the root path it also matches requests with any sub-path. But only if the first path segment matches the regular expression which makes use of negative look-ahead in order to exclude those paths dedicated to API and static resources. Thus, this controller method will only be executed if the path of the requested URL does **not** start with `/api`, `/css` or `/js`. Requests starting with those path segments will be handled by API controllers or the resource handlers.

The controller method returns the view name `index` which tells the Spring Framework to render the template `index.mustache` located in `src/main/resources/templates`. This template file contains the main HTML document content.

### Providing Initial Server-Side Data

The main HTML document content is rendered by the `SinglePageController` using the simple Mustache template engine. The controller can prepare a view model with server-side data before instructing the view engine to render the template `index.mustache`.

In this example, we add the context path of the Spring Boot web application to the model in order to write this value into the main HTML document. In the template `index.mustache` located in `src/main/resources/templates` we can then put the context path in the `<base />` tag which is important for some SPA routing frameworks like [Angular Router](https://angular.io/guide/router).

```html
<base href="{{ contextPath }}/" />
```

It is possible to provide any other server-side data to the SPA like this, e.g. if you are using a session-based CSRF protection you can include the CSRF token as a `<meta />` tag in the document and then acquire the token when bootstrapping the SPA.

## License

[MIT](https://opensource.org/licenses/MIT)
