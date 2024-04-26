<h1>Gestione Personaggi Fantasy</h1>

<p>Questo progetto è un'applicazione web per la gestione dei personaggi in un mondo fantasy. L'applicazione consente agli utenti di creare, visualizzare, modificare ed eliminare personaggi, oltre a fornire funzionalità di autenticazione per gli utenti.</p>

<h2>Requisiti</h2>

<ul>
    <li>Ogni personaggio ha un nome, una razza, una classe, un livello e una descrizione.</li>
    <li>Le razze disponibili includono umani, elfi, dani e orchi.</li>
    <li>Le classi disponibili includono guerrieri, maghi, ranger e ladri.</li>
    <li>Gli utenti possono creare nuovi personaggi specificando nome, razza, classe e descrizione.</li>
    <li>Gli utenti possono visualizzare l'elenco completo dei personaggi.</li>
    <li>Gli utenti possono visualizzare i dettagli di un personaggio specifico.</li>
    <li>Gli utenti possono modificare i dettagli di un personaggio esistente.</li>
    <li>Gli utenti possono eliminare un personaggio dall'elenco.</li>
    <li>L'applicazione include un sistema di autenticazione per gli utenti.</li>
    <li>I personaggi sono associati agli utenti che li hanno creati.</li>
    <li>L'applicazione include una funzionalità di ricerca per cercare personaggi per nome, razza o classe.</li>
</ul>

<h2>Implementazione</h2>

<h3>Struttura del Progetto</h3>

<p>Il progetto è strutturato nei seguenti componenti principali:</p>

<ul>
    <li><strong>Classe Character</strong>: Definisce la struttura di un personaggio con attributi come id, nome, razza, classe, livello e descrizione.</li>
    <li><strong>CharacterService</strong>: Contiene i metodi per la gestione dei personaggi, come recupero, creazione, aggiornamento ed eliminazione.</li>
    <li><strong>CharacterController</strong>: Gestisce le richieste HTTP relative ai personaggi, instradando le richieste ai metodi appropriati di CharacterService.</li>
    <li><strong>Spring Security Configuration</strong>: Configurazione per l'autenticazione degli utenti.</li>
    <li><strong>Spring Data JPA Configuration</strong>: Configurazione per l'interazione con il database.</li>
</ul>

<h3>Utilizzo</h3>

<p>Per utilizzare l'applicazione, seguire i seguenti passaggi:</p>
