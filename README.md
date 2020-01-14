# Legolaskin

*Mikrobitti 1/2020, Simo Sahla*

----

### Ohjelman kääntäminen ja suoritus

Yksinkertaisin tapa ajaa ohjelma on SBT-käännöstyökalun avulla. Sen voit ottaa käyttöön näin:

- Asenna Java, jos sitä ei ole valmiiksi asennettuna. Voit tarkistaa asian komennolla `java -version`. Javan voit ladata [tästä linkistä](https://www.java.com/download/).
- Asenna SBT. Ohjeet ja latauslinkit löydät [täältä](https://www.scala-sbt.org/1.x/docs/Setup.html).
- Lataa tämän hakemiston sisältö ja mene komentorivillä projektin perushakemistoon.
- Anna komento `sbt`. SBT lataa ohjelman kääntämiseen tarvittavat kirjastot.
- Anna SBT:ssä komento `run [parametrit]` käynnistääksesi ohjelman.
- SBT-komento `assembly` luo Java-yhteensopivan .jar-paketin, jonka voit suorittaa komentoriviltä komennolla `java -jar Legolaskin-assembly-1.0.jar [parametrit]`.

Vaihtoehtoisesti voit käyttää IDE-ohjelmointiympäristöä, joka tukee Scalaa. Sellainen on esimerkiksi IntelliJ IDEA.

### Ohjelman parametrit

Legolaskimelle voi antaa parametreina seuraavat tiedot. Jos parametreja ei ole annettu, käytetään oletusarvoja.

1. tietokantatiedoston nimi (oletus: kombot.db)
2. yhdistelmiin käytettävien palikoiden määrä (oletus: 6)
3. palikan korkeus (oletus: 4)
4. palikan leveys (oletus: 2)
5. kuinka monen palikan yhdistelmistä aloitetaan (oletus: 1) (Jos esimerkiksi tiedät tietokannassa olevan valmiina kolmen palikan yhdistelmät, voit säästää aikaa antamalla aloitusarvoksi 4, jolloin valmiina olevia yhdistelmiä ei lasketa uudelleen.)

- Voit antaa komentoriviltä kaikki nämä parametrit tai vain osan niistä, jolloin puuttuville annetaan oletusarvo. 
- Numeeriset parametrit 2–4 tulee antaa juuri tässä järjestyksessä (palikoiden määrä, korkeus, leveys, aloitus), eli jos esimerkiksi haluat määritellä palikan korkeuden ja leveyden, on sinun kerrottava ensin palikoiden määrä. 
- Jos parametri ei ole numeerinen, se tulkitaan tiedostonimeksi.