package org.acme;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.CheckedTemplate;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import javax.xml.transform.Templates;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/hello2")
public class GreetingResource {
    @Inject
    GreetingService greetingService;

    @Inject
    Template index;

    @GET()
    @Produces(MediaType.TEXT_PLAIN)
    @Path("greeting/{name}")
    public String greeting(String name) {
        return greetingService.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance hello() {
        return index.instance();
    }

    // localhost:8080/hello?name=ploy
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello(@RestQuery String name) {
//        return "Hello " + name;
//    }

//    @GET
//    @Transactional
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello(@QueryParam("name") String name) {
//        Greeting greeting = new Greeting();
//        greeting.name = name;
//        greeting.persist();
//        return "Hello " +name;
//    }

    @GET
    @Path("name")
    @Produces(MediaType.TEXT_PLAIN)
    public String names() {
        List<Greeting> greetings = Greeting.listAll();
        String names = greetings.stream().map(g -> g.name)
                .collect(Collectors.joining(", "));
        return "I've said hello to " + names;
    }

}
