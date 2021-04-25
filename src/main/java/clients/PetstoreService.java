package clients;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class PetstoreService {
    private static PetStoreInterface petStore;

    private PetstoreService() { }

    public static PetStoreInterface instance() {
        if (petStore == null) {
            Retrofit retrofit = new Retrofit.
                    Builder().
                    addConverterFactory(JacksonConverterFactory.create()).
                    baseUrl("https://petstore.swagger.io/").
                    build();
            petStore = retrofit.create(PetStoreInterface.class);
        }
        return petStore;
    }
}