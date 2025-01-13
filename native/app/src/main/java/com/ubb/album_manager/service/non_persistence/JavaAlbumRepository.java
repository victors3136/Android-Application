package com.ubb.album_manager.service.non_persistence;

import com.ubb.album_manager.domain.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Deprecated
public class JavaAlbumRepository {
    private final List<Album> data = new ArrayList<>();

    JavaAlbumRepository() {
        data.addAll(List.of(
                new Album("Best of", "Puya", 2018, "Romanian Hip Hop", "", new Random().nextInt()),
                new Album("Yeezus", "Kanye West", 2013, "Hip Hop", "", new Random().nextInt()),
                new Album("Paranoid", "Black Sabbath", 1970, "Heavy Metal", "", new Random().nextInt()),
                new Album("Str8 outta Compton", "N.W.A.", 1989, "Hop Hop", "", new Random().nextInt()),
                new Album("Follow the Leader", "Korn", 1998, "Nu Metal", "", new Random().nextInt()),
                new Album("To pimp a butterfly", "Kendrick Lamar", 2014, "Hop Hop", "", new Random().nextInt()),
                new Album("Toxicity", "System of a Down", 2001, "Alternative", "", new Random().nextInt()),
                new Album("RELoad", "Metalica", 1997, "Alternative", "", new Random().nextInt()),
                new Album("Amenintarea Maimutei", "Kazi Ploae", 2017, "Romanian Hip Hop", "", new Random().nextInt()),
                new Album("Adio 47 Omul Vechi", "Dragonu AKA 47", 2011, "Romanian Hip Hop", "", new Random().nextInt()),
                new Album("Plastic Bertrand", "Plastic Bertrand", 1998, "Funk", "", new Random().nextInt()),
                new Album("The shape of punk to come", "Refused", 1998, "Punk", "", new Random().nextInt()),
                new Album("Celebrity Skin", "Hole", 1998, "Grunge", "", new Random().nextInt()),
                new Album("808s and Heartbreaks", "Kanye West", 2008, "Hip Hop", "", new Random().nextInt()),
                new Album("DAMN.", "Kendrick Lamar", 2016, "Hip Hop", "", new Random().nextInt()),
                new Album("Stankonia", "Outkast", 1999, "Hop Hop", "", new Random().nextInt()),
                new Album("The Infamous", "Mobb Deep", 1995, "Hop Hop", "", new Random().nextInt()),
                new Album("Blood, Fire, Death", "Bathory", 1985, "Black Metal", "", new Random().nextInt()),
                new Album("The College Dropout", "Kanye West", 2003, "Hop Hop", "", new Random().nextInt()),
                new Album("Those Once Loyal", "Bolt Thrower", 2005, "Death Metal", "", new Random().nextInt()),
                new Album("Hybrid Theory", "Linkin Park", 2000, "Nu Metal", "", new Random().nextInt()),
                new Album("Rockefeller Street", "Getter Jaani", 2011, "Europop", "", new Random().nextInt()),
                new Album("M-am nascut sa fiu fenomenal", "Alin DImantul", 2019, "Manele", "", new Random().nextInt()),
                new Album("Animal", "Ke$ha", 2010, "Pop", "", new Random().nextInt()),
                new Album("The Rokes", "The Rokes", 2000, "Rock", "", new Random().nextInt()),
                new Album("Still not getting any", "Simple Plan", 2004, "Punk", "", new Random().nextInt()),
                new Album("Different Class", "Pulp", 1995, "Disco", "", new Random().nextInt()),
                new Album("Nightflight to Venus", "Boney M.", 1978, "Disco", "", new Random().nextInt()),
                new Album("Celebration", "Madonna", 2009, "Pop", "", new Random().nextInt()),
                new Album("Piano Man", "Billy Joel", 1973, "Rock", "", new Random().nextInt()),
                new Album("American Pie", "Don McLean", 1971, "Rock", "", new Random().nextInt()),
                new Album("San ar Lidl", "Lidl", 2022, "Ambient", "", new Random().nextInt()),
                new Album("Brat", "Charli xcx", 2024, "Pop", "", new Random().nextInt()),
                new Album("Macarena", "Erika Isac", 2024, "Pop", "", new Random().nextInt()),
                new Album("Youth", "Citizen", 2013, "Emo", "", new Random().nextInt()),
                new Album("None so Vile", "Cryptopsy", 1995, "Death Metal", "", new Random().nextInt()),
                new Album("Leprosy", "Death", 1989, "Death Metal", "", new Random().nextInt()),
                new Album("Human", "Death", 1991, "Death Metal", "", new Random().nextInt()),
                new Album("Left Hand Path", "Entombed", 1990, "Death Metal", "", new Random().nextInt())
        ));
    }

    public boolean delete(Album album) {
        return data.remove(album);
    }

    public boolean edit(Album album) {
        var result = data.indexOf(album);
        if (result == -1) {
            return false;
        }
        data.set(result, album);
        return true;
    }

    public void add(Album album) {
        data.add(album);
    }

    public Optional<Album> get(int index) {
        try {
            return Optional.of(data.get(index));
        } catch (IndexOutOfBoundsException _discard) {
            return Optional.empty();
        }
    }

    public Optional<Album> getById(int id) {
        return data.stream().filter(album -> album.getId() == id).findFirst();
    }

    public List<Album> getAll() {
        return data;
    }

    public boolean delete(int albumId) {
        return data.removeIf(album -> album.getId() == albumId);
    }
}
