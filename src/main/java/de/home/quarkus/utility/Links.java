package de.home.quarkus.utility;

import de.home.quarkus.common.Pageable;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

public class Links {

    public static Link[] getPaginationLinks(Pageable pageable, UriInfo uriInfo, long count) {

        List<Link> result = new ArrayList<>();

        int currentPage = pageable.getPage();
        int rpp = pageable.getRpp();
        long lastPage = count / rpp;

        if (currentPage > 0) {

            int previousPage = currentPage - 1;

            result.add(Link.fromUriBuilder(
                    uriInfo.getRequestUriBuilder()
                            .replaceQueryParam("page", previousPage)
                            .replaceQueryParam("rpp", rpp))
                    .rel("prev")
                    .build());

            if (0 < previousPage) {

                result.add(Link.fromUriBuilder(
                        uriInfo.getRequestUriBuilder()
                                .replaceQueryParam("page", 0)
                                .replaceQueryParam("rpp", rpp))
                        .rel("first")
                        .build());
            }
        }

        if (currentPage < lastPage) {

            int nextPage = currentPage + 1;

            result.add(Link.fromUriBuilder(
                    uriInfo.getRequestUriBuilder()
                        .replaceQueryParam("page", nextPage)
                        .replaceQueryParam("rpp", rpp))
                    .rel("next")
                    .build());

            if (lastPage > nextPage) {

                result.add(Link.fromUriBuilder(
                        uriInfo.getRequestUriBuilder()
                            .replaceQueryParam("page", lastPage)
                            .replaceQueryParam("rpp", rpp))
                        .rel("last")
                        .build());
            }
        }

        return result.toArray(new Link[0]);
    }
}
