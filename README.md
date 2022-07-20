# Articles

Aplikacja ta to proste REST API służące do obsługi prostych artykułów prasowych zapisywanych w bazie danych.

Aby uruchomic program należy:
  - uruchomić serwer bazy danych mysql poprzez zaimportowanie skryptu bazy danych zawartego w plikuu o nazwie MySQLDatabase.sql
  - kod Javy wymaga zainstalowanego w środowisku kompilatora dla JAVA 11, narzędzia Maven oraz sterownika JDBC
  - aby uruchomić program należy w terminalu środowiska wpisać kolejno komędy: "mvn clean install" i "mvn spring-boot:run" 
  - z endpointami komunikujemy się za pomocą http://localhost:8080/

Aplikacja zawiera następujące endpointy:
  - /allArticles - zwraca on wszystkie artykuły posortowane według daty publikacji w kolejności nie rosnącej
  - /Article/{id} - zwraca on pojedynczy artykuł posiadający podany w adresie URL numer id 
  - /ArticleWord - zwraca on listę wszystkich artykulów posiadających w treści lub tytule szukaną frazę. Frazę, którą chcemy wyszukac podajemy w ciele requestu w formacie:
 
 ```
 
  tytul

 ```
  
  - /addArticle - pozwala on na zapisanie do bazy danych nowego artykułu. Zawartość nowego wpisu podajemy w body zapytania w formie JSON w następujący sposób:
  
  
```JSON
{
    "title":"Ostateczny test",
    "content":"Dodajemy nowy artykul z finalna trescia",
    "publicationDate":"2021-01-03",
    "name":"Gazeta",
    "authorName":"Tomasz",
    "authorSurname":"Działdowy"
}
```


  - /updateArticle - pozwala on na zmianę wartości zapisanych w bazie danych dla konkretnego artykułu. Dane do zmiany podajemy w ciele requestu w formacie JSON w następujący sposób:

```JSON
{
    "id": 2,
    "content": {
        "id": 3,
        "title": "gen tytul nowy ",
        "content": "Proba wykrywania w tresci"
    },
    "publicationDate": "2022-07-20",
    "articleName": "test 4",
    "author": {
        "id": 4,
        "name": "Mariusz ",
        "surname": "Kowalski"
    },
    "saveDate": "2022-07-20T07:24:01.000+00:00"
}
```

  - /DeleteArticle/{id} - pozwala na usunięcie artykułu o podanym id w adresie URL z bazy danych
  
  
