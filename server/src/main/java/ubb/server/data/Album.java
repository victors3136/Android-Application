package ubb.server.data;

public record Album(int id,
                    String name,
                    String artist,
                    int releaseYear,
                    String genre,
                    String imageUrl) {
}