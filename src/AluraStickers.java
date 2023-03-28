import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class AluraStickers {
    public static void main(String[] args) throws IOException, InterruptedException {

        //fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //extrair só os dados que interessam(Titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for(Map<String,String> filme : listaDeFilmes) {

            System.out.println("Posição: "+ filme.get("rank"));
            System.out.println("Título: "+filme.get("title"));
            System.out.println("Classificação: "+filme.get("imDbRating"));

            var countEstrela = Float.parseFloat(filme.get("imDbRating"));

            for(int x = 0; x < Math.floor(countEstrela); x++) {
                System.out.print("\u2B50");
            }
            System.out.println("");
        }
    }
}
