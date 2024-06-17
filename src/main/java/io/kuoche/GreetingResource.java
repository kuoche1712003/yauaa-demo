package io.kuoche;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

@Path("/hello")
public class GreetingResource {
    private final UserAgentAnalyzer ua = UserAgentAnalyzer.newBuilder()
            .withCache(10000)
            .build();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<UserAgent> hello(
            @HeaderParam("User-Agent") String userAgent) {
        return Uni.createFrom().item(userAgent)
                .map(ua::parse);
    }
}
