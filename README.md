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
* [Output richieste](#output)
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


<a name="rotte"></a>
## Rotte

Abbiamo definito le seguenti rotte:

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) |` GET `|`/posts`| *restituisce un JSONObject in cui vengono mostrati tutti i post dell'utente*
[2](#2) |` GET `|`/posts/city`| *restituisce un JSONObject in cui vengono mostrati tutti i post dell'utente filtrati per una o più città*
[3](#3) |` GET `|`/posts/province`| *restituisce un JSONObject in cui vengono mostrati tutti i post dell'utente filtrati per una o più province*
[4](#4) |` GET `|`/posts/region`| *restituisce un JSONObject in cui vengono mostrati tutti i post dell'utente filtrati per una o più regioni*
[5](#5) |` GET `|`/posts/stats/ranking`| *restituisce un JSONObject in cui vengono fatte statistiche riguardo le città, province o regioni in cui l' utente è stato, in particolare il ranking per occorrenze delle tre categorie*
[6](#6) |` GET `|`/posts/location`| *restituisce un JSONObject in cui vengono mostrate tute le città, province o regioni in cui l'utente è stato*


<a name="parametri"></a>
## Parametri
Di seguito saranno indicati le rotte che necessitano di alcuni parametri e anche il tipo di parametro. La prima rotta non è presente perché non necessita di parametri.

N° | Parametri | Tipo | Richiesto
----- | ------------ | -------------------- | ----------------------
[2](#2) | `city` | *List<String>* | *SI*
[3](#3) | `province` | *List<String>* | *SI*
[4](#4) | `region` | *List<String>* | *SI*
[5](#5) | `tipo, data iniziale, data finale` | *String, String, String* | *SI, NO, NO*
[6](#6) | `tipo` | *String* | *SI*

    
<a name="output"></a>
## Output richieste
    
In risposta alla prima richiesta si avrà:
```json
    {
    "id": "107864155075941",
    "posts": {
        "data": [
            {
                "created_time": "2021-12-25T12:46:40+0000",
                "id": "107864155075941_119047300624293",
                "message": "Buon Natale a tutti i miei amici di Facebook, oggi pranzo con i lontani parenti di Firenze"
            },
            {
                "created_time": "2021-12-20T22:12:08+0000",
                "id": "107864155075941_117569294105427",
                "message": "Un bel bombardino a Bolognola ci sta tutto"
            }
```
 
In risposta alla seconda richiesta si avrà:
```json
    {
    "city": [
        {
            "Posts in treia": [
                {
                    "created_time": "2021-12-19T01:52:07+0000",
                    "id": "107864155075941_116943110834712",
                    "message": "Treia sfocata"
                },
                {
                    "created_time": "2021-12-10T18:46:16+0000",
                    "id": "107864155075941_112274567968233",
                    "message": "Ahhh la mia Treia♥️"
                },
                {
                    "created_time": "2021-12-10T07:17:12+0000",
                    "id": "107864155075941_110806978114992",
                    "message": "N'ha fatta tanta a Treia😁"
                },
                {
                    "created_time": "2021-12-07T22:02:55+0000",
                    "id": "107864155075941_107495008446189",
                    "message": "E' stata una bella esperienza ad Cagliari, ora purtroppo si ritorna a casa a Treia😞😞😞"
                }
            ]
        }
    ]
}
```
    
In risposta alla terza richiesta si avrà:
```json
    {
    "province": [
        {
            "Posts in: mc": [
                {
                    "Message": "Tornando da lavoro, giretto a Pioraco. Tanta roba🤩",
                    "Created Time": "2021-12-09T12:20:47+0000"
                },
                {
                    "Message": "Ahhh la mia Treia♥️",
                    "Created Time": "2021-12-10T18:46:16+0000"
                },
                {
                    "Message": "N'ha fatta tanta a Treia😁",
                    "Created Time": "2021-12-10T07:17:12+0000"
                },
                {
                    "Message": "O a Macerata?",
                    "Created Time": "2021-12-10T23:09:44+0000"
                },
                {
                    "Message": "Verso Civitanova Marche😜",
                    "Created Time": "2021-12-11T16:32:49+0000"
                },
                {
                    "Message": "Vi pare normale che tornando a casa da Amsterdam mi ritrovo installata a qualche km dalla mia abitazione (vicino Tolentino)  questa ANTENNA 5G!!!\nNON SIAMO CAVIE!!! #Stop5G",
                    "Created Time": "2021-12-07T22:59:32+0000"
                },
                {
                    "Message": "Treia sfocata",
                    "Created Time": "2021-12-19T01:52:07+0000"
                },
                {
                    "Message": "Un bel bombardino a Bolognola ci sta tutto",
                    "Created Time": "2021-12-20T22:12:08+0000"
                }
            ]
        }
    ]
}
```

In risposta alla quarta richiesta si avrà:
```json
    {
    "region": [
        {
            "Posts in: marche": [
                {
                    "Message": "Tornando da lavoro, giretto a Pioraco. Tanta roba🤩",
                    "Created Time": "2021-12-09T12:20:47+0000"
                },
                {
                    "Message": "Un bel bombardino a Bolognola ci sta tutto",
                    "Created Time": "2021-12-20T22:12:08+0000"
                },
                {
                    "Message": "Treia sfocata",
                    "Created Time": "2021-12-19T01:52:07+0000"
                },
                {
                    "Message": "Porto San Giorgio, bella anche d'inverno",
                    "Created Time": "2021-12-17T21:08:46+0000"
                },
                {
                    "Message": "N'ha fatta tanta a Treia😁",
                    "Created Time": "2021-12-10T07:17:12+0000"
                },
                {
                    "Message": "Ahhh la mia Treia♥️",
                    "Created Time": "2021-12-10T18:46:16+0000"
                },
                {
                    "Message": "Giretto in Ancona?",
                    "Created Time": "2021-12-10T20:37:39+0000"
                },
                {
                    "Message": "Verso Civitanova Marche😜",
                    "Created Time": "2021-12-11T16:32:49+0000"
                },
                {
                    "Message": "O a Macerata?",
                    "Created Time": "2021-12-10T23:09:44+0000"
                },
                {
                    "Message": "Oggi a pranzo in Ancona grande mangiata di biscottiii😁😁",
                    "Created Time": "2021-12-08T13:43:20+0000"
                },
                {
                    "Message": "Vi pare normale che tornando a casa da Amsterdam mi ritrovo installata a qualche km dalla mia abitazione (vicino Tolentino)  questa ANTENNA 5G!!!\nNON SIAMO CAVIE!!! #Stop5G",
                    "Created Time": "2021-12-07T22:59:32+0000"
                }
            ]
        }
    ]
}
```
    
In risposta alla quinta richiesta si avrà:
```json
    {
    "Ranking region": [
        {
            "Occurences": 11,
            "Region": "Marche"
        },
        {
            "Occurences": 3,
            "Region": "Umbria"
        },
        {
            "Occurences": 3,
            "Region": "Sardegna"
        },
        {
            "Occurences": 2,
            "Region": "Basilicata"
        },
        {
            "Occurences": 1,
            "Region": "Piemonte"
        },
        {
            "Occurences": 1,
            "Region": "Lazio"
        },
        {
            "Occurences": 1,
            "Region": "Molise"
        },
        {
            "Occurences": 1,
            "Region": "Lombardia"
        },
        {
            "Occurences": 1,
            "Region": "Toscana"
        }
    ]
}
```
    
In risposta alla sesta richiesta si avrà:
```json{
    "province": [
        [
            "SS",
            "MT",
            "CA",
            "RM",
            "FI",
            "MC",
            "PG",
            "TO",
            "MI",
            "AN",
            "FM",
            "CB"
        ]
    ]
}
```
    
La risposta data alla prima richiesta è stata tagliata per una questione di spazio.
    
<a name="eccezioni"></a>
## Eccezioni
