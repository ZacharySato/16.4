import clients.PetStoreInterface;
import clients.PetstoreService;
import models.Category;
import models.Deleted;
import models.Pet;
import models.Tags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import utils.PetMergeUtil;

import java.io.IOException;
import java.util.List;

public class Tests {
    private static final PetStoreInterface OPERATOR = PetstoreService.instance();
    private static final int NOTFOUND = 404;
    private static final int OK = 200;

    private static Pet specimen;
    private static Response<Pet> response;
    private static Response<Deleted> responseDeleted;
    private static Pet update;

    @BeforeAll
    static void setup() {
        //тестовый объект
        specimen = new Pet();
        Category c = new Category();
        Tags t1 = new Tags();
        Tags t2 = new Tags();
        c.setId(10);
        t1.setId(20);
        t1.setName("laet");
        t2.setId(30);
        t2.setName("ne kusaet");
        c.setName("psovie");
        specimen.setId(100);
        specimen.setName("sobaka");
        specimen.setStatus("begaet");
        specimen.setPhotoUrls(List.of("photo", "urls"));
        specimen.setCategory(c);
        specimen.setTags(List.of(t1, t2));
        //обновление
        update = new Pet();
        update.setId(100);
        update.setStatus("ustal");
        update.setPhotoUrls(List.of("photo 2", "urls 2"));
        t1.setId(50);
        t1.setName("ne laet");
        t2.setId(60);
        t2.setName("kusaet");
        update.setTags(List.of(t1, t2));
    }

    @Test
    @DisplayName("POST 200")
    public void postSuccess() throws IOException {
        response = OPERATOR.create(specimen).execute();
        Assertions.assertEquals(response.code(), OK);
        Assertions.assertEquals(specimen, response.body(), "POST возращает некорректный объект.");

        response = OPERATOR.read(specimen.getId()).execute();
        Assertions.assertEquals(response.code(), OK, "Созданный объект не доступен.");
        Assertions.assertEquals(specimen, response.body());
    }

    @Test
    @DisplayName("GET 200")
    public void getSuccess() throws IOException {
        response = OPERATOR.create(specimen).execute();
        Assertions.assertEquals(response.code(), OK);
        Assertions.assertEquals(specimen, response.body());

        response = OPERATOR.read(specimen.getId()).execute();
        Assertions.assertEquals(response.code(), OK);
        Assertions.assertEquals(specimen, response.body(), "GET возвращает некорректный объект.");
    }

    @Test
    @DisplayName("DELETE 200")
    public void deleteSuccess() throws IOException {
        response = OPERATOR.create(specimen).execute();
        Assertions.assertEquals(response.code(), OK);
        Assertions.assertEquals(specimen, response.body());

        responseDeleted = OPERATOR.delete(specimen.getId()).execute();
        Assertions.assertEquals(responseDeleted.code(), OK);

        response = OPERATOR.read(specimen.getId()).execute();
        Assertions.assertEquals(response.code(), NOTFOUND, "DELETE объект не удален");
    }

    @Test
    @DisplayName("PUT 200")
    //работает как POST, поэтому не проходит.
    public void putSuccess() throws IOException {
        response = OPERATOR.create(specimen).execute();
        Assertions.assertEquals(response.code(), OK);
        Assertions.assertEquals(specimen, response.body());

        response = OPERATOR.update(update).execute();
        Assertions.assertEquals(responseDeleted.code(), OK);
        Assertions.assertEquals(PetMergeUtil.update(specimen, update),
                response.body(),
                "PUT несоответствие результата");

        response = OPERATOR.read(specimen.getId()).execute();
        Assertions.assertEquals(response.code(), OK);
        Assertions.assertEquals(PetMergeUtil.update(specimen, update), response.body());
    }

    @Test
    @DisplayName("GET 404")
    public void getFailure() throws IOException {
        responseDeleted = OPERATOR.delete(specimen.getId()).execute();
        Assertions.assertEquals(responseDeleted.code(), OK);

        response = OPERATOR.read(specimen.getId()).execute();
        Assertions.assertEquals(response.code(), NOTFOUND, "GET некорректный код ответа");
    }

    @Test
    @DisplayName("PUT 404")
    public void putFailure() throws IOException {
        responseDeleted = OPERATOR.delete(specimen.getId()).execute();
        Assertions.assertEquals(responseDeleted.code(), OK);

        response = OPERATOR.update(update).execute();
        Assertions.assertEquals(responseDeleted.code(), NOTFOUND, "PUT некорректный код ответа");
    }

    @Test
    @DisplayName("DELETE 404")
    public void deleteFailure() throws IOException {
        response = OPERATOR.create(specimen).execute();
        Assertions.assertEquals(response.code(), OK);
        Assertions.assertEquals(specimen, response.body());

        responseDeleted = OPERATOR.delete(specimen.getId()).execute();
        Assertions.assertEquals(responseDeleted.code(), OK);

        responseDeleted = OPERATOR.delete(specimen.getId()).execute();
        Assertions.assertEquals(responseDeleted.code(), NOTFOUND, "DELETE некорректный код ответа");
    }
}