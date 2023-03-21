package codersdungeon;

public class WebLauncher {
    public static void main(String[] args) {

        //servlet sono classi java che corrispondono più o meno ai controller.
        // con spring si ha un servlet principale, il dispatcher, che smista le richieste
        // tomcat cerca il web.xml e grazie a quello cerca di smistare le chiamate
        // in spring il web.xml è stato sostituito da WebInizializer

        //l'archivio WAR (alternativa al jar) va caricato su un server tipo tomcat
        //con il jar springboot crea il corrispettivo del war, ci aggiunge quello che serve (compreso
        // un server equivalente a tomcat) e lo impacchetta in un unico jar. In pratica crea una classe main
        //dove dentro mette le classi di tomcat, configura la porta, la cartella dell'applicazione, aggiunge le
        //risorse, le pagine etc. Quando faccio java -jar parte il main che a sua volta avvia tomcat.

        //plugin su pom per tomcat. In fase di package
        System.out.println("Hello world!");


        // controller --- service --- entity
        //    dto        dto e item     item

        //conversione dto nel controller anziché far tornare il dto dal servizio.
        //si può far tornare anche dal servizio, sono due possibilità tra le varie.

        // 1 se il dto lo faccio tornare dal servizio mi evito di fare le conversioni nei controller

        // 2 però se i dto lo faccio nei controller ogni controller può avere il suo formato (il servizio
        //web vuole vedere i dati in modo diverso dal client).

        //è meglio che ogni progetto abbia solamente le proprie cose

    }
}