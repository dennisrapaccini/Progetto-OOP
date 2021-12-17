<p align="center">
<img src="logo2.png" width="40%" height="40%">

<div align="center">

# MetaStats - Facebook Statistics
## _Progetto di Programmazione ad Oggetti 2021-2022_
#### Applicazione Java che effettua statistiche sulle location dei post di un utente Facebook.
    
</div>

# Contenuti
* [Introduzione](#introduzione)
* [Installazione](#installazione)
* [Configurazione](#configurazione)
* [Rotte](#rotte)
* [Parametri](#parametri)
* [Eccezioni](#eccezioni)
* [Test](#test)
* [Documentazione](#documentazione)
* [Autori](#autori)

</div>

<a name="introduzione"></a>
## Introduzione

**MetaStats** ha lo scopo di effettuare statistiche e filtri, di un utente **Facebook**, relative ai post. Le statistiche riguardano le location in cui l'utente è stato (come riferimento si prendono i post dell'utente); tali informazioni riguardo il luogo sono estrapolate dalle API fornite da facebook che ci permettono di conoscere l' ora in cui è stato pubblicato un determinato post, la descrizione relativa al post e l'id associato al post. I post dell' utente sono disponibili in formato JSON attraverso una rotta GET definita nel nostro controller, a seconda del tipo di statistica o di filtro che si vuole eseguire ci saranno diverse rotte.

* **Filtri**
   * Città: Restituisce tutti i post che l' utente ha fatto in una o più città.
   * Province: Restituisce tutti i post che l' utente ha fatto in una o più province.
   * Regioni: Restituisce tutti i post che l' utente ha fatto in una o più regioni.
* **Statistiche**
   * Ranking: viene stilata una classifica delle città più visitate dall' utente.
   * Ranking: viene stilata una classifica delle province più visitate dall' utente.
   * Ranking: viene stilata una classifica delle regioni più visitate dall' utente.

<a name="installazione"></a>
## Installazione

Per l'installazione bisogna clonare la repository dal terminale: 
```
git clone https://github.com/dennisrapaccini/Progetto-OOP.git
```
E' possibile clonare la repository anche attraverso [GitHub Desktop](https://desktop.github.com/).

<a name="configurazione"></a>
## Configurazione

Per poter usufruire della nostra applicazione è necessario lanciarlo da Terminale oppure essere in possesso di un IDE, ad esempio Eclipse, che permetta di avviare il progetto come Spring boot application. Il client Postman permettrà l' utilizzo dell' applicazione che sarà in ascolto su una porta locale, nel nostro caso la seguente porta: 
```
http://localhost:8081
```
Ovviamente la porta che si troverà in ascolto potrà essere modificata a piacere andando a modificare il seguente pezzo di codice:
```
https://github.com/dennisrapaccini/Progetto-OOP/blob/main/MetaStats/src/main/resources/application.properties
```
se si va al link si vedrà: server.port=${port:8081}.

Visualizzando il progetto (su eclipse) è possibile modificare il token di accesso, fornito da facebook per accedere alle API, che abbiamo utilizzato per testare le funzionalità della nostra applicazione; inserendo, quindi, il token dell' account di cui si vogliono fare le statistiche relative ai post.










