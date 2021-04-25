package clients;

import models.Deleted;
import models.Pet;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface PetStoreInterface {
    @POST("/v2/pet")
    Call<Pet> create(@Body Pet specimen);

    @GET("/v2/pet/{id}")
    Call<Pet> read(@Path("id") int id);

    @PUT("/v2/pet")
    Call<Pet> update(@Body Pet specimen);

    @DELETE("/v2/pet/{id}")
    Call<Deleted> delete(@Path("id") int id);
}