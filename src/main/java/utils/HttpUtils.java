package utils;

import com.google.gson.Gson;
import dtos.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUtils {


    public static String fetchData(String _url) throws MalformedURLException, IOException {
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");

        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

    private static Gson gson = new Gson();


    // this Method acts as a switch for the different fetches we want to make, it can return a list containing any type of DTO.

    public static AnimalFactDTO FetchSwitch(String animal) throws ExecutionException, InterruptedException {

        if (animal != null) {
            switch (animal) {

                // use the first case in the switch as a template for additional switches you may want to add.

                case "cat":

                    return catFactDTOFetch();

                case "dog":
                    return dogFactDTOFetch();

                case "koala":

                    return koalaFactDTOFetch();

                case "fox":

                    return foxFactDTOFetch();
                case "random":
                    return randomAnimalFetch();

            }
        }

        return null;
    }

    // down here you will make the methods for the individual fetch methods that will be called in the switch.

    public static AnimalFactDTO randomAnimalFetch() throws ExecutionException, InterruptedException {
        int min = 1;
        int max = 4;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        switch (random) {

            case 1:

                return catFactDTOFetch();

            case 2:
                return dogFactDTOFetch();

            case 3:

                return koalaFactDTOFetch();

            case 4:

                return foxFactDTOFetch();


        }


        return null;
    }

    public static AnimalFactDTO catFactDTOFetch() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        // Create parallel fetches here. by following the template below

        Future<CatFactDTO> catFactDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://meowfacts.herokuapp.com/"), CatFactDTO.class)

        );


        CatFactDTO catFactDTO = new CatFactDTO(catFactDTOFuture.get());

        AnimalTypeDTO typeDTO = new AnimalTypeDTO("cat");

        AnimalFactDTO animalFactDTO = new AnimalFactDTO(typeDTO, catFactDTO.getFact());

        es.shutdown();
        return animalFactDTO;
    }

    public static AnimalFactDTO dogFactDTOFetch() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<DogFactDTO> dogFactDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://dog-api.kinduff.com/api/facts"), DogFactDTO.class)


        );

        DogFactDTO dogFactDTO = new DogFactDTO(dogFactDTOFuture.get());

        AnimalTypeDTO typeDTO = new AnimalTypeDTO("dog");

        AnimalFactDTO animalFactDTO = new AnimalFactDTO(typeDTO, dogFactDTO.getDogFact());
        es.shutdown();
        return animalFactDTO;
    }

    public static AnimalFactDTO koalaFactDTOFetch() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<KoalaFactDTO> koalaFactDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://some-random-api.ml/facts/koala"), KoalaFactDTO.class)


        );

        KoalaFactDTO koalaFactDTO = new KoalaFactDTO(koalaFactDTOFuture.get());

        AnimalTypeDTO typeDTO = new AnimalTypeDTO("koala");

        AnimalFactDTO animalFactDTO = new AnimalFactDTO(typeDTO, koalaFactDTO.getFact());
        es.shutdown();
        return animalFactDTO;
    }

    public static AnimalFactDTO foxFactDTOFetch() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<FoxFactDTO> foxFactDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://some-random-api.ml/facts/fox"), FoxFactDTO.class)


        );

        FoxFactDTO foxFactDTO = new FoxFactDTO(foxFactDTOFuture.get());

        AnimalTypeDTO typeDTO = new AnimalTypeDTO("fox");

        AnimalFactDTO animalFactDTO = new AnimalFactDTO(typeDTO, foxFactDTO.getFact());
        es.shutdown();
        return animalFactDTO;
    }


}