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
# Introduzione
**MetaStats** ha lo scopo di effettuare le statistiche, di un utente **Facebook**, relative ai post. Le statistiche riguardano le location in cui l'utente è stato (come riferimento si prendono i post dell'utente); tali informazioni riguardo il luogo sono estrapolate dalle API fornite da facebook che ci permettono di conoscere l'ora in cui è stato pubblicato un determinato post, la descrizione relativa al post e l'id associato al post. I post dell'utente sono disponibili in formato JSON attraverso una rotta GET definita nel nostro controller, a seconda del tipo di statistica che si vuole eseguire ci saranno diverse rotte.
