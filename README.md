# Articles

Aplikacja ta to proste REST API służące do obsługi prostych artykułów prasowych zapisywanych w bazie danych.

Aby uruchomic program należy:
  - uruchomić serwer bazy danych mysql korzystając ze skryptu bazy danych zawartegow w plikach z rozszerzeniem .sql
  - kod Javy wymaga zainstalowanego w środowisku kompilatora dla JAVA 11, narzędzia Maven oraz sterownika JDBC
  - aby uruchomić program należy w terminalu środowiska wpisać kolejno komędy: "mvn clean install" i "mvn spring-boot:run" 
  - z endpointami komunikujemy się za pomocą http://localhost:8080/

Aplikacja zawiera następujące endpointy:
  - /allArticles - zwraca on wszystkie artykuły posortowane według daty publikacji w kolejności nie rosnącej
  - /Article/{id} - zwraca on pojedynczy artykuł posiadający podany w adresie URL numer id 
  - /ArticleWord - zwraca on listę wszystkich artykulów posiadających w treści lub tytule szukaną frazę. Frazę, którą chcemy wyszukac podajemy w ciele requestu w formacie:
  
  ![image](https://user-images.githubusercontent.com/105129053/180037908-b4f38062-afd8-46c1-a93f-cb7c61622e58.png)

  
  - /addArticle - pozwala on na zapisanie do bazy danych nowego artykułu. Zawartość nowego wpisu podajemy w body zapytania w formie JSON w następujący sposób:
  
  ![image](https://user-images.githubusercontent.com/105129053/180037057-2468e92e-5bd3-4948-8cdb-79c003d6e3d3.png)
    
  - /updateArticle - pozwala on na zmianę wartości zapisanych w bazie danych dla konkretnego artykułu. Dane do zmiany podajemy w ciele requestu w formacie JSON w następujący sposób:
  
  ![image](https://user-images.githubusercontent.com/105129053/180037375-018fcd2a-acb3-4a8f-9152-019ff589191c.png)

  - /DeleteArticle/{id} - pozwala na usunięcie artykułu o podanym id w adresie URL z bazy danych
  
  
