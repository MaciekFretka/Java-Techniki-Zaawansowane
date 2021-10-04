Lab02
Aplikacja umożliwiająca przeglądanie plików o formacie .txt oraz .png,w podanej lokalizacji.

Wnioski: Zastosowanie słabych referencji chroni aplikację przed błędem Java heap space.
Garbage Collector w skrajnych sytuacjach usuwa część kolekcji z WeakHashMap. Nie zauważono różnicy pomiędzy zastosowaniem -XX:+UseParallelGC a -XX:+UseSerialGC