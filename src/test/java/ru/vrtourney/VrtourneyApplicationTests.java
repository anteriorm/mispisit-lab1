package ru.vrtourney;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.vrtourney.repository.TournamentRepository;
import java.time.LocalDate;
import java.net.URI;
import java.net.http.*;
import static org.junit.jupiter.api.Assertions.*;

// Run only against a separate empty test database: the test writes and removes its own row.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VrtourneyApplicationTests {
    @Value("${local.server.port}") int port;
    @Autowired TournamentRepository repository;
    private final HttpClient client = HttpClient.newHttpClient();
    private HttpResponse<String> get(String path) throws Exception {
        return client.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + path)).build(),
            HttpResponse.BodyHandlers.ofString());
    }
    private HttpResponse<String> post(String body) throws Exception {
        return client.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/tournaments/new"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
    }
    @Test void persistsTournamentAndKeepsLab2Routes() throws Exception {
        assertEquals(0L, repository.count());
        assertTrue(get("/tournaments").body().contains("Турниров пока нет"));
        assertTrue(get("/").body().contains("0.0.3"));
        assertEquals(302, post("title=VR+Persistence&discipline=Beat+Saber&format=1x1&date=2026-12-01").statusCode());
        Long id = repository.findAll().getFirst().getId();
        try {
            assertTrue(id > 0);
            var stored = repository.findById(id).orElseThrow();
            assertEquals("Beat Saber", stored.getDiscipline());
            assertEquals("1x1", stored.getFormat());
            assertEquals(LocalDate.of(2026, 12, 1), stored.getDate());
            assertTrue(get("/tournaments").body().contains("VR Persistence"));
            assertTrue(get("/tournaments/" + id).body().contains("2026-12-01"));
            assertEquals("/access-denied", URI.create(get("/tournaments/admin-panel").headers().firstValue("Location").orElseThrow()).getPath());
            assertEquals("/tournaments", URI.create(get("/tournaments/999999999").headers().firstValue("Location").orElseThrow()).getPath());
            assertEquals(200, get("/access-denied").statusCode());
            for (String invalid : new String[]{
                "title=+++&discipline=Beat+Saber&format=1x1&date=2026-12-01",
                "title=Bad&discipline=Beat+Saber&format=1x1&date=wrong",
                "title=Bad&discipline=Beat+Saber&format=1x1&date=2026-02-30",
                "id=42&title=Bad&discipline=Beat+Saber&format=1x1&date=2026-12-01",
                "title=Bad&discipline=unknown&format=1x1&date=2026-12-01"}) {
                assertTrue(post(invalid).body().contains("корректную дату"));
            }
            assertEquals(1L, repository.count());
        } finally { repository.deleteById(id); }
    }
}
