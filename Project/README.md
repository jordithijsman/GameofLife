# Conway's Game of life
## extra info
Naast de basisopdracht zijn er ook aanpassingen gebeurd in Main.java. 

In de methode *handleSaveFile* werd de volgende lijn:
`File file = fileChooser.showOpenDialog(stage);` vervangen door `File file = fileChooser.showSaveDialog(stage);` om het correcte bestandsverkennnerscherm te openen.

Bij *handleLoadFile* werd een extra try-catch toegevoegd die de exceptie *BestandOngeldigException* die in de constructor van klasse *World* wordt opgegooid op te vangen. Deze exceptieklasse wordt opgegooid wanneer het format van het bestand ongeldig is en geeft een duidelijke foutboodschap om de gebruiker te laten weten wat er fout is aan het ingevoerde bestand.

In de projectmap worden ook enkele fout geformatteerde bestanden voorzien om de exceptieklasse te illustreren: *Breedte_fout.txt*, *Hoogte_fout.txt* en *Teken_fout.txt*.

*Door Merel Vemeire en Jordi Thijsman*

