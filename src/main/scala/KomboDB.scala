import java.sql.{Connection, DriverManager, Statement}

import scala.collection.mutable.ArrayBuffer

object KomboDB {
  var yhteys : Connection = _
  var kysely : Statement = _
  var komento = new StringBuilder

  def alusta(tiedostonimi: String): Unit = {
    yhteys = DriverManager.getConnection("jdbc:sqlite:" + tiedostonimi)
    kysely = yhteys.createStatement()
  }

  def luoTaulu(kierros: Int): Unit = {
    kysely.execute("CREATE TABLE Tulokset" + kierros + " (Tulos VARCHAR(12) PRIMARY KEY) WITHOUT ROWID")
  }

  def poistaTaulut(alku: Int): Unit = {
    val poistoKysely = yhteys.createStatement()
    val hakutulos = kysely.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table'")
    while (hakutulos.next()) {
      val taulunNimi = hakutulos.getString(1)
      if (taulunNimi.replace("Tulokset", "").toInt >= alku) {
        poistoKysely.addBatch("DROP TABLE " + taulunNimi)
      }
    }
    hakutulos.close()
    poistoKysely.executeBatch()
    poistoKysely.close()
    kysely.execute("VACUUM")
  }

  def lisaaKombo(kierros: Int, kombo: String): Unit = {
    kysely.execute("INSERT OR IGNORE INTO Tulokset" + kierros + " VALUES ('" + kombo + "')")
  }

  def lisaaKombot(kierros: Int, kombot:ArrayBuffer[String]): Unit = {
    komento.clear()
    komento.append("INSERT OR IGNORE INTO Tulokset" + kierros + " VALUES ")
    for (kombo <- kombot) {
      komento.append("('")
      komento.append(kombo)
      komento.append("'), ")
    }
    komento.setLength(komento.length() - 2)
    kysely.execute(komento.toString())
  }

  def haeKombot(kierros: Int, alku: Int, maara: Int): ArrayBuffer[String] = {
    val kombot = new ArrayBuffer[String]
    val hakutulos = kysely.executeQuery("SELECT * FROM Tulokset" + kierros
      + " LIMIT " + maara + " OFFSET " + alku)
    while (hakutulos.next()) {
      kombot .addOne(hakutulos.getString("Tulos"))
    }
    hakutulos.close()
    kombot
  }

  def lukumaara(kierros: Int): Int = {
    var maara = 0
    val hakutulos = kysely.executeQuery("SELECT COUNT (Tulos) FROM Tulokset" + kierros)
    while (hakutulos.next()) {
      maara = hakutulos.getInt(1)
    }
    hakutulos.close()
    maara
  }

  def sulje(): Unit = {
    kysely.close()
    yhteys.close()
  }
}

