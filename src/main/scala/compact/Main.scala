package compact.fp

import compact.fp.io._
import compact.fp.model._
import compact.fp.logic._

object Main extends App {

  println("Введите компоненты по строкам (id, name, manufacturer, type, compat...).")
  println("Пустая строка = конец ввода.")

  val lines = Input.readLines().takeWhile(_.nonEmpty)
  val components = lines.flatMap(Parser.parse).toList

  println("DEBUG: Прочитанные строки:")
  lines.foreach(println)

  println("DEBUG: Спарсенные компоненты:")
  components.foreach(c => println(s"- ${c.id} ${c.name} (${c.compType})"))

  Builder.buildConfiguration(components) match {
    case Right(cfg) =>
      println("\nКонфигурация успешно создана:")
      cfg.components.foreach(c => println(s"- ${c.name} (${c.compType})"))
    case Left(err) =>
      println("\nОшибка: " + err)
  }
}
